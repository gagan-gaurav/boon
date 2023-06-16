package com.services.boon.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping()
    public ResponseEntity<Map<String, Object>> sayHello(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "This is a sample response from the GET API");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
