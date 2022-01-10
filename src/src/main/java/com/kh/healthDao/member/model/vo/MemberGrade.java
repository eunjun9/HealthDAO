package com.kh.healthDao.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberGrade {
	
	private int userNo;			// 유저번호
	private String userGrade;	// 등급명

}
