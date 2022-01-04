package com.kh.healthDao.mypage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.mypage.model.dao.MypageMapper;
import com.kh.healthDao.mypage.model.vo.Qna;


@Service("mypageService")
public class mypageServiceImpl implements qnaService{
	
	private final MypageMapper mypageMapper; 
	
	@Autowired
	public mypageServiceImpl(MypageMapper mypageMapper) {
		this.mypageMapper = mypageMapper;
	}
	
	@Override
	public int qnaInsert(Qna newQna) {
		return mypageMapper.qnaInsert(newQna);
	}

	@Override
	public int qnaModify(Qna modifyQna) {
		return 0;
	}

}