package com.kh.healthDao.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Authority {
	
	/* 하나의 AUTHORITY 정보 - 어떤 코드가 어떤 권한명을 가지고 있는지 */
	private int code;		// 권한코드
	private String name;	// 권한명
	private String desc;	// 권한설명

}
