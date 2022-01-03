package com.kh.healthDao.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberGrade {
	
	private int UserNo;			// 유저번호
	private String userGrade;	// 등급명

}
