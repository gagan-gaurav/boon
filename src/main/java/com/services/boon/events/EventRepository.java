package com.services.boon.events;

import com.services.boon.blogs.Blog;
import com.services.boon.blogs.BlogRequest;
import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository <Event, Integer> {

    Optional<Event> findEventByBlogAndUser(Blog blog, User user);
}
