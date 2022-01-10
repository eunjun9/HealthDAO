package com.kh.healthDao.admin.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
	private int nNo;				// 게시물번호
	private String nTitle;			// 공지사항 제목
	private String nContent;		// 내용
	private Date nDate;				// 작성일
	private Date modifyDate;		// 수정일
	private String nStatus;			// 상태
	private int nView;				// 조회수
	private int userNo;				// 작성자
}
