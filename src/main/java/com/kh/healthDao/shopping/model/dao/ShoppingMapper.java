package com.kh.healthDao.shopping.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.shopping.model.vo.Shopping;



@Mapper
public interface ShoppingMapper {

	List<Product> ShoppingList();

	Product NewProductSelect(int productNo);

	List<Product> foodShoppingList();
	
	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

	Product shoppingSelect(int productNo);
}
