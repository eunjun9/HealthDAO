package com.kh.healthDao.shopping.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.mypage.model.vo.Qna;
import com.kh.healthDao.shopping.model.dao.ShoppingMapper;


@Service("ShoppingService")
public class ShoppingServiceImpl implements ShoppingService{
	
	private final ShoppingMapper shoppingMapper;

	@Autowired
	public ShoppingServiceImpl(ShoppingMapper shoppingMapper) {
		this.shoppingMapper = shoppingMapper;
	}
	
	@Override
	public List<Product> ShoppingList() {
		return shoppingMapper.ShoppingList();
	}

	
	@Override
	public List<Product> foodShoppingList() {
		return shoppingMapper.foodShoppingList();
	}

	@Override
	public List<Product> beverageShoppingList() {
		return shoppingMapper.beverageShoppingList();
	}

	@Override
	public List<Product> goodsShoppingList() {
		return shoppingMapper.goodsShoppingList();
	}

	@Override
	public Product shoppingSelect(int productNo) {
		return shoppingMapper.shoppingSelect(productNo);
	}

	@Override
	public Map<String, Object> shoppingList(int page) {
		int listCount = shoppingMapper.shoppingListCount();
		Paging pi = new Paging(page, listCount, 5, 12);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Product> shoppingList = shoppingMapper.shoppingList(pageRow);
		
		Map<String, Object> newProduct = new HashMap<>();
		
		newProduct.put("listCount", listCount);
		newProduct.put("shoppingList", shoppingList);
		newProduct.put("pi", pi);
		
		return newProduct;
	}

	@Override
	public Map<String, Object> foodShoppingList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product shoppingDetail(int productNo) {
		return shoppingMapper.shoppingDetail(productNo);
	}

	

	


}
