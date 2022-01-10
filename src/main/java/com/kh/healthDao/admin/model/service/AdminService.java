package com.kh.healthDao.admin.model.service;

import java.util.List;

import com.kh.healthDao.admin.model.vo.Category;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Option;
import com.kh.healthDao.admin.model.vo.Product;

public interface AdminService {
		
	// 상품 등록
	 public int registProduct(Product product);
	 
	// 재고내역
	List<Product> listProductInventory();

	// 재고 수량 입력
	public int pLPopupSu(Product product);

	public int insertproductStock(Product product);

	public int registCategory(Product product);

	public int registOption(Product product);

	// List<Product> listProduct(Product product);




	
	

}
