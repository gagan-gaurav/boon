package com.services.boon.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
    String description;
    String resume;
    Boolean showResume;
    String linkedin;
    Boolean showLinkedin;
    String github;
    Boolean showGithub;
    String gmail;
    Boolean showGmail;
}
