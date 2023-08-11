package com.services.boon.profile;

import com.services.boon.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository <Profile, Integer> {

    @Query(value = "SELECT u.username AS username, u.firstname AS firstname, u.lastname as lastname, p.description AS description," +
            "p.resume AS resume, p.show_resume AS showResume, p.linkedin AS linkedin, p.show_linkedin as showLinkedin, " +
            "p.github AS github, p.show_github AS showGithub, p.gmail AS gmail, p.show_gmail AS showGmail, " +
            "p.profile_url AS ProfileUrl, p.profile_pic AS ProfilePic FROM profile p JOIN _user u ON p.user_id = u.id WHERE u.username = :user", nativeQuery = true)
    Optional<ProfileProjection> findByUsername(@Param("user") String user);

    Optional<Profile> findByUser(User user);
}

