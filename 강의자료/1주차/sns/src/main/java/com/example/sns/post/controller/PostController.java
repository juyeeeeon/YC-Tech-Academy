package com.example.sns.post.controller;

import com.example.sns.post.controller.dto.PostDto;
import com.example.sns.post.controller.dto.ResultDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Tag(name = "Post API", description = "Post 기능 API")
public class PostController {

    @PostMapping("/")
    @Operation(summary = "add post", description = "Post 를 추가하는 API")
    public ResultDto<PostDto> addPost(
            @Parameter(name = "dto", description = "post dto")
            @RequestBody PostDto dto) {
        return null;
    }

    @GetMapping("/")
    public ResultDto<List<PostDto>> getPostList() {
        return null;
    }

    @GetMapping("/{postId}")
    public ResultDto<PostDto> getPost(@PathVariable("postId") String postId) {
        return null;
    }
}