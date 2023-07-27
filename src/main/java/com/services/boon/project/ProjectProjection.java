package com.services.boon.project;

import java.util.Date;

public interface ProjectProjection {
    String getProjectId();
    String getUsername();
    String getFirstname();
    String getLastname();
    Boolean getShowProject();
    String getTitle();
    String getDescription();
    Date getStartDate();
    Date getEndDate();
    String getContent();
}
