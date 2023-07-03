package com.services.boon.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsService userDetailsService;
    @GetMapping("/{username}")
    ResponseEntity<UserDetails> getUser(@PathVariable String username){
        var user = userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
