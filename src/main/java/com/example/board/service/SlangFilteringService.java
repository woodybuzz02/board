package com.example.board.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.SwiftCode;
import com.example.board.domain.SwiftCodeRepository;
import com.example.board.dto.SwiftCodeDto;
import com.example.board.util.paging.Criteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SlangFilteringService {
	
	private final SwiftCodeRepository swiftCodeRepository;
	
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
	
	/** 
	검사를 하다가 욕이 나오면 사용자한테 메일 보내고 게시글 블라인드 처리를 하는 거..
	 **/
	
//	@Scheduled(cron = "0 0/10 * * * *") // 10분마다!
//	@Transactional
//	public void filterAllPost(){
//		
//	}
	
}
