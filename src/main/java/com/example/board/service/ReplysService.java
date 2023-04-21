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
        reply.setReplyOrder(replyDto.getReplyOrder());
        
        if(replyDto.getDepth() == 0) {
        	Integer replyGroup = replysRepository.findReplyGroup().orElse(0);
            // commentGroup의 max를 찾은 후 없으면(null이면) 0을 반환하고 0 이상이 있다면 changeGroup을 통해서 1씩 증가시킨다.
        	reply.changeGroup(replyGroup);
        	reply.setReplyOrder(0);

        }else {
        	reply.setReplyGroup(replyDto.getReplyGroup());
        	reply.setParentReplyId(replyDto.getParentReplyId());
        	reply.setReplyOrder(replysRepository.findReplyOrder(replyDto.getReplyGroup(), replyDto.getPostId()));
        }
        
        return replysRepository.save(reply);
    }

    @Transactional(readOnly = true)
	public List<Replys> findAllReply(int postId) {
    	
    	List<Replys> replys = replysRepository.findByPostId(postId);
    	
		return replys;
	}
    
}
