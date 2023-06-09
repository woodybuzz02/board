package com.example.board.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplysRepository extends JpaRepository<Replys, Integer>{

	@Query(value = "SELECT * FROM replys r WHERE post_id = :postId and depth = 0 ORDER BY reply_group ASC, reply_order ASC", nativeQuery = true)
	Page<Replys> findParentReplyByPostId(Pageable pageable, @Param("postId") int postId);
	
	@Query(value = "SELECT * FROM replys r WHERE post_id = :postId and depth = 1 and parent_reply_id = :parentReplyId ORDER BY reply_group ASC, reply_order ASC", nativeQuery = true)
	List<Replys> findChildReplyByPostId(@Param("postId") int postId, @Param("parentReplyId") int parentReplyId );
	
	@Query(value = "SELECT max(r.reply_group) FROM replys r", nativeQuery = true)
	Optional<Integer> findReplyGroup();

	@Query(value = "SELECT max(r.reply_order) FROM replys r WHERE reply_group = :replyGroup and post_id = :postId", nativeQuery = true)
	Optional<Integer> findReplyOrder(@Param("replyGroup") int replyGroup, @Param("postId") int postId);

	@Query(value = "SELECT count(*) FROM replys r WHERE parent_reply_id = :parentReplyId", nativeQuery = true)
	Optional<Integer> countChildList(@Param("parentReplyId") int parentReplyId);
	
	@Modifying
	@Query(value = "DELETE FROM replys r WHERE id = :id", nativeQuery = true)
	void deleteById(@Param("id") int id);
	
	@Modifying
	@Query(value = "update replys r \r\n"
			+ "set status=(select id from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '게시글상태') and sc_nm = '블라인드(비속어)') \r\n"
			+ "where status=0 and\r\n"
			+ "r.content similar to (select string_agg('%'||sc_nm||'%', '|') from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어'));", nativeQuery = true)
	void filteringAllReply();
	
	@Query(value = "select * from replys r \r\n"
			+ "where status=0 and\r\n"
			+ "r.content similar to (select string_agg('%'||sc_nm||'%', '|') from swift_code sc where sc.gc_id = (select gc.id from group_code gc where gc.gc_nm = '비속어'));", nativeQuery = true)
	List<Replys> findSlangReplys();
	
	
}
