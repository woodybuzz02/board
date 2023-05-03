package com.example.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReplyDto {
	
    private int postId;
	
    @NotBlank // 빈값이거나 null 체크 그리고 빈 공백
    private String content;
    
    private int parentReplyId;
    
    private int replyGroup;
    
    private int depth;
    
    private int status;

}
