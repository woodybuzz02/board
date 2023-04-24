package com.example.board.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.board.util.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper=false)
public class Replys extends BaseEntity{ // 댓글 글번호, 게시글 글번호, 댓글 작성자, 댓글 작성일, 부모글, 댓글 내용
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 댓글의 ID
	
	private String content;
	
	private int parentReplyId; // 대댓글의 경우 모댓글의 id를 저장하여 누구의 대댓글인지 확인
	
	private int replyGroup; // 그룹 그룹과 부모댓글id는 항상 같다.
	
	private int depth; // 댓글의 깊이 일반 댓글은 0, 대댓글은 1
	
	private int replyOrder; // 댓글 순서
	
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Posts post;
    
    public void changeGroup(int replyGroup) {
    	this.replyGroup = replyGroup+1;
    }
    
    public void changeOrder(int findedReplyOrder) {
    	this.replyOrder = findedReplyOrder+1;
    }
}
