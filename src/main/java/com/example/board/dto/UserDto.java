package com.example.board.dto;

import com.example.board.domain.RoleType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {
	
	private int id;

	@JsonProperty("role")
    private RoleType role;
	
}
