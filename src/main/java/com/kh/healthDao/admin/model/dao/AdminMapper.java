package com.kh.healthDao.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Product;



@Mapper
public interface AdminMapper {


	// 상품 등록
	public int RegistProduct(Product product);


	// 상품 재고
	public List<Product> listProductInventory();

	// 재고 수량 입력
	public int insertproductStock(Product product);


	public int couponInput(Coupon coupon);


	public int allCouponListCount();


	public List<Coupon> allCouponList(Map<String, Object> pageRow);

	
}
