package com.social_media.iam_service.service.impl;

import com.social_media.iam_service.mapper.PostMapper;
import com.social_media.iam_service.model.constants.ApiErrorMessage;
import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.dto.post.PostSearchDTO;
import com.social_media.iam_service.model.entity.Post;
import com.social_media.iam_service.model.exception.DataExistsException;
import com.social_media.iam_service.model.exception.NotFoundException;
import com.social_media.iam_service.model.requests.post.PostRequest;
import com.social_media.iam_service.model.requests.post.PostSearchRequest;
import com.social_media.iam_service.model.response.IamResponse;
import com.social_media.iam_service.model.response.PaginationResponse;
import com.social_media.iam_service.repository.PostRepository;
import com.social_media.iam_service.repository.criteria.PostSearchCriteria;
import com.social_media.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final RestClient.Builder builder;

    @Override
    public IamResponse<PostDTO> getById(Integer postId) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
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
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(
                        () -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId))
                );

        postMapper.updatePost(post, postRequest);
        post.setUpdated(LocalDateTime.now());
        post = postRepository.save(post);

        PostDTO postDTO = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public void softDeletePost(Integer postId) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(
                        () -> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId))
                );
        post.setDeleted(true);
        postRepository.save(post);
    }

    @Override
    public IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable) {
        Page<PostSearchDTO> posts = postRepository.findAll(pageable).map(postMapper::toPostSearchDTO);

        PaginationResponse<PostSearchDTO> paginationResponse = new PaginationResponse<>(
                posts.getContent(),
                new PaginationResponse.PaginationResponseData(
                        posts.getTotalElements(),
                        pageable.getPageSize(),
                        pageable.getPageNumber() + 1,
                        posts.getTotalPages()
                )
        );

        return IamResponse.createSuccessful(paginationResponse);

    }

    @Override
    public IamResponse<PaginationResponse<PostSearchDTO>> searchPosts(PostSearchRequest postSearchRequest, Pageable pageable) {
        Specification<Post> specification = new PostSearchCriteria(postSearchRequest);
        Page<PostSearchDTO> posts = postRepository.findAll(specification, pageable)
                .map(postMapper::toPostSearchDTO);

        PaginationResponse<PostSearchDTO> response = PaginationResponse.<PostSearchDTO>builder()
                .data(posts.getContent())
                .paginationData(
                        PaginationResponse.PaginationResponseData.builder()
                                .total(posts.getTotalElements())
                                .limit(posts.getSize())
                                .page(posts.getNumber() + 1)
                                .pages(posts.getTotalPages())
                                .build()
                )
                .build();

        return IamResponse.createSuccessful(response);

    }
}
