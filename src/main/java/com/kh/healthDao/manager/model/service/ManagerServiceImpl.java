package com.kh.healthDao.manager.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.manager.model.dao.ManagerMapper;
import com.kh.healthDao.manager.model.vo.Qna;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	
	 private final ManagerMapper managerMapper;
	 
	 @Autowired
	 public ManagerServiceImpl(ManagerMapper managerMapper) {
		 this.managerMapper = managerMapper; 
	 }
	 
	
	@Override
	public List<Qna> listAllQna() {

		return managerMapper.listAllQna();
	}


	@Override
	public Qna listQna(int qNo) {
		return managerMapper.listQna(qNo);
	}


	@Override
	public int managerQAnswer(Qna qna) {
		return managerMapper.managerQAnswer(qna);
	}




	





}
