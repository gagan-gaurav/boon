package com.services.boon.profile;

import com.services.boon.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private User user;
    private String description;
    private String resume;
    private Boolean showResume;
    private String linkedin;
    private Boolean showLinkedin;
    private String github;
    private Boolean showGithub;
    private String gmail;
    private Boolean showGmail;
}
