package com.example.fitbull.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.fitbull.entities.Activity;
import com.example.fitbull.repo.ActivityRepository;
import com.example.fitbull.repo.GymRepository;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private GymRepository gymRepository;

    // Tüm aktiviteleri listele
    @GetMapping
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // Yeni bir aktivite oluştur
    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody Activity newActivity) {
        if (newActivity.getGym() != null && newActivity.getGym().getId() != null) {
            // Gym'in varlığını kontrol et
            return gymRepository.findById(newActivity.getGym().getId())
                .map(gym -> {
                    newActivity.setGym(gym); // Gym'i ayarla
                    Activity savedActivity = activityRepository.save(newActivity); // Aktiviteyi kaydet
                    return ResponseEntity.ok(savedActivity); // Kaydedilen aktiviteyi dön
                })
                .orElse(ResponseEntity.notFound().build()); // Eğer Gym bulunamazsa, 404 dön
        } else {
            return ResponseEntity.badRequest().body("Gym ID is required");
        }
    }
}
