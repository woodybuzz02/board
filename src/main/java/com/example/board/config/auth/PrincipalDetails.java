package com.example.board.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.board.domain.Users;

public class PrincipalDetails implements UserDetails {

private static final long serialVersionUID = 1L;
    
    private Users user;
    
    public PrincipalDetails(Users user) {
        this.user = user;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Collection<GrantedAuthority> collector = new ArrayList<>();
	        collector.add(() -> {
	            return user.getRole();
	        });
	     return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정만료여부 리턴 - true를 리턴하면 만료되지않음
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정잠김여부 리턴 - true를 리턴하면 계정이 잠겨있지않음
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	 // 계정의 패스워드 만료여부 리턴 - true를 리턴하면 패스워드가 만료되지않음
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 사용가능 여부 리턴 - true를 리턴하면 사용가능한 계정임.
	@Override
	public boolean isEnabled() {
		return true;
	}


}
