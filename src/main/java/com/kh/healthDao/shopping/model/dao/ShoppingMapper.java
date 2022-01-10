package com.kh.healthDao.shopping.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Product;


@Mapper
public interface ShoppingMapper {

	List<Product> ShoppingList();
	
	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

	Product shoppingDetail(int productNo);

	List<Product> shoppingList(Map<String, Object> pageRow);
	
	int shoppingListCount();

	List<Product> foodShoppingList(Map<String, Object> pageRow);

	int foodShoppingListCount();

	List<Product> beverageShoppingList(Map<String, Object> pageRow);

	int beverageShoppingListCount();

	List<Product> goodsShoppingList(Map<String, Object> pageRow);

	int goodsShoppingListCount();
	
}
