package com.example.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.board.config.auth.PrincipalDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
    @GetMapping({ "/", "post/main" })
    public String mainPage(@AuthenticationPrincipal PrincipalDetails principal) {
        return "post/main";
    }

}
