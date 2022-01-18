package com.kh.healthDao.mypage.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roulette {
	
	/*
	ROUL_NO	NUMBER
	ROUL_DATE	DATE
	USER_NO	NUMBER
	*/
	
	private int roulNo;	 	// 룰렛 no
	private Date roulDate;	// insert된 날짜
	private int userNo;		// 사용자 no

}
