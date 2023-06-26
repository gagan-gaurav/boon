package com.services.boon.blogs;

import java.util.Date;

public interface BlogProjection {
    String getTitle();
    String getContent();
    Date getCreatedAt();
}
