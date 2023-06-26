package com.services.boon.blogs;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository <Blog, Integer>  {

    @Query("SELECT b.title AS title, b.content AS content, b.createdAt AS createdAt FROM Blog b WHERE b.user = :user")
    List<BlogProjection> findByUser(@Param("user") User user);

    @Query("SELECT b.title AS title, b.content AS content, b.createdAt AS createdAt FROM Blog b")
    List<BlogProjection> findAllBlogs();
}
