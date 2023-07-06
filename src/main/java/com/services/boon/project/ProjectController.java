package com.services.boon.project;

import com.services.boon.blogs.BlogRequest;
import com.services.boon.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/public/projects/{username}")
    ResponseEntity<List<ProjectProjection>> getProjects(@PathVariable("username") String username){
        return ResponseEntity.ok(projectService.getUserProjects(username));
    }

    @PostMapping("/secured/projects")
    ResponseEntity<Map<String, Object>> addProject(@RequestHeader("Authorization") String token, @RequestBody ProjectRequest request) {
        projectService.createProject(token, request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Project is added to the database");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
