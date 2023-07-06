package com.services.boon.blogs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/secured/blogs")
    ResponseEntity<Map<String, Object>> postBlogs(@RequestHeader("Authorization") String token, @RequestBody BlogRequest request) {
        blogService.createBlog(token, request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Blogs is added to the database");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/blogs/{username}")
    ResponseEntity<List<BlogProjection>> getBlogs(@PathVariable("username") String username){
        return ResponseEntity.ok(blogService.getBlogs(username));
    }

    @GetMapping("/public/blogs/all")
    ResponseEntity<List<BlogProjection>> getAllBlogs(){
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    //Add pagination support here.
}