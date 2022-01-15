package com.kh.healthDao.shopping.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.shopping.model.dao.ShoppingMapper;


@Service("ShoppingService")
public class ShoppingServiceImpl implements ShoppingService{
	
	private final ShoppingMapper shoppingMapper;

	@Autowired
	public ShoppingServiceImpl(ShoppingMapper shoppingMapper) {
		this.shoppingMapper = shoppingMapper;
	}

	@Override
	public List<Product> recoRankList() {
		return shoppingMapper.recoRankList();
	}

	@Override
	public List<Product> recentList(int[] addList) {
		List<Product> pdtList = new ArrayList<>();
		Product pdt = new Product();		
		//System.out.println("length : "+addList.length);

		for(int i=0; i<addList.length; i++) {
			pdt = shoppingMapper.recentList(addList[i]);
			pdtList.add(pdt);
		}
		// System.out.println("리스트 : " + pdtList);
		return pdtList;
	}
	
	@Override
	public Map<String, Object> pdtList() {
		int listCount = shoppingMapper.pdtListCount();
		List<Product> pdtList = shoppingMapper.pdtList();
		List<Product> recoList = shoppingMapper.recoList();
		int recoCount = shoppingMapper.recoListCount();
		
		Map<String, Object> pdtMap = new HashMap<>();
		pdtMap.put("listCount", listCount);
		pdtMap.put("pdtList", pdtList);
		pdtMap.put("recoList", recoList);
		pdtMap.put("recoCount", recoCount);
		
		return pdtMap;
	}
	
	// 찜한 상품
	public Map<String, Object> wishList(int userNo) {
		int listCount = shoppingMapper.wishListCount(userNo);
		List<Product> wishList = shoppingMapper.wishList(userNo);
		
		Map<String, Object> wishMap = new HashMap<>();
		wishMap.put("wishListCount", listCount);
		wishMap.put("wishList", wishList);
		
		return wishMap;
	}

	@Override
	public Product detailPdt(int productNo) {
		return shoppingMapper.detailPdt(productNo);
	}

	
	/* 추천 상품 */
	@Override
	public int insertReco(int productNo, int productRank) {
		return shoppingMapper.insertReco(productNo, productRank);
	}

	@Override
	public Product selectReco(int productNo) {
		return shoppingMapper.selectReco(productNo);
	}

	@Override
	public int modifyReco(int productNo, int productRank) {
		return shoppingMapper.modifyReco(productNo, productRank);
	}

	@Override
	public int deleteReco(int productNo) {
		return shoppingMapper.deleteReco(productNo);
	}
	
	/* 찜한 상품 */
	@Override
	public Product wishChk(int productNo, int userNo) {
		return shoppingMapper.wishChk(productNo, userNo);
	}
	@Override
	public int insertWish(int productNo, int userNo) {
		return shoppingMapper.insertWish(productNo, userNo);
	}
	@Override
	public int deleteWish(int productNo, int userNo) {
		return shoppingMapper.deleteWish(productNo, userNo);
	}
  
	/* 상품 음료 */
	@Override
	public List<Product> beverageShoppingList() {
		return shoppingMapper.beverageShoppingList();
	}

	/* 상품 운동기구 */
	@Override
	public List<Product> goodsShoppingList() {
		return shoppingMapper.goodsShoppingList();
	}

	/* 상품 상세페이지 */
	@Override
	public Product shoppingDetail(int productNo) {
		return shoppingMapper.shoppingDetail(productNo);
	}

	/*신상품*/
	@Override
	public Map<String, Object> ShoppingList(int page) {
		int listCount = shoppingMapper.shoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.shoppingList(pageRow);
		
		Map<String, Object> newProductMap = new HashMap<>();
		
		newProductMap.put("listCount", listCount);
		newProductMap.put("shoppingList", shoppingList);
		newProductMap.put("pi", pi);
		
		return newProductMap;
	}

	/*식품*/
	@Override
	public Map<String, Object> foodShoppingList(int page) {
		int listCount = shoppingMapper.foodShoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.foodShoppingList(pageRow);
		
		Map<String, Object> foodProductMap = new HashMap<>();
		
		foodProductMap.put("listCount", listCount);
		foodProductMap.put("shoppingList", shoppingList);
		foodProductMap.put("pi", pi);
		
		return foodProductMap;
	}

	/* 상품 음료 페이지 */
	@Override
	public Map<String, Object> beverageShoppingList(int page) {
		int listCount = shoppingMapper.beverageShoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.beverageShoppingList(pageRow);
		
		Map<String, Object> beverageProductMap = new HashMap<>();
		
		beverageProductMap.put("listCount", listCount);
		beverageProductMap.put("shoppingList", shoppingList);
		beverageProductMap.put("pi", pi);
		
		return beverageProductMap; 
		
	}

	/* 상품 운동기구 */
	@Override
	public Map<String, Object> goodsShoppingList(int page) {
		int listCount = shoppingMapper.goodsShoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.goodsShoppingList(pageRow);
		
		Map<String, Object> goodsProductMap = new HashMap<>();
		
		goodsProductMap.put("listCount", listCount);
		goodsProductMap.put("shoppingList", shoppingList);
		goodsProductMap.put("pi", pi);
		
		return goodsProductMap; 
	}

	@Override
	public Product NewProductSelect(int productNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* 상품 주문 */
	@Override
	public Product shoppingPayment(int productNo) {
		return shoppingMapper.shoppingPayment(productNo);
	}
}
