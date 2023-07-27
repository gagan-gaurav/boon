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
        Map<String, Object> response = new HashMap<>();
        if(projectService.createProject(token, request)) {
            response.put("message", "Project is created successfully.");
            response.put("status", "success");
        }else {
            response.put("message", "user or project not found.");
            response.put("status", "fail");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/secured/projects/{project_id}")
    ResponseEntity<Map<String, Object>> updateProject(@RequestHeader("Authorization") String token, @PathVariable("project_id") Integer projectId, @RequestBody ProjectRequest request) {
        Map<String, Object> response = new HashMap<>();
        if(projectService.updateProject(token, projectId, request)) {
            response.put("message", "Project is updated successfully.");
            response.put("status", "success");
        }else {
            response.put("message", "user or project not found.");
            response.put("status", "fail");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/secured/projects/delete/{project_id}")
    ResponseEntity<Map<String, Object>> deleteProject(@RequestHeader("Authorization") String token, @PathVariable("project_id") Integer projectId) {
        Map<String, Object> response = new HashMap<>();
        if(projectService.deleteProject(token, projectId)) {
            response.put("message", "Project is removed successfully.");
            response.put("status", "success");
        }else {
            response.put("message", "user or project not found.");
            response.put("status", "fail");
        }
        return ResponseEntity.ok(response);
    }
}
