package com.example.demo.mypage.model.service;

import com.example.demo.mypage.model.vo.Qna;

public interface qnaService {
	// 문의하기
	int qnaInsert(Qna newQna);
	// (사용자) 문의 수정하기
	int qnaModify(Qna modifyQna);
	
}
