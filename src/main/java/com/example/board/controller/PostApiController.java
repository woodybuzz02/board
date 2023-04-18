package com.example.board.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.Posts;
import com.example.board.dto.CMRespDto;
import com.example.board.dto.ModifyPostDto;
import com.example.board.service.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostApiController {
	
	private final PostsService postsService;
	
    @DeleteMapping("api/post/{postId}")
    public ResponseEntity<?> postDelete(@PathVariable int postId){
    	postsService.deletePost(postId);
        return new ResponseEntity<>(new CMRespDto<>(1, "게시글삭제 성공", null), HttpStatus.OK);
    }
    
    @PutMapping("/api/post/{postid}")
    public CMRespDto<?> postModify(@PathVariable int postid, @Valid ModifyPostDto modifyPostDto, BindingResult bindingResult) {
        Posts post = postsService.modifyPost(postid, modifyPostDto);
        return new CMRespDto<>(1, "게시글수정 성공", post);
    }
}
