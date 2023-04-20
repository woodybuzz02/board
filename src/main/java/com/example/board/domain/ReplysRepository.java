package com.example.board.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplysRepository extends JpaRepository<Replys, Integer>{

	List<Replys> findByPostId(int postId);

}
