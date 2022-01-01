package com.example.demo.trainer.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.trainer.model.dao.TrainerMapper;
import com.example.demo.trainer.model.vo.Trainer;

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


}
