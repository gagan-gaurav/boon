package com.services.boon.project;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository <Project, Integer> {

    @Query(value = "SELECT p.id AS projectId, p.show_project AS showProject, p.title AS title, p.description AS description, p.start_date AS startDate, p.end_date AS endDate, " +
            "p.content AS content, u.username AS username, u.firstname AS firstname, u.lastname AS lastname FROM project p JOIN _user u on p.user_id = u.id WHERE u.id = :user_id", nativeQuery = true)
    Optional<List<ProjectProjection>> findByUser(@Param("user_id") Integer userId);

}
