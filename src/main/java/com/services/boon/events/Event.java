package com.services.boon.events;

import com.services.boon.blogs.Blog;
import com.services.boon.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Integer Id;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private Integer liked;
}
