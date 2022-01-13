package com.kh.healthDao.manager.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.admin.model.vo.Product;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.manager.model.dao.ManagerMapper;
import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.manager.model.vo.Qna;
import com.kh.healthDao.manager.model.vo.Refund;

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


	// 페이징 된 정산내역
	@Override
	public Map<String, Object> calculateList(int page) {
		int listCount = managerMapper.calculateListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Payment> calculateList = managerMapper.calculateList(pageRow);
		
		Map<String, Object> calculate = new HashMap<>();
		
		calculate.put("listCount", listCount);
		calculate.put("calculateList", calculateList);
		calculate.put("pi", pi);
		
		return calculate;
	}


	
	// 페이징된 환불리스트
	@Override
	public Map<String, Object> refundListPaging(int page) {
		int listCount = managerMapper.refundListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Refund> refundList = managerMapper.refundList(pageRow);
		
		Map<String, Object> Refund = new HashMap<>();
		
		Refund.put("listCount", listCount);
		Refund.put("refundList", refundList);
		Refund.put("pi", pi);
		
		return Refund;
	}



	// 환불완료 버튼
	@Override
	public int managerRefundOk(Refund refund) {
		
		return managerMapper.managerRefundOk(refund);
	}






	





}
