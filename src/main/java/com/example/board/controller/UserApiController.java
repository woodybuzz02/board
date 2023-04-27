package com.example.board.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.Users;
import com.example.board.dto.CMRespDto;
import com.example.board.dto.UserDto;
import com.example.board.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UsersService usersService;

    @GetMapping("/api/users/{userId}")
    public CMRespDto<?> getUserInfo(@PathVariable int userId) {
        Optional<Users> user = usersService.selectUser(userId);
        return new CMRespDto<>(1, "유저정보 가져오기 성공", user);
    }
    
	@PutMapping("/api/users")
	public CMRespDto<?> modifyUserRole(@RequestBody List<UserDto> userDtos){
		
		System.out.println(userDtos);
		
		List<Users> users = new ArrayList<>();
		
		for(int i=0; i<userDtos.size(); i++) {
			Users user = usersService.updateRole(userDtos.get(i));
			users.add(user);
		}
		
		return new CMRespDto<>(1, "유저 권한 수정 성공", users);
	}
}
