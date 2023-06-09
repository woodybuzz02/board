package com.example.board.dto;

import javax.validation.constraints.NotBlank;

import com.example.board.domain.Posts;
import com.example.board.domain.Users;

import lombok.Data;

@Data
public class UploadPostDto {
	
	@NotBlank(message = "글 제목을 입력해주세요.")
	private String title;
	
	@NotBlank(message = "내용을 입력해주세요.")
	private String content;
	
	public Posts toEntity(Users user) {
		return Posts.builder()
				.title(title)
				.content(content)
				.user(user)
				.build();
	}

}
