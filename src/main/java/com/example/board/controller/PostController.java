package com.example.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.config.auth.PrincipalDetails;
import com.example.board.domain.Posts;
import com.example.board.dto.UploadPostDto;
import com.example.board.service.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostsService postsService;
	
    @GetMapping({ "/", "post/main" })
    public String mainPage() {
        return "post/main";
    }
    
    @GetMapping("post/upload")
    public String uploadPage() {
        return "post/upload";
    }
    
    @PostMapping("/post")
    public String postUpload(UploadPostDto uploadPostDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
    	Posts post = uploadPostDto.toEntity(principalDetails.getUser());
    	System.out.println(post);
        postsService.uploadPost(post);
        return "post/main";
    }
    
    

}
