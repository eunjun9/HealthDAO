package com.kh.healthDao.admin.model.service;

import com.kh.healthDao.admin.model.vo.Product;


public interface AdminService {
	
	// 상품 등록
	public void registNewProduct(Product newProduct);

	public int RegistProduct(Product product);

}
