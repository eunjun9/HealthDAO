package com.kh.healthDao.trainer.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.review.model.vo.Review;
import com.kh.healthDao.trainer.model.dao.TrainerMapper;
import com.kh.healthDao.trainer.model.vo.PtOrder;
import com.kh.healthDao.trainer.model.vo.Trainer;


@Service("trainerService")
public class TrainerServiceImpl implements TrainerService{
	
	private final TrainerMapper trainerMapper;

	@Autowired
	public TrainerServiceImpl(TrainerMapper trainerMapper) {
		this.trainerMapper = trainerMapper;
	}
	
	@Override
	public List<Trainer> trainerList(String searchTrainer) {
		return trainerMapper.trainerList(searchTrainer);
	}

	@Override
	public Trainer trainerSelect(int tNo) {
		return trainerMapper.trainerSelect(tNo);
	}

	@Override
	public int trainerModify(Trainer trainer) {
		return trainerMapper.trainerModify(trainer);
	}

	@Override
	public List<PtOrder> trainerOrderList(int userNo) {
		return trainerMapper.trainerOrderList(userNo);
	}

	@Override
	public List<Review> trainerReviewList(int tNo) {
		return trainerMapper.trainerReviewList(tNo);
	}

	@Override
	public int trainerInsert(Trainer trainer) {
		return trainerMapper.trainerInsert(trainer);
	}

	@Override
	public int sumPtOrder() {
		return trainerMapper.sumPtOrder();
	}

	@Override
	public int sumTrainer() {
		return trainerMapper.sumTrainer();
	}

	@Override
	public int sumReview() {
		return trainerMapper.sumReview();
	}




}
