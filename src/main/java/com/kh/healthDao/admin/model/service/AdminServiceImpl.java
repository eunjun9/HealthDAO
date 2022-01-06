package com.kh.healthDao.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.dao.AdminMapper;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;

@Service("adminService")
public class AdminServiceImpl implements AdminService, CouponService{

	private final AdminMapper adminMapper; 
	
	@Autowired
	public AdminServiceImpl(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	


	// 상품 등록
	@Override
	public int RegistProduct(Product product) {
		return adminMapper.RegistProduct(product);
	}

	@Override
	public List<Product> listProductInventory() {
		
		return adminMapper.listProductInventory();
	}


	// 재고 수량 입력
	@Override
	public int insertproductStock(Product product) {
		return adminMapper.insertproductStock(product);
	}



	@Override
	public int couponInput(Coupon coupon) {
		return adminMapper.couponInput(coupon);
	}



	@Override
	public Map<String, Object> allCouponList(int page) {
		int listCount = adminMapper.allCouponListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Coupon> couponList = adminMapper.allCouponList(pageRow);
		
		Map<String, Object> coupon = new HashMap<>();
		
		coupon.put("listCount", listCount);
		coupon.put("couponList", couponList);
		coupon.put("pi", pi);
		
		return coupon;
	}
	
	
	
	

}
