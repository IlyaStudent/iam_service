package com.social_media.iam_service.mapper;

import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.entity.Post;
import com.social_media.iam_service.model.requests.post.PostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PostMapper {

    PostDTO toPostDTO(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post createPost(PostRequest postRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    void updatePost(@MappingTarget Post post, PostRequest updatedPostRequest);
}
