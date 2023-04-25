package com.example.board.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.board.domain.RoleType;

import lombok.Data;

@Data
public class UserDto {

    @Enumerated(EnumType.STRING)
    private RoleType role; 
	
}
