package com.services.boon.blogs;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository <Blog, Integer>  {

    @Query("SELECT b.title AS title, b.content AS content, b.createdAt AS createdAt, b.user.firstname AS firstname, b.user.lastname AS lastname FROM Blog b WHERE b.user = :user")
    List<BlogProjection> findByUser(@Param("user") User user);

    @Query("SELECT b.title AS title, b.content AS content, b.createdAt AS createdAt, b1.firstname As firstname, b1.lastname As lastname FROM Blog b, User b1 where b.user.id = b1.id")
    List<BlogProjection> findAllBlogs();

}
