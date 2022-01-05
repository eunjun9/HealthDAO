package com.kh.healthDao.shopping.model.service;

import java.util.List;

import com.kh.healthDao.admin.model.vo.Product;



public interface ShoppingService {
	List<Product> ShoppingList();

	Product NewProductSelect(int productNo);

	List<Product> foodShoppingList();

	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

}
