package com.kh.healthDao.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.mypage.model.vo.Qna;



@Mapper
public interface AdminMapper {

	

	public void register(Product product);

	// 상품 등록
	public int RegistProduct(Product product);

	// 상품 재고
	public List<Product> listProductInventory();

	public String insertproductStock(Product product);

	public int getinventoryCount();

	public List<Product> listProductInventory(Map<String, Object> pageRow);

	public Product pLPopupSu(Product product);

	// 재고 추가
	public int stockPlus(Product product);










	
}
