package com.kh.healthDao.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.healthDao.member.model.dao.MemberMapper;
import com.kh.healthDao.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void signUp(Member member) {
		// TODO Auto-generated method stub
	}
	
}
