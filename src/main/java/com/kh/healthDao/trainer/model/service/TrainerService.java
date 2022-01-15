package com.kh.healthDao.trainer.model.service;

import java.util.List;

import com.kh.healthDao.review.model.vo.Review;
import com.kh.healthDao.trainer.model.vo.PtOrder;
import com.kh.healthDao.trainer.model.vo.Trainer;


public interface TrainerService {
	List<Trainer> trainerList(String searchTrainer);

	Trainer trainerSelect(int tNo);

	int trainerModify(Trainer trainer, String originFile1, String originFile2, String changeFile1, String changeFile2);

	List<PtOrder> trainerOrderList(int userNo);

	List<Review> trainerReviewList(int tNo);

	int trainerInsert(Trainer trainer, String originFile1, String originFile2, String changeFile1, String changeFile2);

	int sumPtOrder();

	int sumTrainer();

	int sumReview();

	int trainerModify2(Trainer trainer, String originFile1, String changeFile1);

	int trainerModify3(Trainer trainer, String originFile2, String changeFile2);


}
