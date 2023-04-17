package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.domain.Posts;
import com.example.board.domain.Users;

import lombok.Data;

@Data
public class UploadPostDto {
	
    private int id;
	
	private String title;
	
	private String content;
	
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private String writer;
    
    private String modifier;
	
	public Posts toEntity(Users user) {
		return Posts.builder()
				.title(title)
				.content(content)
				.user(user)
				.build();
	}

}
