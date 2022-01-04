package com.kh.healthDao.member.model.vo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class UserImpl extends User{
	
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
	
	/* 한 멤버는 여러 권한을 가질 수 있음 - MEMBER_ROLE과 조인한 결과 값 */
	private List<MemberRole> memberRoleList;	// 보유권한목록

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(Member member) {
		this.userNo = member.getUserNo();
		this.userId = member.getUserId();	// = username
		this.userPwd = member.getUserPwd();	// = password
		this.userName = member.getUserName();
		this.userNickName = member.getUserNickName();
		this.userPhone = member.getUserPhone();
		this.userEmail = member.getUserEmail();
		this.userBirth = member.getUserBirth();
		this.userGender = member.getUserGender();
		this.joinDate = member.getJoinDate();
		this.userStatus = member.getUserStatus();
		this.banDate = member.getBanDate();
		this.emailReceive = member.getEmailReceive();
		this.smsReceive = member.getSmsReceive();
		this.memberRoleList = member.getMemberRoleList();
	}

}
