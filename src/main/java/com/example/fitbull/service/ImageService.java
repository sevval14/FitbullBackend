package com.example.fitbull.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	private Path fileStorageLocation;

	public ImageService(Path fileStorageLocation) {
		this.fileStorageLocation = fileStorageLocation;

	}
	

    public String uploadFileService(MultipartFile file) {
        Path targetLocation = fileStorageLocation.resolve(file.getOriginalFilename());
        try {
            file.transferTo(targetLocation);
            return targetLocation.toString();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return null; 
        }
    }


	
}
