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
                .likes(0)
                .dislikes(0)
                .build();
        blogRepository.save(blog);
    }

    public List<BlogProjection> getBlogs(String token, String username){
        var user = userRepository.findByUsername(username);
        if(!user.isPresent()) return new ArrayList<BlogProjection>();
        if(token != null) {
            String jwt = token.substring(7);
            var jwtUser = userRepository.findByUsername(jwtService.extractUsername(jwt));
            if (jwtUser.isPresent()) {
                List<BlogProjection> blogs = blogRepository.AuthUserFindBlogsByUser(jwtUser.get().getId(), user.get().getId());
                return blogs;
            }
        }
        List<BlogProjection> blogs = blogRepository.findBlogsByUser(user.get().getId());
        return blogs;
    }

    public List<BlogProjection> getAllBlogs(String token){
        if(token != null) {
            String jwt = token.substring(7);
            var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
            if (user.isPresent()) {
                List<BlogProjection> blogs = blogRepository.AuthUserFindAllBlogs(user.get().getId());
                return blogs;
            }
        }
        List<BlogProjection> blogs = blogRepository.findAllBlogs();
        return blogs;
    }
}
