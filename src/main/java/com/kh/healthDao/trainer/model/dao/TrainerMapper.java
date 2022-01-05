package com.kh.healthDao.trainer.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.trainer.model.vo.PtOrder;
import com.kh.healthDao.trainer.model.vo.Trainer;


@Mapper
public interface TrainerMapper {

	List<Trainer> TrainerList();

	Trainer TrainerSelect(int tNo);

	int TrainerModify(Trainer trainer);

	List<PtOrder> TrainerOrderList();


	
}
