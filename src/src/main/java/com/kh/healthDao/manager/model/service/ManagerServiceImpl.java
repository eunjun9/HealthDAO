package com.kh.healthDao.manager.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;
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
	public Qna listQna(int qNo) {
		return managerMapper.listQna(qNo);
	}


	@Override
	public int managerQAnswer(Qna qna) {
		return managerMapper.managerQAnswer(qna);
	}


	@Override
	public Map<String, Object> InquiryPaging(int page) {
		int listCount = managerMapper.getInquiryCount();
		Paging pi = new Paging(page, listCount, 5, 5);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Qna> QnaList = managerMapper.listInquiryInventory(pageRow);
		
		Map<String, Object> inv = new HashMap<>();
		
		inv.put("listCount", listCount);
		inv.put("QnaList", QnaList);
		inv.put("pi", pi);
		
		
		return inv;
	}







	





}
