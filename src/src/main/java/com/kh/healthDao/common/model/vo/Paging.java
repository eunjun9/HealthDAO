package com.kh.healthDao.common.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paging {
	private int page;			// 요청하는 페이지
	private int listCount;		// 전체 게시글 수
	private int pageLimit;		// 하단에 보여질 페이지 목록 수
	private int boardLimit;		// 한 페이지에 보여질 게시글 최대 
	private int maxPage;		// 전체 페이지에서 가장 마지막 페이지
	private int startPage;		// 하단에 보여질 페이지 목록 시작 값
	private int endPage;		// 하단에 보여질 페이지 목록 끝 값
	
	public Paging(int page, int listCount, int pageLimit, int boardLimit) {
		this.page = page;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit =  boardLimit;
		
		this.maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		this.startPage = (page-1) / pageLimit * pageLimit + 1;
		
		this.endPage = startPage + pageLimit - 1;
		
		if(this.maxPage < this.endPage) {
			this.endPage = this.maxPage;
		}
	}
	
}
