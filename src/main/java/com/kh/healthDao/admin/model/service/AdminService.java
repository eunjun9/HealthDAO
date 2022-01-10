package com.kh.healthDao.admin.model.service;

import java.util.Map;

import com.kh.healthDao.admin.model.vo.Product;

public interface AdminService {
	
	
	public void registNewProduct(Product newProduct);
	
	// 상품 등록
	public int RegistProduct(Product product);
	
	// 재고내역
	Map<String, Object> inventoryPaging(int page);

	public Product pLPopupSu(Product product);

	public int stockPlus(Product product);






	



	
	

}
