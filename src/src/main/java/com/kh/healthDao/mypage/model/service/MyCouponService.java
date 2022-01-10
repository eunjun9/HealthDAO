package com.kh.healthDao.mypage.model.service;

import java.util.List;

import com.kh.healthDao.admin.model.vo.Coupon;

public interface MyCouponService {

	List<Coupon> couponEventList();

	int myCouponInsert(int pNo, int userNo);

	List<Coupon> myCouponList(int userNo);


}
