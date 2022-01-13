package com.kh.healthDao.manager.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refund {
	
	/*
	REFUND_NO	NUMBER	No		1	환불번호
	REFUND_DATE	DATE	No	"SYSDATE	"	2	환불신청일
	REFUND_STATUS	VARCHAR2(20 BYTE)	No	"'N'	"	3	처리현황
	PAY_NO	NUMBER	No		4	주문번호
	USER_ID	VARCHAR2(50 BYTE)	No		2	아이디
	PAY_DATE	DATE	No	"SYSDATE	"	2	결제일
	QUANTITY	NUMBER	No		3	수량
	PRODUCT_PRICE	VARCHAR2(100 BYTE)	No		4	가격
	*/
	
	private int refundNo;			// 환불번호
	private Date refundDate;		// 환불일
	private String refundStatus;	// 처리현황
	private int payNo;				// 주문번호
	private String userId;			// 아이디
	private Date payDate;			// 결제일
	private int quantity;			// 수량
	private int productPrice;	// 가격
	
	

}
