package com.example.demo.trainer.model.service;

import java.util.List;

import com.example.demo.trainer.model.vo.Trainer;

public interface TrainerService {
	List<Trainer> TrainerList();

	Trainer TrainerSelect(int tNo);

}
