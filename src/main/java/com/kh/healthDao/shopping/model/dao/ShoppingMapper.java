package com.kh.healthDao.shopping.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.shopping.model.vo.Shopping;



@Mapper
public interface ShoppingMapper {

	List<Shopping> ShoppingList();

	Shopping ShoppingSelect(int tNo);

	
}
