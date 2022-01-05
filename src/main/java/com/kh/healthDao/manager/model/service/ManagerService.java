package com.kh.healthDao.manager.model.service;

import java.util.List;

import com.kh.healthDao.manager.model.vo.Qna;

public interface ManagerService{
	
	// 전체 문의글 조회
	List<Qna> listAllQna();
	
	// 문의글 답변
	Qna listQna(int qNo);

	// 답변쓰기
	int managerQAnswer(Qna qna);
	

	



	

}
