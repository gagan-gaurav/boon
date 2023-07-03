package com.services.boon.blogs;

import com.services.boon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public void createBlog(BlogRequest request){
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var blog = Blog.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(new Date())
                .build();
        blogRepository.save(blog);
    }

    public List<BlogProjection> getBlogs(String username){
        var user = userRepository.findByUsername(username)
                .orElseThrow();
        List<BlogProjection> blogs = blogRepository.findByUser(user);
        return blogs;
    }

    public List<BlogProjection> getAllBlogs(){
        List<BlogProjection> blogs = blogRepository.findAllBlogs();
        return blogs;
    }
}
