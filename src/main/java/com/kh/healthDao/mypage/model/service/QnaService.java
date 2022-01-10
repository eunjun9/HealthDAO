package com.kh.healthDao.mypage.model.service;

import java.util.List;
import java.util.Map;

import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;

public interface QnaService {
	// 문의하기
	int qnaInsert(Qna newQna);
	// (사용자) 문의 수정하기
	int qnaModify(Qna modifyQna);	
	// (사용자) 문의 리스트
	Map<String, Object> findQnaList(int page, int userNo);
	// 문의 디테일
	Qna qnaDetail(int qNo);
	
	// 포인트 내역 페이징
	Map<String, Object> pointList(int page);
	
	// 출석체크
	int attendanceCheck(AttCheck att);
	
}
