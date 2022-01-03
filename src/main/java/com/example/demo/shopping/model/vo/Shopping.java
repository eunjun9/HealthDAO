package com.example.demo.shopping.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shopping {
	
	private int tNo;				// 트레이너번호
	private String tGymName;		// 센터명
	private String tGymAddress;		// 센터주소
	private String tArea;			// 활동지역
	private String tMetro;			// 활동지하철역
	private String tAreaIntro;		// 위치소개
	private String tOneIntro;		// 한줄소개
	private int tPrice;				// 가격
	private String weekDaySc;		// 평일스케줄
	private String weekAndSc;		// 주말스케줄
	private String tIntro;			// 선생님소개
	private String queIntro;		// 문의소개
	
	/* + 추가할 것
	 * MEMBER 조인 -> 트레이너 이름, 트레이너 전화번호
	 * REVIEW 조인 -> 별점, 리뷰 수
	 * TRAINER_FILE 조인 -> AT_FILE 조인 -> 트레이너 사진 1장, 헬스장 사진 2장
	 * 
	 * */
	
}
