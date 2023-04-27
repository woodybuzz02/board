package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleType {
	ADMIN, 
	USER;
	
	@JsonCreator
    public static RoleType from(String s) {
        return RoleType.valueOf(s.toUpperCase());
    }
	
}
