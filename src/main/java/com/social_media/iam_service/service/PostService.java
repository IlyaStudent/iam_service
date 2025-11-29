package com.social_media.iam_service.service;

import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.dto.post.PostSearchDTO;
import com.social_media.iam_service.model.requests.post.PostRequest;
import com.social_media.iam_service.model.response.IamResponse;
import com.social_media.iam_service.model.response.PaginationResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;


public interface PostService {

    IamResponse<PostDTO>  getById(@NotNull Integer postId);

    IamResponse<PostDTO> createPost(@NotNull PostRequest postRequest);

    IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull PostRequest postRequest);

    void softDeletePost(@NotNull Integer postId);

    IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable);
}
