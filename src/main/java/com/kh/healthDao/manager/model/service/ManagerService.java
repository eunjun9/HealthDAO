package com.kh.healthDao.manager.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.manager.model.vo.Qna;
import com.kh.healthDao.manager.model.vo.Refund;

public interface ManagerService{
	
	// 전체 문의글 조회
	// List<Qna> listAllQna();
	
	// 문의글 답변
	Qna listQna(int qNo);

	// 답변쓰기
	int managerQAnswer(Qna qna);

	// 페이징된 전체 문의글
	Map<String, Object> InquiryPaging(int page);

	// 정산내역
	Map<String, Object> calculateList(int page);
	// 정산엑셀 다운받기
	List<Payment> excelList();

	// 환불내역
	Map<String, Object> refundListPaging(int page);

	// 환불완료버튼
	int managerRefundOk(Refund refund);

	
	

	



	

}
