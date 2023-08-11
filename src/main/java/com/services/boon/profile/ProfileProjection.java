package com.services.boon.profile;

public interface ProfileProjection {
    String getUsername();
    String getFirstname();
    String getLastname();
    String getDescription();
    String getResume();
    Boolean getShowResume();
    String getLinkedin();
    Boolean getShowLinkedin();
    String getGithub();
    Boolean getShowGithub();
    String getGmail();
    Boolean getShowGmail();
    String getProfileUrl();
    Boolean getProfilePic();
}
