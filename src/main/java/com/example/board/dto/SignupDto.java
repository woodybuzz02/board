package com.example.board.dto;

import com.example.board.domain.Users;

import lombok.Data;

@Data
public class SignupDto {
	
    private String username;
    
    private String password;
    
    private String nickname;
    
    private String email;
    
    private int zipcode;
    
    private String steetAdr;
    
    private String detailAdr;

    public Users toEntity() {
        return Users.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .zipcode(zipcode)
                .steetAdr(steetAdr)
                .detailAdr(detailAdr)
                .build();
    }

}
