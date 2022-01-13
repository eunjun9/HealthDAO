package com.kh.healthDao.mypage.model.service;

import java.util.List;

import com.kh.healthDao.mypage.model.vo.Cart;

public interface CartService {
	// 장바구니 등록
	int cartInsert(Cart cartinfo);
	// 장바구니 리스트
	List<Cart> cartList(int userNo);	

}
