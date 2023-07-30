package com.services.boon.user;

import com.services.boon.search.UserSearchProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query(value = "select * from _user where lower(concat(firstname, lastname, username)) Like replace(lower(concat('%',:query,'%')), ' ', '')", nativeQuery = true)
    Optional<List<UserSearchProjection>> searchUser(@Param("query") String query);
}
