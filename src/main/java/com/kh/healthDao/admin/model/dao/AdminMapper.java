package com.kh.healthDao.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Product;



@Mapper
public interface AdminMapper {

	
	public void register(Product product);

	// 상품 등록
	public int RegistProduct(Product product);

	// 상품 재고
	public List<Product> listProductInventory();

	
}
