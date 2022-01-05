package com.kh.healthDao.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.mypage.model.vo.Qna;


@Mapper
public interface MypageMapper {

	int qnaInsert(Qna newQna);

	List<Qna> findQnaList();

	int getQnaListCount();

	List<Qna> pagingQnaList(Map<String, Object> pageRow);

	Qna qnaDetail(int qNo);


}
