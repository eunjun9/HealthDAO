package com.kh.healthDao.member.model.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kh.healthDao.member.model.vo.MailTO;
import com.kh.healthDao.member.model.vo.Member;

/* 스프링 시큐리티에서 제공하는 기능을 이용해야 하므로 UserDetailsService 상속 */
public interface MemberService extends UserDetailsService{

	void signUp(Member member);

	int idChk(Member member);

	Member findId(String userName, String userEmail);

	Member selectMember(String userEmail);

	int pwdUpdate(Member member);
	
}
