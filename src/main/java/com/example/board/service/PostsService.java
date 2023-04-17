package com.example.board.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Posts;
import com.example.board.domain.PostsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	
	private final PostsRepository postsRepository;
	
	@Transactional
	public void uploadPost(Posts post) {
		postsRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public List<Posts> postList(){
		return postsRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Long countAllPost(){
		return postsRepository.count();
	}

}
