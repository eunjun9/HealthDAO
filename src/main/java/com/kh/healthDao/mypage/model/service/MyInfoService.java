package com.kh.healthDao.mypage.model.service;

import java.util.List;

import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.vo.Address;

public interface MyInfoService {

	/* 내 정보 수정 */
	Member myInfoView(int userNo);
	
	int myInfoModify(Member member);

	/* 배송지 등록 */
	List<Address> deliView(int userNo);
	
	int insertDeli(Address address);

	Address selectDeli(int addressNo);



}
