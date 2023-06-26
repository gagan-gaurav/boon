package com.services.boon.blogs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping()
    ResponseEntity<Map<String, Object>> postBlogs(@RequestBody BlogRequest request) {
        blogService.createBlog(request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Blogs is added to the database");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<BlogProjection>> getBlogs(@PathVariable("userId") String username){
        return ResponseEntity.ok(blogService.getBlogs(username));
    }

    @GetMapping("/all")
    ResponseEntity<List<BlogProjection>> getAllBlogs(){
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    //Add pagination support here. 
}
