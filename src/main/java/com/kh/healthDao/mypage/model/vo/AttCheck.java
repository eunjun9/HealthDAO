package com.kh.healthDao.mypage.model.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttCheck {

	private int attendanceId;		// 출석체크번호
	private Date attendanceDate;	// 출석체크일자
	private int stringAttendanceDate;
	private int userNo;				// 출석유저번호
	
	

}
