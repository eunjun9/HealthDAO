package com.kh.healthDao.trainer.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.review.model.vo.Review;
import com.kh.healthDao.trainer.model.vo.PtOrder;
import com.kh.healthDao.trainer.model.vo.Trainer;


@Mapper
public interface TrainerMapper {

	List<Trainer> trainerList(String searchTrainer);

	Trainer trainerSelect(int tNo);

	int trainerModify(Trainer trainer);

	List<PtOrder> trainerOrderList(int userNo);

	List<Review> trainerReviewList(int tNo);

	int trainerInsert(Trainer trainer);

	int sumPtOrder();

	int sumTrainer();

	int sumReview();

	int trainerFileInsert(Map<String, Object> map);

	int trainerFileInsertDB(Trainer trainer);

	int centerFileInsert(Map<String, Object> map);

	int centerFileInsertDB(Trainer trainer);

	int trainerFileModify(Map<String, Object> map);

	int centerFileModify(Map<String, Object> map);

	Review rvStatus(Map<String, Object> map);

	int trainerReviewInsert(Review review);

	Review reviewStatus(Review review);

	Member mOrderSelect(Member m);

	int trainerPayInsert(Payment payment);

	int trainerPtOrderInsert(Payment payment);

}
