package com.services.boon.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private Integer projectId;
    private Boolean showProject;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String content;
}
