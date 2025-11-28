package com.social_media.iam_service.model.requests.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private String title;
    private String content;
    private Integer likes;
}
