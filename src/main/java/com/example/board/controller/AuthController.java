package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.domain.Users;
import com.example.board.dto.SignupDto;
import com.example.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

	@Autowired
    AuthService authService;
	
    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }
    
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {
        Users userEntity = signupDto.toEntity();
        System.out.println(userEntity);
        authService.signUp(userEntity);
        return "auth/signin";
    }

	
}
