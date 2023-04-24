package com.example.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.config.auth.PrincipalDetails;
import com.example.board.domain.Replys;
import com.example.board.dto.CMRespDto;
import com.example.board.dto.ReplyDto;
import com.example.board.service.ReplysService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
	
	private final ReplysService replysService;
	
	// 댓글 저장
	@PostMapping("/api/reply/{depth}")
	public ResponseEntity<?> saveReply(@Valid @RequestBody ReplyDto replyDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principal, @PathVariable int depth){
		
        Replys reply = replysService.saveReplys(replyDto, principal.getUser().getId());
        
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 저장 성공!", reply), HttpStatus.OK);
	}
	
	// 게시글 댓글 보기
	@GetMapping("/api/{postId}/reply")
	public ResponseEntity<?> findAllReply(@PathVariable("postId") int postId){
		
		List<Replys> replys = replysService.findAllReply(postId);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글들 보기 성공!", replys), HttpStatus.OK);
	}
	
	// 댓글 수정
	@PutMapping("/api/reply/{replyId}")
    public ResponseEntity<?> updateReply(@PathVariable("replyId") int replyId, @Valid @RequestBody ReplyDto replyDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principal) {

		Replys reply = replysService.updateReplys(replyId, replyDto, principal.getUser().getId());
		
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글 수정 성공!", reply), HttpStatus.OK);
    }
	
	// 댓글 삭제
	@DeleteMapping("/api/reply/{replyId}")
	public ResponseEntity<?> deleteReply(@PathVariable("replyId") int replyId, @AuthenticationPrincipal PrincipalDetails principal) {

		 replysService.deleteReplys(replyId, principal.getUser().getId());
		
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글 삭제 성공!", null), HttpStatus.OK);
    }

}
