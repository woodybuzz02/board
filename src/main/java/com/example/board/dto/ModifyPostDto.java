package com.example.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ModifyPostDto {
	
	@NotBlank(message = "글 제목을 입력해주세요.")
	private String title;
	
	@NotBlank(message = "내용을 입력해주세요.")
	private String content;

}
