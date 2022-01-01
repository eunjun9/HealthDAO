package com.example.demo.trainer.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.trainer.model.vo.Trainer;

@Mapper
public interface TrainerMapper {

	List<Trainer> TrainerList();

	Trainer TrainerSelect(int tNo);

	
}
