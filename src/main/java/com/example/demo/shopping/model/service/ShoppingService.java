package com.example.demo.shopping.model.service;

import java.util.List;

import com.example.demo.shopping.model.vo.Shopping;

public interface ShoppingService {
	List<Shopping> ShoppingList();

	Shopping ShoppingSelect(int tNo);

}
