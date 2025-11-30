package com.social_media.iam_service.model.requests.post;

import com.social_media.iam_service.model.enums.PostSortField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequest implements Serializable {

    private String title;
    private String content;
    private String likes;

    private Boolean deleted;
    private String keyword;
    private PostSortField sortField;
}
