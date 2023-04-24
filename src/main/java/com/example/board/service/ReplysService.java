package com.example.board.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Replys;
import com.example.board.domain.ReplysRepository;
import com.example.board.domain.Users;
import com.example.board.domain.UsersRepository;
import com.example.board.dto.ReplyDto;
import com.example.board.handler.ex.CustomApiException;
import com.example.board.domain.Posts;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplysService {
	
	private final UsersRepository usersRepository;
	private final ReplysRepository replysRepository;
	
	// 댓글 구현
    @Transactional
    public Replys saveReplys(ReplyDto replyDto, int userId) {

        Posts post = new Posts();
        post.setId(replyDto.getPostId());

        Users userEntity = usersRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });
        
        Replys reply = new Replys();
        reply.setDepth(replyDto.getDepth());
        reply.setContent(replyDto.getContent());
        reply.setPost(post);
        reply.setUser(userEntity);
        
        if(replyDto.getDepth() == 0) {
        	Integer replyGroup = replysRepository.findReplyGroup().orElse(0);
            // commentGroup의 max를 찾은 후 없으면(null이면) 0을 반환하고 0 이상이 있다면 changeGroup을 통해서 1씩 증가시킨다.
        	reply.changeGroup(replyGroup);
        	reply.setReplyOrder(0);

        }else {
        	reply.setReplyGroup(replyDto.getReplyGroup());
        	reply.setParentReplyId(replyDto.getParentReplyId());
        	Integer replyOrder = replysRepository.findReplyOrder(replyDto.getReplyGroup(), replyDto.getPostId()).orElse(0);
        	reply.changeOrder(replyOrder);
        	System.out.println(reply.getReplyOrder()); 
        }
        
        return replysRepository.save(reply);
    }

    // 댓글 불러오기
    @Transactional(readOnly = true)
	public List<Replys> findAllReply(int postId) {
    	
    	List<Replys> replys = replysRepository.findByPostId(postId);
    	
		return replys;
	}
    
    // 댓글 수정
    @Transactional
    public Replys updateReplys(int replyId, ReplyDto replyDto, int userId) {
    	
    	Replys reply = replysRepository.findById(replyId).orElseThrow(() -> {
            throw new CustomApiException("해당 댓글은 없는 댓글입니다.");
        });
    	
        reply.setContent(replyDto.getContent());
        	 
        return reply;

    }
    
    // 댓글 삭제
    @Transactional
    public void deleteReplys(int replyId, int userId) {
    	
    	Replys reply = replysRepository.findById(replyId).orElseThrow(() -> {
            throw new CustomApiException("해당 댓글은 없는 댓글입니다.");
        });
    	
    	Integer countChild = replysRepository.countChildList(reply.getId()).orElse(0);
    	
    	// 부모댓글인 경우
    	if(reply.getParentReplyId() == 0) {
		
    		// 자식댓글이 있을 경우
    		if(countChild != 0) {
    			reply.setContent("삭제된 댓글입니다.");
    		}else{// 자식댓글이 없을 경우	
    			replysRepository.deleteById(reply.getId());
    		}	
    		
    	}else { // 자식댓글인 경우
    		
    		Replys parentReply = replysRepository.findById(reply.getParentReplyId()).orElseThrow(() -> {
                throw new CustomApiException("해당 댓글은 없는 댓글입니다.");
            });
    		
    		countChild = replysRepository.countChildList(parentReply.getId()).orElse(0);
    		
    		System.out.println(parentReply.getContent());
    		
    		// 부모댓글이 없을 경우(=content의 내용이 "삭제된 댓글입니다."인 경우)
    		if(parentReply.getContent().equals("삭제된 댓글입니다.")) { 
    			System.out.println("countChild: "+countChild);
    			System.out.println("parentReplyId: "+parentReply.getId());
    			if(countChild != 1) { // 다른 자식댓글이 있는 경우
    				replysRepository.deleteById(reply.getId());
        		}else if(countChild == 1){// 다른 자식댓글이 없을 경우
        			replysRepository.deleteById(parentReply.getId()); // 부모댓글 삭제
        			replysRepository.deleteById(reply.getId()); // 자식댓글 삭제
        		}	
    		}else { // 부모댓글이 있는 경우
    			replysRepository.deleteById(reply.getId());
    		}
    		
    	}
    }
    
}