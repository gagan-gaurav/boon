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
