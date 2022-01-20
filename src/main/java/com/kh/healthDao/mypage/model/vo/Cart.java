package com.kh.healthDao.mypage.model.vo;

import com.kh.healthDao.admin.model.vo.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	private int cartNo;			// 장바구니 번호
	private int cartStock;		// 제품 수량
	private int userNo;			// 사용자
	private int productNo;		// 상품번호
	
	private Product product;
}
