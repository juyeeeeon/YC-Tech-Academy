package com.msa.post.domain;

import com.msa.post.controller.dto.PostDto;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Table(name = "post")
public class Post {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="created_at")
	private LocalDate createdAt;
	
	public Post() {
		super();
	}

	public Post(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Post(Long userId, String title, String content) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createdAt = LocalDate.now();
	}

	public Post(Long id, Long userId, String title, String content, LocalDate createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public PostDto convert2DTO() {
		return new PostDto(this.getTitle(), this.getContent());
	}




}
