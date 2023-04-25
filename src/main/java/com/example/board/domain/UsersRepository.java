package com.example.board.domain;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	Users findByUsername(String username);
	boolean existsByUsername(String username);
	
	@Query(value = "SELECT * FROM users ORDER BY id DESC LIMIT :perPageNum OFFSET :pageStart", nativeQuery = true)
	List<Map<String, Object>> selectUserList(@Param("perPageNum") int perPageNum, @Param("pageStart")  int pageStart);

}
