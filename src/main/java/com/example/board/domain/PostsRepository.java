package com.example.board.domain;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Integer>{

    @Query(value = "SELECT * FROM posts ORDER BY id DESC LIMIT :perPageNum OFFSET :pageStart", nativeQuery = true)
	List<Map<String, Object>> selectPostList(@Param("perPageNum") int perPageNum, @Param("pageStart")  int pageStart);
    
	@Query(value = "select * from posts p where status=0 and (p.content similar to (select string_agg('%'||sc_nm||'%', '|') from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어')) or p.title similar to (select string_agg('%'||sc_nm||'%', '|') from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어')));", nativeQuery = true)
	List<Posts> findSlangPosts();

    @Modifying
    @Query(value = "update posts p \r\n"
    		+ "set status=(select id from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '게시글상태') and sc_nm = '블라인드(비속어)') \r\n"
    		+ "where status=0 and\r\n"
    		+ "(p.content similar to (select string_agg('%'||sc_nm||'%', '|') from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어')) \r\n"
    		+ "or p.title similar to (select string_agg('%'||sc_nm||'%', '|') from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어')));", nativeQuery = true)
    void filteringAllPost();
    

}
