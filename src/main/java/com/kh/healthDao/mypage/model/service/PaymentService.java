package com.kh.healthDao.mypage.model.service;

import java.util.List;

import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.review.model.vo.Review;

public interface PaymentService {
	// 유저 결제 이력 출력
	List<Payment> mypaymentList(int userNo);
	// 리뷰 작성 후 상태 변경
	int statusModify(Review review);	
	// 환불 리스트에 추가
	int refundInsert(int payNo);

}
