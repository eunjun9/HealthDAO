package com.kh.healthDao.mypage.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.vo.Address;

public interface MyInfoService {

	/* 내 정보 수정 */
	Member myInfoView(int userNo);
	
	int myInfoModify(Member member);
	
	int myInfoDelete(int userNo, HttpSession session);

	/* 배송지 등록 */
	List<Address> deliView(int userNo);
	
	int insertDeli(Address address);

	Address selectDeli(int addressNo);

	int updateDeil(int addressNo);
	
	int deleteDeil(int addressNo);
	
	void defAddRemove(int userNo);
	
	int defAddDeli(int addressNo);

	/* 회원 탈퇴 */
	void unregister(Member member, HttpSession session);

	int passCheck(Member member);


}
