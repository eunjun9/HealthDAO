package com.kh.healthDao.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRole {
	
	private int memberNo;		// 유저번호
	private int authorityCode;	// 권한코드
	
	/* 권한 코드별 가지는 권한을 나타냄 - TBL_AUTHORITY */
	private Authority authority;

}
