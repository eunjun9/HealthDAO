package com.kh.healthDao.mypage.model.service;

import java.util.Map;

import com.kh.healthDao.review.model.vo.Review;

public interface MyReviewService {

	Map<String, Object> userReviewList(int page, int userNo);

	Review reviewDetail(int reviewNo);

	int reviewModify(Review review);

	int reviewInsert(Review review);

}
