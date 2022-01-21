package com.kh.healthDao.mypage.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.dao.MypageMapper;
import com.kh.healthDao.mypage.model.vo.Address;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Cart;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;
import com.kh.healthDao.mypage.model.vo.Roulette;
import com.kh.healthDao.review.model.vo.Review;

@Service("mypageService")
public class MypageServiceImpl implements QnaService, MyCouponService, MyReviewService, MyInfoService, CartService, PaymentService{
	
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
	public Map<String, Object> pointList(int page, int userNo) {
		int listCount = mypageMapper.pointListCount(userNo);
		Paging pi = new Paging(page, listCount, 5, 6);
		
		int pointCount = 0;
		String pointCounts = mypageMapper.pointCount(userNo);
		if(pointCounts != null) {
			pointCount = Integer.parseInt(pointCounts);
		}
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		pageRow.put("userNo", userNo);
		
		List<Point> PointList = mypageMapper.listPoint(pageRow);
		
		Map<String, Object> point = new HashMap<>();
		
		point.put("listCount", listCount);
		point.put("PointList", PointList);
		point.put("pointCount", pointCount);
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
	
	// 룰렛
	@Override
	public int rouletteInsert(int userNo, int pointamount) {
		
		int pointInsert = mypageMapper.rouletteInsert(userNo, pointamount);
		int rouletteTableInsert = mypageMapper.rouletteTableInsert(userNo);
		// System.out.println(pointInsert);
		// System.out.println(rouletteTableInsert);
		int result = 0;
		if(pointInsert > 0 && rouletteTableInsert > 0) result = 1;
		
		return result;
	}

	@Override
	public List<Roulette> rouletteButton(int userNo) {
		return mypageMapper.rouletteButton(userNo);
	}
	
	// 출석체크
	@Override
	public int attendCheck(AttCheck attcheck) {
		int pointResult = mypageMapper.pointCheck(attcheck.getUserNo());
		int attendResult = mypageMapper.attendCheck(attcheck);
		int result = 0;
		if(pointResult > 0 && attendResult > 0) result = 1;
		return result;
	}
	
	// 출석체크 여부 확인
	@Override
	public List<AttCheck> attendUserList(int userNo) {
		return mypageMapper.attendUserList(userNo);
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
	
	@Override
	public int myInfoDelete(int userNo, HttpSession session) {
		int result = mypageMapper.myInfoDelete(userNo);
		if(result == 1) session.invalidate();
		return result;
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
	
	@Override
	public int updateDeil(int addressNo) {
		return mypageMapper.updateDeil(addressNo);
	}
	
	@Override
	public int deleteDeil(int addressNo) {
		return mypageMapper.deleteDeli(addressNo);
	}
	
	@Override
	public void defAddRemove(int userNo) {
		mypageMapper.defAddRemove(userNo);
	}
	
	@Override
	public int defAddDeli(int addressNo) {
		return mypageMapper.defAddDeli(addressNo);
	}
	
	/* 회원 탈퇴 */
	@Override
	public void unregister(Member member, HttpSession session) {
		mypageMapper.unregister(member);
		session.invalidate();
	}
	
	/* 장바구니 */
	@Override
	public int cartInsert(Cart cartinfo) {
		Cart cartProductChk = mypageMapper.cartProductChk(cartinfo);
		
		int result = 1;
		if(cartProductChk == null) {
			result = mypageMapper.cartInsert(cartinfo);
		}
		
		return result;
	}

	@Override
	public List<Cart> cartList(int userNo) {
		return mypageMapper.cartList(userNo);
	}

	@Override
	public int cartStock(int cartNo, String upDown) {
		Map<String, Object> map = new HashMap<>();
		map.put("cartNo", cartNo);
		map.put("upDown", upDown);
		
		return mypageMapper.cartStock(map);
	}

	@Override
	public int cartDelete(int cartNo) {
		return mypageMapper.cartDelete(cartNo);
	}

	@Override
	public int cartAllDelete(int userNo) {
		return mypageMapper.cartAllDelete(userNo);
	}

	@Override
	public List<Payment> mypaymentList(int userNo) {
		return mypageMapper.mypaymentList(userNo);
	}

	@Override
	public int reviewInsert(Review review) {
		int payNo = review.getPayNo();
		// 한 결제 당 상품 몇개인지 구하기
		int payProductCount = mypageMapper.payProductCount(payNo);
		// 현재 한 결제 중 리뷰 몇개 되어있는지 구하기
		int payReviewCount = mypageMapper.payReviewCount(payNo);
		
		if((payProductCount - 1) == payReviewCount) {
			mypageMapper.statusModify(review);
		}
		
		int insertResult = mypageMapper.reviewInsert(review);
		
		return insertResult;
	}

	@Override
	public int statusModify(Review review) {
		return mypageMapper.statusModify(review);
	}

	@Override
	public int refundInsert(int payNo) {
		// 환불 리스트에 추가
		int result1 = mypageMapper.refundInsert(payNo);
		// 결제 상태 변경
		int result2 = mypageMapper.refundStatusModify(payNo);
		
		int totalResult = 0;
		
		if(result1 > 0 && result2 > 0) {
			totalResult = 1;
		}
		
		return totalResult;
	}

	public int attendCount(int userNo) {
		return mypageMapper.attendCount(userNo);
	}

	// 회원등급
	@Override
	public List<Payment> memberGrade(int userNo) {
		return mypageMapper.memberGrade(userNo);
	}




	


}
