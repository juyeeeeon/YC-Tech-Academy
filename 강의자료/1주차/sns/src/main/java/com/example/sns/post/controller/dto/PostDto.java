package com.example.sns.post.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PostDto {

        @Schema(description = "게시물 제목", defaultValue = "디폴트 제목")
        private final String title;

        @Schema(description = "내용")
        private final String content;
}