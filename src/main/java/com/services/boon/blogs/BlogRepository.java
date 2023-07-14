package com.services.boon.blogs;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository <Blog, Integer>  {

    @Query(value = "select b.id as blogId, b.created_at, b.title, b.content, b.dislikes, b.likes, u.firstname, u.lastname, u.username, e.liked from blog b " +
            "join _user u on b.user_id = u.id left join (select liked, blog_id from event where user_id = :jwt_user_id) e on b.id = e.blog_id where b.user_id = :user_id ;", nativeQuery = true)
    List<BlogProjection> AuthUserFindBlogsByUser(@Param("jwt_user_id") Integer JwtUserId, @Param("user_id") Integer userId);

    @Query(value = "select b.id as blogId, b.created_at, b.title, b.content, b.dislikes, b.likes, u.firstname, u.lastname, u.username from blog b " +
            "join _user u on b.user_id = u.id where b.user_id = :user_id ;", nativeQuery = true)
    List<BlogProjection> findBlogsByUser(@Param("user_id") Integer userId);

    @Query(value = "select b.id as blogId, b.created_at, b.title, b.content, b.dislikes, b.likes, u.firstname, u.lastname, u.username, e.liked from blog b " +
            "join _user u on b.user_id = u.id left join (select liked, blog_id from event where user_id = :user_id) e on b.id = e.blog_id;\n", nativeQuery = true)
    List<BlogProjection> AuthUserFindAllBlogs(@Param("user_id") Integer userId);

    @Query(value = "select b.id as blogId, b.created_at, b.title, b.content, b.dislikes, b.likes, u.firstname, u.lastname, u.username from blog b " +
            "join _user u on b.user_id = u.id;\n", nativeQuery = true)
    List<BlogProjection> findAllBlogs();
}
