package com.kh.healthDao.mypage.model.service;

import java.util.List;

import com.kh.healthDao.mypage.model.vo.Cart;

public interface CartService {
	// 장바구니 등록
	int cartInsert(Cart cartinfo);
	// 장바구니 리스트
	List<Cart> cartList(int userNo);
	// 장바구니에서 수량 변경
	int cartStock(int cartNo, String upDown);
	// 장바구니 삭제
	int cartDelete(int cartNo);
	// 장바구니 모두 삭제
	int cartAllDelete(int userNo);
	

}
