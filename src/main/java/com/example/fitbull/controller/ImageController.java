package com.example.fitbull.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fitbull.service.ImageService;



@RestController
@RequestMapping("/upload_image")
@CrossOrigin
public class ImageController {
	

	@Autowired
	private ImageService imageService;
	 
	public ImageController( ImageService imageService) {
		this.imageService = imageService;
	}
	
	@PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
		return imageService.uploadFileService(file);
	}


}
