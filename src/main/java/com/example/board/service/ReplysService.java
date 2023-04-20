package com.example.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Replys;
import com.example.board.domain.ReplysRepository;
import com.example.board.domain.Users;
import com.example.board.domain.UsersRepository;
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
    public Replys saveReplys(String content, int postId, int userId, int order) {

        Posts post = new Posts();
        post.setId(postId);

        Users userEntity = usersRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        Replys reply = new Replys();
        reply.setParentReplyId(postId);
        reply.setDepth(0);
        reply.setReplyGroup(postId);
        reply.setContent(content);
        reply.setPost(post);
        reply.setUser(userEntity);
        reply.setReplyOrder(order);

        return replysRepository.save(reply);
    }
    
    @Transactional
    public Replys saveChildReplys(String content, int postId, int UserId, int order) {
    	
    	Posts post = new Posts();
        post.setId(postId);

        Users userEntity = usersRepository.findById(UserId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });
        
        Replys reply = new Replys();
        reply.setParentReplyId(postId);
        reply.setDepth(1);
        reply.setContent(content);
        reply.setPost(post);
        reply.setUser(userEntity);
        reply.setReplyOrder(order);
    	
        return replysRepository.save(reply);
    }

    @Transactional(readOnly = true)
	public List<Replys> findAllReply(int postId) {
    	
    	List<Replys> replys = replysRepository.findByPostId(postId);
    	
		return replys;
	}
    
}
