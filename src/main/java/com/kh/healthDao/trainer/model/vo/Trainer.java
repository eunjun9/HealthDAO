package com.kh.healthDao.trainer.model.vo;


import com.kh.healthDao.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
	
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
	
	private Member member;			// Member 테이블
	private float avgStar;
	private int sumReview;
	
	private String file_path;		// 파일경로
	private String change_file1;	// 트레이너 파일명
	private String change_file2;	// 센터 파일명
	private int f_tno;				// 트레이너 파일 번호
	private int f_cno;				// 센터 파일 번호
	
}
