package com.example.board.domain;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Integer>{
	
    @Query(value = "SELECT * FROM post ORDER BY id DESC", nativeQuery = true)
    Page<Posts> findPostList(Pageable pageable);

}
