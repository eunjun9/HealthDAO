package com.kh.healthDao.shopping.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.mypage.model.vo.Qna;



@Mapper
public interface ShoppingMapper {

	List<Product> ShoppingList();

	Product NewProductSelect(int productNo);

	List<Product> foodShoppingList();
	
	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

	Product shoppingSelect(int productNo);

	Product shoppingDetail(int productNo);	
	
	int shoppingListCount();

	List<Product> shoppingList(Map<String, Object> pageRow);

}
