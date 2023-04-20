package com.example.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReplyDto {
	
    @NotBlank // 빈값이거나 null 체크 그리고 빈 공백
    private String content;

    @NotNull // 빈값이거나 null 체크
    private int postId;

    @NotNull // 빈값이거나 null 체크
    private int replyOrder;

}
