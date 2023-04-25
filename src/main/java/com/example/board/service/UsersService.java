package com.example.board.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Replys;
import com.example.board.domain.Users;
import com.example.board.domain.UsersRepository;
import com.example.board.dto.ReplyDto;
import com.example.board.dto.UserDto;
import com.example.board.handler.ex.CustomApiException;
import com.example.board.util.paging.Criteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersService {
	
	private final UsersRepository usersRepository;
	
    @Transactional(readOnly = true)
    public boolean usernameCheck(String username) {
        return usersRepository.existsByUsername(username);
    }

	@Transactional(readOnly = true)
	public Long countAllUser(){
		return usersRepository.count();
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> selectUserList(Criteria cri){
		
		int perPageNum = cri.getPerPageNum();
		int pageStart = cri.getPageStart();
		
		return usersRepository.selectUserList(perPageNum, pageStart);
	}
	
	public Optional<Users> selectUser(int userId) {
		return usersRepository.findById(userId);
	}
	
	// 댓글 수정
    @Transactional
    public Users updateRole(int userId, UserDto userDto) {
    	
    	Users user = usersRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("해당 유저는 없는 유저입니다.");
        });
    	
    	user.setRole(userDto.getRole());
        	 
        return user;

    }
}
