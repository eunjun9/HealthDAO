package com.kh.healthDao.mypage.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.mypage.model.vo.Qna;


@Mapper
public interface MypageMapper {

	int qnaInsert(Qna newQna);


}
