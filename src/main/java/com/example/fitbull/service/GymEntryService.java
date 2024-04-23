package com.example.fitbull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fitbull.entities.Gym;
import com.example.fitbull.entities.GymEntry;
import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.GymEntryRepository;
import com.example.fitbull.request.GymEntryRequest;
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

	  public GymEntry createGymEntry(GymEntryRequest gymEntryRequest) throws Exception {
		  
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
	        gymEntryRepository.save(entry);
	        return entry;
	    }

	    public List<GymEntry> getAllGymEntries() {
	        return gymEntryRepository.findAll();
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
