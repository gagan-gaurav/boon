package com.services.boon.user;

import com.services.boon.config.JwtService;
import com.services.boon.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final ProfileService profileService;
    @GetMapping("/secured/verify_user")
    ResponseEntity<Map<String, Object>> getUser(@RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        var profile = profileService.getUserProfile(username);
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("status", "loggedIn");
        if(profile.getProfilePic() != null && profile.getProfilePic() == true){
            response.put("profileImageUrl", profile.getProfileUrl());
        }else response.put("profileImageUrl", null);
        return ResponseEntity.ok(response);
    }
}
