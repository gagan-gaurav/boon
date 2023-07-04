package com.services.boon.profile;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository <Profile, Integer> {

    @Query("SELECT p.description AS description, p.resume AS resume, p.showResume AS showResume, p.github AS github, p.showGithub AS showGithub," +
            "p.linkedin AS linkedin, p.showLinkedin AS showLinkedin, p.gmail AS gmail, p.showGmail AS showGmail, p.user.username as username," +
            "p.user.firstname AS firstname, p.user.lastname AS lastname FROM Profile p WHERE p.user = :user")
    Optional<ProfileProjection> findByUserCustom(@Param("user") User user);

    Optional<Profile> findByUser(User user);
}

