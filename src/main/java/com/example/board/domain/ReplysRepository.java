package com.example.board.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplysRepository extends JpaRepository<Replys, Integer>{

	List<Replys> findByPostId(int postId);
	
	@Query(value = "SELECT max(r.reply_group) FROM replys r", nativeQuery = true)
	Optional<Integer> findReplyGroup();

	@Query(value = "SELECT max(r.reply_order) FROM replys r WHERE reply_group = :replyGroup and post_id = :postId", nativeQuery = true)
	Integer findReplyOrder(@Param("replyGroup") int replyGroup, @Param("postId") int postId);

}
