package com.social_media.iam_service.service.impl;

import com.social_media.iam_service.mapper.PostMapper;
import com.social_media.iam_service.model.constants.ApiErrorMessage;
import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.entity.Post;
import com.social_media.iam_service.model.exception.DataExistsException;
import com.social_media.iam_service.model.exception.NotFoundException;
import com.social_media.iam_service.model.requests.post.PostRequest;
import com.social_media.iam_service.model.response.IamResponse;
import com.social_media.iam_service.repositories.PostRepository;
import com.social_media.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Override
    public IamResponse<PostDTO> getById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId))
                );

        PostDTO postDTO = postMapper.toPostDTO(post);

        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> createPost(PostRequest postRequest) {
        if (postRepository.existsByTitle(postRequest.getTitle())) {
            throw new DataExistsException(ApiErrorMessage.POST_ALREADY_EXISTS.getMessage(postRequest.getTitle()));
        }

        Post post = postMapper.createPost(postRequest);

        Post savedPost = postRepository.save(post);

        PostDTO postDTO = postMapper.toPostDTO(savedPost);

        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull PostRequest postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId))
                );

        postMapper.updatePost(post, postRequest);
        post.setUpdated(LocalDateTime.now());
        post = postRepository.save(post);

        PostDTO postDTO = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDTO);
    }
}
