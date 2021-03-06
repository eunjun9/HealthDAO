package com.kh.healthDao.member.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	private int userNo;				// 유저번호
	private String userId;			// 아이디
	private String userPwd;			// 비밀번호
	private String userName;		// 이름
	private String userNickName;	// 닉네임
	private String userPhone;		// 휴대폰번호
	private String userEmail;		// 이메일
	private String userBirth;		// 생년월일
	private String userGender;		// 성별
	private Date joinDate;			// 가입일
	private String userStatus;		// 활동여부
	private Date banDate;			// 활동정지일
	private String emailReceive;	// 이메일수신여부
	private String smsReceive;		// SMS수신여부
	private int point;				// 포인트
	private int authorityCode;
	private int num;
	
	/* 한 멤버는 여러 권한을 가질 수 있음 - MEMBER_ROLE과 조인한 결과 값 */
	private List<MemberRole> memberRoleList;	// 보유권한목록
	
	private MemberGrade memberGrade;	// 유저등급
}
