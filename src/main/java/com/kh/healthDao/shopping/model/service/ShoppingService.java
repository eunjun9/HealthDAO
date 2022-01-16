package com.kh.healthDao.shopping.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.shopping.model.vo.Shopping;



public interface ShoppingService {
	/* 쇼핑리스트 */
	Map<String, Object> ShoppingList(int page);

	/* 식품 페이징 */
	Map<String, Object> foodShoppingList(int page);

	/* 음료 페이징 */
	Map<String, Object> beverageShoppingList(int page);

	/* 운동기구 페이징 */
	Map<String, Object> goodsShoppingList(int page);

	/* 신상품 */
	Product NewProductSelect(int productNo);

	List<Product> beverageShoppingList();

	List<Product> goodsShoppingList();

	/* 상품 상세페이지 */
	Product shoppingDetail(int productNo);

	/* 상품 주문페이지 */
	Product shoppingPayment(int productNo);

	Map<String, Object> pdtList();

	Product detailPdt(int productNo);

	int insertReco(int productNo, int productRank);

	Product selectReco(int productNo);

	int modifyReco(int productNo, int productRank);

	int deleteReco(int productNo);

	List<Product> recoRankList();

	List<Product> recentList(int[] addList);

	Map<String, Object> wishList(int userNo);

	Product wishChk(int productNo, int userNo);
	
	int insertWish(int productNo, int userNo);

	int deleteWish(int productNo, int userNo);

	List likeList(int userNo);

}
