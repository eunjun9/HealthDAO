package com.kh.healthDao.admin.model.vo;

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
	private String productView;		// 조회수
	private int productStock;	// 재고량
	private String categoryId;		// 카테고리ID
	private int pMembership;		// 적립혜택
	private String productOption;	//임시 옵션번호
	
	
	
	/* + 추가할 것
	 * MEMBER 조인 -> 트레이너 이름, 트레이너 전화번호
	 * REVIEW 조인 -> 별점, 리뷰 수
	 * TRAINER_FILE 조인 -> AT_FILE 조인 -> 트레이너 사진 1장, 헬스장 사진 2장
	 * 
	 * */
	

	private int productRank;		// 순번
	
}
