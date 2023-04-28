package com.example.board.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SwiftCodeRepository extends JpaRepository<SwiftCode, Integer>{

	@Modifying
	@Query(value = "INSERT INTO swift_code (sc_nm, gc_id, modifier, writer, updated_at, created_at) VALUES(:scNm, (SELECT gc.id FROM group_code gc WHERE gc.gc_nm = '비속어'),:username ,:username, now(), now());", nativeQuery = true)
	void addSlang(@Param("scNm") String scNm, @Param("username") String username);
	
	@Query(value = "select * from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어') LIMIT :perPageNum OFFSET :pageStart ;", nativeQuery = true)
	List<SwiftCode> findAllSlang(@Param("perPageNum") int perPageNum, @Param("pageStart")  int pageStart);
	
	@Query(value = "select count(*) from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어');", nativeQuery = true)
	int countAllSlang();
}
