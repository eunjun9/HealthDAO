package com.example.demo.member.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/* 스프링 시큐리티에서 제공하는 기능을 이용해야 하므로 UserDetailsService 상속 */
public interface MemberService extends UserDetailsService{

//	void signUp(Member member);

}
