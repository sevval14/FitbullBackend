package com.example.fitbull.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitbull.entities.GymEntry;
import com.example.fitbull.request.GymEntryRequest;
import com.example.fitbull.response.GymEntryResponse;
import com.example.fitbull.service.GymEntryService;

@RestController
@RequestMapping("/gym_entries")
public class GymEntryController {

    @Autowired
    private GymEntryService gymEntryService;

    public GymEntryController(GymEntryService gymEntryService) {
        this.gymEntryService = gymEntryService;
    }

    @PostMapping
    public GymEntryResponse addGymEntry(@RequestBody GymEntryRequest gymEntryRequest) throws Exception {
        return gymEntryService.createGymEntry(gymEntryRequest);
    }

    @GetMapping
    public List<GymEntryResponse> getAllGymEntries() {
        return gymEntryService.getAllGymEntries();
    }

    @GetMapping("/generate_qr/{userId}/{gymId}")
    public String generateQRCode(@PathVariable Long userId, @PathVariable Long gymId) throws Exception {
        return gymEntryService.generateQRCode(userId, gymId);
    }

    @PutMapping("/{gymEntryId}")
    public GymEntry updateOneGymEntry(@PathVariable Long gymEntryId, @RequestBody GymEntry entry) {
        return gymEntryService.updateGymEntry(gymEntryId, entry);
    }
}