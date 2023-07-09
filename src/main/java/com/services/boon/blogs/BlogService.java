package com.services.boon.blogs;

import com.services.boon.config.JwtService;
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
    private final JwtService jwtService;

    public void createBlog(String token, BlogRequest request){
        String jwt = token.substring(7);
        var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if(!user.isPresent()) {
            System.out.println("No user present with this jwt.");
            return;
        }
        var blog = Blog.builder()
                .user(user.get())
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(new Date())
                .build();
        blogRepository.save(blog);
    }

    public List<BlogProjection> getBlogs(String username){
        var user = userRepository.findByUsername(username);
        if(!user.isPresent()) new ArrayList<BlogProjection>();
        List<BlogProjection> blogs = blogRepository.findByUser(user.get());
        return blogs;
    }

    public List<BlogProjection> getAllBlogs(){
        List<BlogProjection> blogs = blogRepository.findAllBlogs();
        return blogs;
    }
}
