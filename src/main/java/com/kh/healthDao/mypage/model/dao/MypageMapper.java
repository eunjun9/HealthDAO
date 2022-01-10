package com.kh.healthDao.mypage.model.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;
import com.kh.healthDao.review.model.vo.Review;


@Mapper
public interface MypageMapper {

	int qnaInsert(Qna newQna);

	List<Qna> findQnaList(Map<String, Object> pageRow);

	int getQnaListCount(int userNo);

	Qna qnaDetail(int qNo);

	int qnaModify(Qna modifyQna);

	List<Coupon> couponEventList();

	List<Point> pointList();


	int attendanceCheck(AttCheck att);
	int myCouponInsert(Map<String, Object> map);

	List<Coupon> myCouponList(int userNo);

	int userReviewListCount(int userNo);

	List<Qna> userReviewList(Map<String, Object> pageRow);

	Review reviewDetail(int reviewNo);

	int reviewModify(Review review);



}
