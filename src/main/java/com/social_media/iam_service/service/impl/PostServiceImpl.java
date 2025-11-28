package com.social_media.iam_service.service.impl;

import com.social_media.iam_service.model.constants.ApiErrorMessage;
import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.entity.Post;
import com.social_media.iam_service.model.exception.NotFoundException;
import com.social_media.iam_service.model.response.IamResponse;
import com.social_media.iam_service.repositories.PostRepository;
import com.social_media.iam_service.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public IamResponse<PostDTO> getById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId))
                );

        PostDTO postDTO = PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikes())
                .created(post.getCreated())
                .build();

        return IamResponse.createSuccessful(postDTO);
    }
}
