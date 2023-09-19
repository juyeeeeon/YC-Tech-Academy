package com.example.sns.post.controller.dto;

import lombok.Data;

@Data
public class ResultDto<T> {

    private final int code;
    private final String message;
    private final T data;
}
