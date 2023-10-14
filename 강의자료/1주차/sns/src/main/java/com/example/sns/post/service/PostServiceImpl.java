package com.example.sns.post.service;

import com.example.sns.post.domain.Post;
import com.example.sns.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public Post addPost(String title, String content) {
        return null;
    }

    @Override
    public Post getPost(Long id) {
        return null;
    }

    @Override
    public List<Post> getPostListByUserId() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getPostList() {
        return new ArrayList<>();
    }

}