package com.example.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Posts;
import com.example.board.domain.PostsRepository;
import com.example.board.dto.ModifyPostDto;
import com.example.board.util.paging.Criteria;

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
	public Long countAllPost(){
		return postsRepository.count();
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> selectPostList(Criteria cri){
		
		int perPageNum = cri.getPerPageNum();
		int pageStart = cri.getPageStart();
		
		return postsRepository.selectPostList(perPageNum, pageStart);
	}
	
    @Transactional(readOnly = true)
	public Posts postDetail(int postId) {
    	Posts post = postsRepository.findById(postId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 게시글은 없는 게시글입니다.");
        });
		return post;
	}
    
	@Transactional
	public void deletePost(int id) {
		postsRepository.deleteById(id);
	}
	
	@Transactional
	public Posts modifyPost(int postId, ModifyPostDto modifyPostDto) {
		Posts post = postsRepository.findById(postId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 게시글은 없는 게시글입니다.");
        });
		
		post.setTitle(modifyPostDto.getTitle());
		post.setContent(modifyPostDto.getContent());
		
		return post;
	}
	

}
