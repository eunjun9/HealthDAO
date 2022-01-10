package com.kh.healthDao.trainer.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
	
}
