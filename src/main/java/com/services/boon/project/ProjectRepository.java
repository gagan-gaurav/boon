package com.services.boon.project;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository <Project, Integer> {

    @Query("SELECT p.showProject AS showProject, p.title AS title, p.description AS description, p.startDate AS startDate, p.endDate AS endDate," +
            "p.content AS content, p.user.username AS username, p.user.firstname AS firstname, p.user.lastname AS lastname FROM Project p WHERE p.user = :user")
    Optional<List<ProjectProjection>> findByUser(@Param("user") User user);
}
