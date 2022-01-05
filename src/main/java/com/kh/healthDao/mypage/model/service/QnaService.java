package com.kh.healthDao.mypage.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.mypage.model.vo.Qna;

public interface QnaService {
	// 문의하기
	int qnaInsert(Qna newQna);
	// (사용자) 문의 수정하기
	int qnaModify(Qna modifyQna);	
	// (사용자) 문의 리스트
	List<Qna> findQnaList();
	// 문의리스트 페이징
	Map<String, Object> pagingQnaList(int page);
	// 문의 디테일
	Qna qnaDetail(int qNo);
	
}
