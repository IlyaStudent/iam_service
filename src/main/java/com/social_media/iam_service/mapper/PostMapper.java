package com.social_media.iam_service.mapper;

import com.social_media.iam_service.model.dto.post.PostDTO;
import com.social_media.iam_service.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PostMapper {

    PostDTO toPostDTO(Post post);
}
