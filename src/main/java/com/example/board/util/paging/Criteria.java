package com.example.board.util.paging;

public class Criteria {
	
	private int page; // 현재 페이지 번호
	
	private int perPageNum; // 한 페이지당 보여줄 게시글의 갯수
	
	public int getPageStart() { // 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
		return (this.page-1)*perPageNum;
	}
	
	public Criteria() { // 기본 생성자를 통해 기본 값 세팅!
		this.page = 1;
		this.perPageNum = 20;
	}
	
	public void setCriteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) { // 잘못된 값들이 세팅되지 않게 적절하게 set 메서드 세팅
		if(page <= 0) { // 페이지가 음수값이 되지 않게 설정
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
}
