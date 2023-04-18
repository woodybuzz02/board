package com.example.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.domain.Users;
import com.example.board.dto.CMRespDto;
import com.example.board.dto.SignupDto;
import com.example.board.service.AuthService;
import com.example.board.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

	private final AuthService authService;
	private final UsersService usersService;
	
    @GetMapping("/auth/signin")
    public String signinForm(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception, Model model) {
    	model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }
    
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        Users userEntity = signupDto.toEntity();
        System.out.println(userEntity);
        authService.signUp(userEntity);
        return "auth/signin";
    }

    //유저네임 중복확인 
    @PostMapping("/auth/usernameCheck")
    @ResponseBody
    public ResponseEntity<?> usernameCheck(@RequestParam("username") String username) {
        boolean chq = usersService.usernameCheck(username);
        return new ResponseEntity<>(new CMRespDto<>(1, "유저네임 중복 확인 성공!", chq), HttpStatus.OK);
    }
}
