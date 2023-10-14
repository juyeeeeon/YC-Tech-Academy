package com.example.sns.post.repository;

import com.example.sns.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Serializable> {

    List<Post> findByOrderByIdDesc();

    List<Post> findAllByUserId(Long userId);

    List<Post> findByIdInOrderByIdDesc(List<Long> postIdList);

}
