package com.example.sns.post.service;

import com.example.sns.post.domain.Post;

import java.util.List;

public interface PostService {

    Post addPost(String title, String content);

    Post getPost(Long id);

    List<Post> getPostListByUserId();

    List<Post> getPostList();
}