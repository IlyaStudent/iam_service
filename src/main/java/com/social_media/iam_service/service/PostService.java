package com.social_media.iam_service.service;

import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.entity.Post;
import com.social_media.iam_service.model.response.IamResponse;
import jakarta.validation.constraints.NotNull;

public interface PostService {

    IamResponse<PostDTO>  getById(@NotNull Integer id);
}
