package com.example.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.board.domain.Users;

import lombok.Data;

@Data
public class SignupDto {
	
	@Size(min = 2, max = 20, message = "유저네임은 2자 이상 30자 이하로 입력해주세요.")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;
    
	@NotBlank(message = "패스워드를 입력해주세요.")
    private String password;
    
    private String email;
    
    private String zipcode;
    
    private String steetAdr;
    
    private String detailAdr;

    public Users toEntity() {
        return Users.builder()
                .username(username)
                .password(password)
                .email(email)
                .zipcode(zipcode)
                .steetAdr(steetAdr)
                .detailAdr(detailAdr)
                .build();
    }

}
