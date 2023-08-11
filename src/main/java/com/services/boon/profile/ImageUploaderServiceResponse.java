package com.services.boon.profile;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploaderServiceResponse {
    private String message;
    private String url;
}
