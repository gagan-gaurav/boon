package com.services.boon.events;

import com.services.boon.blogs.Blog;
import com.services.boon.blogs.BlogRepository;
import com.services.boon.config.JwtService;
import com.services.boon.user.User;
import com.services.boon.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public void updateEvent(Integer blogId, String token, EventRequest request){
        String jwt = token.substring(7);
        var user = userRepository.findByUsername(jwtService.extractUsername(jwt));
        if(!user.isPresent()) {
            System.out.println("No user present with this jwt.");
            return;
        }
        var blog = blogRepository.findById(blogId);
        if(!blog.isPresent()) {
            // what is the best way to show this exception.
            System.out.println("No blog is present with this blogId.");
            return;
        }
        Blog currentBlog = blog.get();

        var event = eventRepository.findEventByBlogAndUser(blog.get(), user.get());
        if(event.isPresent()){
            // update the value.
            Event currentEvent = event.get();
            if((currentEvent.getLiked() == 0 || currentEvent.getLiked() == null) && request.getLiked() == 1){
                currentBlog.setLikes(currentBlog.getLikes() + 1);
            } else if((currentEvent.getLiked() == 0 || currentEvent.getLiked() == null)  && request.getLiked() == -1){
                currentBlog.setDislikes(currentBlog.getDislikes() + 1);
            } else if(currentEvent.getLiked() == 1 && request.getLiked() == 0){
                currentBlog.setLikes(currentBlog.getLikes() - 1);
            } else if(currentEvent.getLiked() == 1 && request.getLiked() == -1){
                currentBlog.setLikes(currentBlog.getLikes() - 1);
                currentBlog.setDislikes(currentBlog.getDislikes() + 1);
            } else if(currentEvent.getLiked() == -1 && request.getLiked() == 1){
                currentBlog.setLikes(currentBlog.getLikes() + 1);
                currentBlog.setDislikes(currentBlog.getDislikes() - 1);
            } else if(currentEvent.getLiked() == -1 && request.getLiked() == 0){
                currentBlog.setDislikes(currentBlog.getDislikes() - 1);
            }
            currentEvent.setLiked(request.getLiked());
            eventRepository.save(currentEvent);
            blogRepository.save(currentBlog);
            return;
        }

        var newEvent = Event.builder()
                .user(user.get())
                .blog(blog.get())
                .liked(request.getLiked())
                .build();

        if(request.getLiked() == 1){
             currentBlog.setLikes(currentBlog.getLikes() + 1);
        }else if(request.getLiked() == -1){
            currentBlog.setDislikes(currentBlog.getDislikes() + 1);
        }

        eventRepository.save(newEvent);
        blogRepository.save(currentBlog);
    }
}
