package com.kh.healthDao.trainer.model.service;

import java.util.List;

import com.kh.healthDao.trainer.model.vo.Trainer;


public interface TrainerService {
	List<Trainer> TrainerList();

	Trainer TrainerSelect(int tNo);

}
