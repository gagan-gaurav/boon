package com.services.boon.blogs;

import com.services.boon.events.Event;
import com.services.boon.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date createdAt;

    private String title;

    private Integer likes;

    private Integer dislikes;

    @Column(columnDefinition = "text")
    private String content;
}
