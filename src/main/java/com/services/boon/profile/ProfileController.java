package com.services.boon.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/public/profile/{username}")
    ResponseEntity<ProfileProjection> getProfile(@PathVariable("username") String username){
        return ResponseEntity.ok(profileService.getUserProfile(username));
    }

    @PostMapping("/secured/profile")
    ResponseEntity<Map<String, Object>> setProfile(@RequestHeader("Authorization") String token, @RequestBody ProfileRequest request){
        profileService.setProfile(token, request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Profile updated successfully.");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/secured/profile_image")
    ResponseEntity<Map<String, Object>> setProfileImage(@RequestHeader("Authorization") String token, @RequestBody(required = false) byte[] image){
        profileService.setProfileImage(token, image);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Profile Pic updated successfully.");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
