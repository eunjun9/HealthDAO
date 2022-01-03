package com.kh.healthDao.shopping.model.service;

import java.util.List;

import com.kh.healthDao.shopping.model.vo.Shopping;


public interface ShoppingService {
	List<Shopping> ShoppingList();

	Shopping ShoppingSelect(int tNo);

}
