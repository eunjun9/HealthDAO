package com.kh.healthDao.admin.model.service;

import java.util.List;

import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Product;


public interface AdminService {
	
	
	// 상품 등록
	public int RegistProduct(Product product);

	// 재고 수량 입력
	public int insertproductStock(Product product);

	List<Product> listProductInventory();


	
	

}
