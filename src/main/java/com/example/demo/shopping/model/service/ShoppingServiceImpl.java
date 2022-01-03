package com.example.demo.shopping.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.shopping.model.dao.ShoppingMapper;
import com.example.demo.shopping.model.vo.Shopping;


@Service("shoppingService")
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
