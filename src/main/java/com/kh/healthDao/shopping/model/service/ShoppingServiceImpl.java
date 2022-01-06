package com.kh.healthDao.shopping.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.shopping.model.dao.ShoppingMapper;
import com.kh.healthDao.shopping.model.vo.Shopping;


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
	public Product NewProductSelect(int productNo) {
		return shoppingMapper.NewProductSelect(productNo);
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

	


}
