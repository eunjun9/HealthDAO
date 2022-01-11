package com.kh.healthDao.admin.model.service;

import java.util.Map;

import com.kh.healthDao.admin.model.vo.Coupon;

public interface CouponService {
	
	// 쿠폰 등록
	int couponInput(Coupon coupon);

	// 쿠폰 리스트 
	Map<String, Object> allCouponList(int page);

}
