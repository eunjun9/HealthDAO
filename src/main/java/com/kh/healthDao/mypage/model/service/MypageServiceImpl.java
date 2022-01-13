package com.kh.healthDao.mypage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.dao.MypageMapper;
import com.kh.healthDao.mypage.model.vo.Address;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;
import com.kh.healthDao.review.model.vo.Review;


@Service("mypageService")
public class MypageServiceImpl implements QnaService, MyCouponService, MyReviewService, MyInfoService{
	
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

	@Override
	public Map<String, Object> userReviewList(int page, int userNo) {
		int listCount = mypageMapper.userReviewListCount(userNo);
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		pageRow.put("userNo", userNo);
		
		List<Qna> reviewList = mypageMapper.userReviewList(pageRow);
		
		Map<String, Object> review = new HashMap<>();
		
		review.put("listCount", listCount);
		review.put("reviewList", reviewList);
		review.put("pi", pi);
		
		return review;
   }
  

	// 페이징 된 포인트 내역
	@Override
	public Map<String, Object> pointList(int page) {
		int listCount = mypageMapper.pointListCount();
		Paging pi = new Paging(page, listCount, 5, 6);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Point> PointList = mypageMapper.listPoint(pageRow);
		
		Map<String, Object> point = new HashMap<>();
		
		point.put("listCount", listCount);
		point.put("PointList", PointList);
		point.put("pi", pi);
		
		return point;
	}

	@Override
	public Review reviewDetail(int reviewNo) {
		return mypageMapper.reviewDetail(reviewNo);
	}

	@Override
	public int reviewModify(Review review) {
		return mypageMapper.reviewModify(review);

	}
	
	// 출석체크
	@Override
	public int attendCheck(AttCheck attcheck) {
		return mypageMapper.attendCheck(attcheck);
	}

	/* 내 정보 수정 */
	@Override
	public Member myInfoView(int userNo) {
		return mypageMapper.myInfoView(userNo);
	}
	
	@Transactional
	@Override
	public int myInfoModify(Member member) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUserPwd(passwordEncoder.encode(member.getUserPwd()));
		
		return mypageMapper.myInfoModify(member);
	}

	/* 배송지 등록 */
	@Override
	public List<Address> deliView(int userNo) {
		return mypageMapper.deliView(userNo);
	}
	
	@Override
	public int insertDeli(Address address) {
		return mypageMapper.insertDeli(address);
	}

	@Override
	public Address selectDeli(int addressNo) {
		return mypageMapper.selectDeil(addressNo);
	}


}
