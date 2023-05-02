package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Posts;
import com.example.board.domain.PostsRepository;
import com.example.board.domain.Replys;
import com.example.board.domain.ReplysRepository;
import com.example.board.domain.SwiftCode;
import com.example.board.domain.SwiftCodeRepository;
import com.example.board.dto.SwiftCodeDto;
import com.example.board.util.paging.Criteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SlangFilteringService {
	
	private final SwiftCodeRepository swiftCodeRepository;
	private final PostsRepository postsRepository;
	private final ReplysRepository replysRepository;
	
	// 욕추가	
	@Transactional
	public void addSlang(SwiftCodeDto swiftCodeDto, String username) {
		swiftCodeRepository.addSlang(swiftCodeDto.getScNm(), username);
	}
	
	// 욕목록 불러오기
	@Transactional(readOnly = true)
	public List<SwiftCode> findAllSlang(Criteria cri){
		
		int perPageNum = cri.getPerPageNum();
		int pageStart = cri.getPageStart();
		
		return swiftCodeRepository.findAllSlang(perPageNum, pageStart);
	}
	
	// 전체 갯수
	@Transactional(readOnly = true)
	public int countAllSlang(){
		return swiftCodeRepository.countAllSlang();
	}
	
	// 욕 삭제
	@Transactional
	public void deleteSlang(SwiftCodeDto swiftCodeDto) {
		swiftCodeRepository.deleteById(swiftCodeDto.getId());
	}
	
	// 게시글(제목+내용) 필터링 처리 
	@Transactional
	public List<Posts> filteringAllPost(){
		
		List<Posts> SlangPostList = postsRepository.findSlangPosts();
		postsRepository.filteringAllPost();
		
		return SlangPostList;
			
	}
	
	// 댓글 필터링 처리 
	@Transactional
	public List<Replys> filteringAllReply(){
		
		List<Replys> SlangReplyList = replysRepository.findSlangReplys();
		replysRepository.filteringAllReply();
		
		return SlangReplyList;
			
	}
	
}
