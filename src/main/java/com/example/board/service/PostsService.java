package com.example.board.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.scheduling.annotation.Scheduled;
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
	
	/** 
	검사를 하다가 욕이 나오면 사용자한테 메일 보내고 게시글 블라인드 처리를 하는 거..
	 **/
	
	@Scheduled(cron = "0 0/10 * * * *") // 10분마다!
	@Transactional
	public void filterAllPost(){
		List<Posts> posts = postsRepository.findAll();
		
		posts.forEach((post) -> {
			
			Pattern p = Pattern.compile("시발", Pattern.CASE_INSENSITIVE); // 대소문자 구분하지 않음.
	        Matcher m = p.matcher(post.getContent());

	        System.out.println("되냐..?");
	        System.out.println(m.matches()); // 대상 문자열과 패턴이 일치할 경우 true 반환!
	        
	        
		
		});
	}
	

}
