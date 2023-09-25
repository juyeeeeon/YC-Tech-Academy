package com.msa.post.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import com.msa.post.domain.Post;
import com.msa.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.msa.post.controller.dto.PostDto;
import com.msa.post.controller.dto.ResultDto;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post API", description = "Post 기능 API")
@RequiredArgsConstructor
public class PostController {

	@Autowired
	private final PostService postService;

	@PostMapping("/")
	@Operation(summary = "add post", description = "Post 를 추가하는 API")
	public ResultDto<PostDto> addPost(
			@Parameter(name = "dto", description = "post dto")
			@RequestBody PostDto dto) {
		return null;
	}

	@GetMapping("/")
	public ResultDto<List<PostDto>> getPostList() {
		List<PostDto> postDtos = postService.getPostList()
				.stream()
				.map(post -> new PostDto(post.getTitle(), post.getContent()))
				.toList();

		return new ResultDto<>(200, "ok", postDtos);
	}

	@GetMapping("/{postId}")
	public ResultDto<PostDto> getPost(@PathVariable("postId") Long postId) {

		Optional<PostDto> optPost = postService.getPost(postId)
				.map(Post::convert2DTO);

		if (optPost.isEmpty()) {
			throw new IllegalArgumentException("not exist post : %s".formatted(postId));
		} else {
			return new ResultDto<>(200, "ok", optPost.get());
		}
	}

	@DeleteMapping("/{postId}")
	public ResultDto<PostDto> deletePost(@PathVariable("postId") Long postId) {
		postService.getPost(postId)
				.ifPresentOrElse(post -> postService.deletePost(postId),
				() -> {
					throw new IllegalArgumentException("not exist post : %s".formatted(postId));
				});

		return new ResultDto<>(200, "ok", null);
	}
}
