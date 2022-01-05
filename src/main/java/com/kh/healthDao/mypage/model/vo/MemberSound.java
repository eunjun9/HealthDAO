package com.kh.healthDao.mypage.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSound {
	private int cNo;
	private String cDept;
	private String cContent;
	private Date cDate;
	private int userNo;
	private String userId;
	private String imgLocation;
	private String imgName;
	private String cStatus;
	
}
