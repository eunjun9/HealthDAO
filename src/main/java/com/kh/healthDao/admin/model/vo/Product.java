package com.kh.healthDao.admin.model.vo;

import java.util.List;

import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.review.model.vo.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private int productNo;			// 상품번호
	private String productBrand;	// 상품브랜드
	private String productTitle;	// 상품 제목
	private String productPrice;	// 상품 가격
	private String productBenefit;	// 추가 혜택
	private String productNotice;	// 공지 사항
	private String productInfo;		// 상품 정보
	private int productView;		// 조회수
	private int productStock;		// 재고량
	private int pMembership;		// 적립혜택
	private String productOption;
	private String categoryId;  // 카테고리ID
	
	private List<Option> option;	// 옵션 테이블
	private Category category;		// 카테고리 테이블
	private PayDetail payDetail;	// 상품 디테일(수량) 테이블
	

	private List<Review> reviewList;
	private int productRank;		// 순번
	private Member member;			// userNo 가져올 때 필요
	
	private String file_path;		// 파일경로
	private String change_file1;	// 썸네일 파일명
	private String change_file2;	// 상품 정보 파일명
	private int f_productNo;		// 상품 파일 번호
	private int f_productInfoNo;	// 상품 정보 파일 번호
	
	private int quantity; // 수량;
	
	private float avgStar;			// 리뷰 평균
	private int sumReview;			// 리뷰 개수
}
