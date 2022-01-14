package com.kh.healthDao.shopping.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Product;


@Mapper
public interface ShoppingMapper {
	List<Product> recoRankList();
	List<Product> pdtList();
	int pdtListCount();
	Product detailPdt(int productNo);
	int insertReco(int productNo, int productRank);
	List<Product> recoList();
	Product selectReco(int productNo);
	int modifyReco(int productNo, int productRank);
	int deleteReco(int productNo);
	int recoListCount();
	Product recentList(int productNo);
	int wishListCount(int userNo);
	List<Product> wishList(int userNo);

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

	Product shoppingPayment(int productNo);
}
