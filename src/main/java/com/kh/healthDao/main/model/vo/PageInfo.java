package com.kh.healthDao.main.model.vo;

// 안되서 일단 주석처리
public class PageInfo {
	
	private int page;		// 요청 페이지
	private int listCount;	// 전체 게시글
	private int pageLimit;	// 페이지 목록 수
	private int listLimit;	// 한 페이지에 보여지는 목록
	private int maxPage;	// 마지막 페이지
	private int startPage;	// 목록 시작 페이지
	private int endPage;	// 목록 마지막 페이지
	
	public PageInfo(int page, int listCount, int pageLimit, int listLimit) {
		this.page = page;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.listLimit = listLimit;
		
		this.maxPage = (int)Math.ceil((double)listCount/listLimit);
		this.startPage = (page-1) / pageLimit * pageLimit + 1;
		this.endPage = startPage + pageLimit - 1;
		
		if(this.maxPage < this.endPage) {
			this.endPage = this.maxPage;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getListLimit() {
		return listLimit;
	}

	public void setListLimit(int listLimit) {
		this.listLimit = listLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", listCount=" + listCount + ", pageLimit=" + pageLimit + ", listLimit="
				+ listLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
