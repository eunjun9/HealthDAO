package com.kh.healthDao.review.model.vo;

import java.util.Date;

import com.kh.healthDao.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	
	private int reviewNo;		// 리뷰번호
	private String category;	// 구분
	private int star;			// 별점
	private String rContent;	// 리뷰내용
	private Date rDate;			// 작성일자
	private Date modifyDate;	// 수정일자
	private int payNo;			// 주문번호
	private int userNo;			// 유저번호
	private String productName; // 상품명
	
	private Member member;		// 멤버테이블
	
}
