package com.kh.healthDao.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
	
	private int optionNo;			// 옵션 번호
	private String productOption;	// 상품 옵션
	private int productNo;			// 상품 번호
	
	private Product product;		// 상품 테이블

}
