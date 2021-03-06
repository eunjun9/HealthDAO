package com.kh.healthDao.manager.model.vo;


import java.util.Date;
import java.util.List;

import com.kh.healthDao.admin.model.vo.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	
	private int payNo; 					// 결제번호
	private Date payDate;				// 결제일
	private String payMethod;			// 결제수단
	private String ordererPhone;		// 주문자 연락처
	private String ordererEmail;		// 주문자 이메일
	private String orderStatus;			// 배송상태
	private int userNo;					// 주문자번호

	private int ProductNo;				// 상품번호
	private int quantity;				// 재고량

	private String productBrand;		// 브랜드
	private String productTitle;		// 상품명

	private int productPrice;		// 상품가격


	private String productOption;		// 상품 옵션
	
	private List<Product> productList;	// 상품리스트

	private int totalPrice;				// 총 합계 금액
	
}










