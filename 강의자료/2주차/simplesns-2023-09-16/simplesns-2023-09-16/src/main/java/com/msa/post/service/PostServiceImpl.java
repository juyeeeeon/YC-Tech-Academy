package com.msa.post.service;

import com.msa.post.domain.Post;
import com.msa.post.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}


	@Override
	public Post addPost(String title, String content) {
		Post post = new Post(1L, title, content);

		return postRepository.save(post);
	}

	@Override
	public Optional<Post> getPost(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public List<Post> getPostListByUserId() {
		return new ArrayList<>();
	}

	@Override
	public List<Post> getPostList() {
		return postRepository.findAll()
				.stream()
				.sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()))
				.toList();
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
}
