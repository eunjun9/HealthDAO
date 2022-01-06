package com.kh.healthDao.shopping.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.admin.model.vo.Product;



public interface ShoppingService {
	List<Product> ShoppingList();

	List<Product> foodShoppingList();

	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

	Product shoppingSelect(int productNo);

	Map<String, Object> shoppingList(int page);

	Map<String, Object> foodShoppingList(int page);

	Product shoppingDetail(int productNo);

}
