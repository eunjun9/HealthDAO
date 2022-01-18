package com.kh.healthDao.trainer.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<PtOrder> trainerOrderList(int userNo) {
		return trainerMapper.trainerOrderList(userNo);
	}

	@Override
	public List<Review> trainerReviewList(int tNo) {
		return trainerMapper.trainerReviewList(tNo);
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

	@Override
	public int trainerInsert(Trainer trainer, String originFile1, String originFile2, String changeFile1, String changeFile2) {
		Map<String, Object> map = new HashMap<>();
		
		trainer.setFile_path("/images/upload/trainer/");
		trainer.setChange_file1(changeFile1);
		trainer.setChange_file2(changeFile2);
		
		map.put("trainer", trainer);
		map.put("originFile1", originFile1);
		map.put("originFile2", originFile2);
		
		int result1 = trainerMapper.trainerInsert(trainer);
		int result2 = trainerMapper.trainerFileInsert(map);
		int result3 = trainerMapper.trainerFileInsertDB(trainer);
		int result4 = trainerMapper.centerFileInsert(map);
		int result5 = trainerMapper.centerFileInsertDB(trainer);
		int result = 0;
		
		if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public int trainerModify(Trainer trainer, String originFile1, String originFile2, String changeFile1, String changeFile2) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("trainer", trainer);
		map.put("originFile1", originFile1);
		map.put("originFile2", originFile2);
		map.put("changeFile1", changeFile1);
		map.put("changeFile2", changeFile2);
		
		
		int result2 = trainerMapper.trainerFileModify(map);
		int result3 = trainerMapper.centerFileModify(map);
		int result1 = trainerMapper.trainerModify(trainer);
		int result = 0;
		
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public int trainerModify2(Trainer trainer, String originFile1, String changeFile1) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("trainer", trainer);
		map.put("originFile1", originFile1);
		map.put("changeFile1", changeFile1);
		
		
		int result2 = trainerMapper.trainerFileModify(map);
		int result1 = trainerMapper.trainerModify(trainer);
		int result = 0;
		
		if(result1 > 0 && result2 > 0) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public int trainerModify3(Trainer trainer, String originFile2, String changeFile2) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("trainer", trainer);
		map.put("originFile2", originFile2);
		map.put("changeFile2", changeFile2);
		
		
		int result2 = trainerMapper.centerFileModify(map);
		int result1 = trainerMapper.trainerModify(trainer);
		int result = 0;
		
		if(result1 > 0 && result2 > 0) {
			result = 1;
		}
		
		return result;
	}

	@Override
	public List<Trainer> trainerRankList() {
		return trainerMapper.trainerRankList();
	}





}
