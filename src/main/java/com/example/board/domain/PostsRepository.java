package com.example.board.domain;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Integer>{

    @Query(value = "SELECT * FROM posts ORDER BY id DESC LIMIT :perPageNum OFFSET :pageStart", nativeQuery = true)
	List<Map<String, Object>> selectPostList(@Param("perPageNum") int perPageNum, @Param("pageStart")  int pageStart);

}
