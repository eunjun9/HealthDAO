package com.kh.healthDao.admin.model.service;

import java.util.Map;

import com.kh.healthDao.admin.model.vo.Product;

public interface AdminService {
	
	
	
	// 상품 등록
	
	// 재고내역
	Map<String, Object> inventoryPaging(int page);

	public Product pLPopupSu(Product product);

	public int stockPlus(Product product);

	public int insertproductStock(Product product);

	public int registProduct(Product product);

	public int registOption(Product product);


	
	

}
