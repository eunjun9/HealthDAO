package com.kh.healthDao.mypage.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	private int addressNo;		// 배송지번호
	private String receiver;	// 받으실분
	private String address;		// 배송주소
	private String recPhone;	// 연락처
	private String defAddress;	// 기본배송지 여부
	private int userNo;			// 유저번호

}
