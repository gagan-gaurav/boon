package com.services.boon.blogs;

import java.util.Date;

public interface BlogProjection {
    Integer getBlogId();
    Date getCreatedAt();
    String getContent();
    String getTitle();
    String getFirstname();
    String getLastname();
    String getUsername();
    Integer getDislikes();
    Integer getLikes();
    Integer getLiked();
}
