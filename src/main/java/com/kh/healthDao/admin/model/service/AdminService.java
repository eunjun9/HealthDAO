package com.kh.healthDao.admin.model.service;

import com.kh.healthDao.admin.model.vo.Product;


public interface AdminService {
	
	
	public void registNewProduct(Product newProduct);
	
	// 상품 등록
	public int RegistProduct(Product product);

}
