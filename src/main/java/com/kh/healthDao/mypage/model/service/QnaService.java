package com.kh.healthDao.mypage.model.service;

import java.util.List;

import com.kh.healthDao.mypage.model.vo.Qna;

public interface QnaService {
	// 문의하기
	int qnaInsert(Qna newQna);
	// (사용자) 문의 수정하기
	int qnaModify(Qna modifyQna);	
	// (사용자) 문의 리스트
	List<Qna> findQnaList();
	
}