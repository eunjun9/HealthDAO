package com.kh.healthDao.mypage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.mypage.model.dao.MypageMapper;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;


@Service("mypageService")
public class MypageServiceImpl implements QnaService, MyCouponService{
	
	private final MypageMapper mypageMapper; 
	
	@Autowired
	public MypageServiceImpl(MypageMapper mypageMapper) {
		this.mypageMapper = mypageMapper;
	}
	
	@Override
	public int qnaInsert(Qna newQna) {
		return mypageMapper.qnaInsert(newQna);
	}

	@Override
	public int qnaModify(Qna modifyQna) {
		return mypageMapper.qnaModify(modifyQna);
	}
	

	@Override
	public Qna qnaDetail(int qNo) {
		return mypageMapper.qnaDetail(qNo);
	}

	@Override
	public List<Coupon> couponEventList() {
		return mypageMapper.couponEventList();
	}
	
	// 포인트 내역
	@Override
	public List<Point> pointList() {
		
		return mypageMapper.pointList();

	}

	@Override
	public Map<String, Object> findQnaList(int page, int userNo) {
		int listCount = mypageMapper.getQnaListCount(userNo);
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		pageRow.put("userNo", userNo);
		
		List<Qna> qnaList = mypageMapper.findQnaList(pageRow);
		
		Map<String, Object> qna = new HashMap<>();
		
		qna.put("listCount", listCount);
		qna.put("qnaList", qnaList);
		qna.put("pi", pi);
		
		return qna;
	}

	@Override
	public int myCouponInsert(int pNo, int userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("pNo", pNo);
		map.put("userNo", userNo);
				
		return mypageMapper.myCouponInsert(map);
	}

	@Override
	public List<Coupon> myCouponList(int userNo) {
		return mypageMapper.myCouponList(userNo);
	}

	// 출석체크
	@Override
	public int attendanceCheck(AttCheck att) {
		
		return mypageMapper.attendanceCheck(att);
	}

}
