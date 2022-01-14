package com.kh.healthDao.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDetail {
	
	private int productNo;			// 상품 번호
	private int quantity;			// 수량
	private int payNo;				// 주문 번호
	
	private Product product;		// 상품 테이블

}
