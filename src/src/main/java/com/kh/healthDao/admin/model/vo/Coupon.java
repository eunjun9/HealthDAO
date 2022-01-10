package com.kh.healthDao.admin.model.vo;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
	private int pNo;				// 쿠폰번호
	private String pName;			// 쿠폰이름
	private int pSale;				// 할인금액
	private Date startDate;			// 쿠폰사용가능 시작일
	private Date endDate;			// 쿠폰사용가능 끝일
}
