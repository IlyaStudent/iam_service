package com.social_media.iam_service.repositories;

import com.social_media.iam_service.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
