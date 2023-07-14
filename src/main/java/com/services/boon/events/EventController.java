package com.services.boon.events;

import com.services.boon.blogs.BlogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/secured/event/{blogId}")
    ResponseEntity<Map<String, Object>> modifyEvent(@PathVariable("blogId") Integer blogId, @RequestHeader("Authorization") String token, @RequestBody EventRequest request) {
        eventService.updateEvent(blogId, token, request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event Updated");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
