package com.example.board.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.board.domain.UsersRepository;
import com.example.board.domain.Users;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
        } else {
            return new PrincipalDetails(userEntity);
        }
    }

}
