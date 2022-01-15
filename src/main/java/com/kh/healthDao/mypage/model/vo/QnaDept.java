package com.kh.healthDao.mypage.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaDept {
	
	/*
	Q_DEPT_CODE	VARCHAR2(30 BYTE)	No		1	문의유형코드
	Q_DEPT	VARCHAR2(50 BYTE)	No		2	문의유형
	*/
	
	private String qDeptCode;		// 문의유형코드
	private String qDept;			// 문의유형
	

}
