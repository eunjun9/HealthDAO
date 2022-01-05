package com.kh.healthDao.trainer.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PtOrder {
	
	private int ptNo;		// PT신청번호
	private Date ptDate;	// PT신청일
	private int userId;		// 유저번호
	private int payNo;		// 결제번호
	private int tNo;		// 트레이너번호
	
	private Trainer trainer;	// 트레이너 테이블
}
