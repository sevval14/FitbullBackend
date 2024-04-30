package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymEntry;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.entities.Services;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.GymEntryRepository;
import com.example.fitbull.request.GymEntryRequest;
import com.example.fitbull.response.GymEntryResponse;
import com.example.fitbull.response.ServiceResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
@Service
public class GymEntryService {
	
	private GymEntryRepository gymEntryRepository;
	private GymService gymService;
	private UserService userService;


	public GymEntryService(GymEntryRepository gymEntryRepository,GymService gymService,UserService userService) {
		this.gymEntryRepository = gymEntryRepository;
		this.gymService=gymService;
		this.userService=userService;
	}
	

	  public GymEntryResponse createGymEntry(GymEntryRequest gymEntryRequest) throws Exception {
		  
		     GymEntry existingEntry = gymEntryRepository.findByUserId(gymEntryRequest.getUserId());
		     if (existingEntry != null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already has an entry.");
		    }
		    Gym gym = gymService.getOneGymId(gymEntryRequest.getGymId());
		    User user = userService.getOneUserById(gymEntryRequest.getUserId());

	        GymEntry entry = new GymEntry();
	        entry.setId(gymEntryRequest.getId());
	        entry.setUser(user);
	        entry.setGym(gym);
	        entry.setEntryTime(gymEntryRequest.getEntryTime());
	        if(user==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is not.");
	        }
	        user.setGymEntry(entry);
	        GymEntry gymEntry =gymEntryRepository.save(entry);
	        
	        GymEntryResponse gymEntryResponse = new GymEntryResponse();
	        gymEntryResponse.setId(gymEntry.getId());
	        gymEntryResponse.setUserId(gymEntry.getUser().getId());
	        gymEntryResponse.setGymId(gymEntry.getGym().getId());
	        gymEntryResponse.setEntryTime(gymEntry.getEntryTime());

	        return gymEntryResponse;
	    }

	    public List<GymEntryResponse> getAllGymEntries() {
	    	List<GymEntry> gymEntries = gymEntryRepository.findAll();
	    	
	    
	    	  return gymEntries.stream()
			            .map(this::convertToServiceResponse)
			            .collect(Collectors.toList()); 
	    }
	    
		private GymEntryResponse convertToServiceResponse(GymEntry gymEntry) {
			GymEntryResponse response = new GymEntryResponse();
		    response.setId(gymEntry.getId());
		    response.setUserId(gymEntry.getUser().getId());
		    response.setGymId(gymEntry.getGym().getId());
		    response.setEntryTime(gymEntry.getEntryTime());

		    return response;
		}


	    public String generateQRCode(Long userId, Long gymId) throws Exception {
	        String qrCodeData = userId + "," + gymId;
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);
	        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	        byte[] pngData = pngOutputStream.toByteArray(); 
	        return Base64.getEncoder().encodeToString(pngData);
	    }
}