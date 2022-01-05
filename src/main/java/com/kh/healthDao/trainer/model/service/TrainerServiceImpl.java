package com.kh.healthDao.trainer.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Trainer> TrainerList() {
		return trainerMapper.TrainerList();
	}

	@Override
	public Trainer TrainerSelect(int tNo) {
		return trainerMapper.TrainerSelect(tNo);
	}

	@Override
	public int TrainerModify(Trainer trainer) {
		return trainerMapper.TrainerModify(trainer);
	}

	@Override
	public List<PtOrder> TrainerOrderList() {
		return trainerMapper.TrainerOrderList();
	}






}
