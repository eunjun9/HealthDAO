package com.kh.healthDao.shopping.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Shopping> ShoppingList() {
		return shoppingMapper.ShoppingList();
	}

	@Override
	public Shopping ShoppingSelect(int tNo) {
		return shoppingMapper.ShoppingSelect(tNo);
	}


}
