package com.kh.healthDao.mypage.model.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.kh.healthDao.mypage.model.vo.Address;
import com.kh.healthDao.mypage.model.vo.AttCheck;
import com.kh.healthDao.mypage.model.vo.Cart;
import com.kh.healthDao.admin.model.vo.Coupon;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.mypage.model.vo.Point;
import com.kh.healthDao.mypage.model.vo.Qna;
import com.kh.healthDao.review.model.vo.Review;


@Mapper
public interface MypageMapper {

	int qnaInsert(Qna newQna);

	List<Qna> findQnaList(Map<String, Object> pageRow);

	int getQnaListCount(int userNo);

	Qna qnaDetail(int qNo);

	int qnaModify(Qna modifyQna);

	List<Coupon> couponEventList();

	List<Point> pointList();

	int myCouponInsert(Map<String, Object> map);

	List<Coupon> myCouponList(int userNo);

	int userReviewListCount(int userNo);

	List<Qna> userReviewList(Map<String, Object> pageRow);


	// 포인트 페이징
	int pointListCount();
	List<Point> listPoint(Map<String, Object> pageRow);
	int pointCount();

	Review reviewDetail(int reviewNo);

	int reviewModify(Review review);

	// 출석체크
	int attendCheck(AttCheck attcheck);
	List<AttCheck> attendUserList(int userNo);

	/* 내 정보 수정 */
	Member myInfoView(int userNo);
	
	int myInfoModify(Member member);

	/* 배송지 등록 */
	List<Address> deliView(int userNo);
	
	int insertDeli(Address address);
  
  Address selectDeil(int addressNo);

  /* 장바구니 */
	int cartInsert(Cart cartinfo);

	List<Cart> cartList(int userNo);

	Cart cartProductChk(Cart cartinfo);

	int cartStock(Map<String, Object> map);

	int cartDelete(int cartNo);

	int cartAllDelete(int userNo);


}
