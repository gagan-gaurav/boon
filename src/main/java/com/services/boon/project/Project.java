package com.services.boon.project;

import com.services.boon.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project{
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private User user;
    private Boolean showProject;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    @Column(columnDefinition = "text")
    private String content;
}
