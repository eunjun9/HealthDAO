package com.kh.healthDao.shopping.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.admin.model.vo.Product;



public interface ShoppingService {
	
	Map<String, Object> ShoppingList(int page);

	Map<String, Object> foodShoppingList(int page);

	Map<String, Object> beverageShoppingList(int page);

	Map<String, Object> goodsShoppingList(int page);

	Product NewProductSelect(int productNo);

	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

	Product shoppingDetail(int productNo);

	Product shoppingPayment(int productNo);

	Map<String, Object> pdtList();

}
