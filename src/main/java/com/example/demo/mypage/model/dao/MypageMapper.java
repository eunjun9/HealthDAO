package com.example.demo.mypage.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.mypage.model.vo.Qna;

@Mapper
public interface MypageMapper {

	int qnaInsert(Qna newQna);


}
