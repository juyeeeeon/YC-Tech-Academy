package com.msa.post.service;

import com.msa.post.domain.Post;
import com.msa.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final RedisTemplate<String, Post> postRedisTemplate;

	@Override
	public Post addPost(String title, String content) {
		// 테스트 코드가 동작하도록 구현
		Post post = new Post(title, content);
		return postRepository.save(post);
	}

	@Override
	public Optional<Post> getPost(long id) {

		Post cachedPost = postRedisTemplate.opsForValue()
				.getAndExpire(String.valueOf(id), Duration.ofMinutes(3));

		if (cachedPost != null) {
			return Optional.of(cachedPost);
		}


		/*Optional<Post> findPost = postRepository.findById(id);
		postRedisTemplate.opsForValue().set(String.valueOf(findPost.get().getId()), findPost.get());
		return findPost;
		*/

		return postRepository.findById(id)
				.map(post -> {
					postRedisTemplate.opsForValue().set(String.valueOf(id), post);
					return post;
				} );

	}

	@Override
	public List<Post> getPostListByUserId() {
		return new ArrayList<>();
	}

	@Override
	// findAll 을 통해서 모든 데이터 조회
	// created_at 기준 내림차순으로 정렬
	public List<Post> getPostList() {
		return postRepository.findAll()
				.stream()
				.sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())).toList();
	}

	@Override
	public void deletePost(long id) {
		postRepository.deleteById(id);
	}

}
