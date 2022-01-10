package com.kh.healthDao.mypage.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
	
	private int pointNo;			// 포인트 번호		
	private String pointamount;		// 포인트 금액
	private Date pointIssueDate;	// 발급일
	private Date pointEndDate;		// 종료일
	private int userNo;				// 유저번호
	private int pCategoryNo;		// 카테고리번호
	private String pCategoryName;	// 발급명
	
	

}
