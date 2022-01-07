package com.kh.healthDao.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.healthDao.member.model.dao.MemberMapper;
import com.kh.healthDao.member.model.vo.Authority;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.member.model.vo.MemberGrade;
import com.kh.healthDao.member.model.vo.MemberRole;
import com.kh.healthDao.member.model.vo.UserImpl;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 로그인 페이지 username을 매개변수로 전달 -> DB select -> 스프링 시큐리티에서 제공하는 User 타입의 객체 리턴
		
		/* 우리가 만든 타입으로 유저 조회 */
		Member member = memberMapper.findMemberById(username);
		
		/* null 값을 없게 하기 위해 조회된 값이 없을 시 빈 객체 다시 담기 */
		if(member == null) member = new Member();
		
		/* GrantedAuthority 타입의 컬렉션 객체에 조회해온 권한을 담는다 */
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// 멤버의 멤버롤리스트 -> 일반유저 1개, 트레이너유저 2개, 어드민유저 3개 권한
		if(member.getMemberRoleList() != null) {
			List<MemberRole> roleList = member.getMemberRoleList();
			
			// ADMIN의 경우 반복문이 3번 돌게 됨 (MEMBER, TRAINER, ADMIN 권한을 모두 가지므로)
			for(MemberRole role : roleList) {
				// 권한에 대한 상세 설명 값 추출 (코드, 권한명, 권한설명)
				Authority authority = role.getAuthority();
				
				// null이 아닐 경우 사용해야 하는 매개변수 타입에 맞게 List에 추가 (ROLE_ADMIN, ROLE_TRAINER, ROLE_MEMBER)
				if(authority != null) {
					authorities.add(new SimpleGrantedAuthority(authority.getName()));
				}
			}
		}
		
		/* 스프링 시큐리티 모듈에서 사용되는 타입인 User 객체로 id, pwd, 접근권한을 담아 객체 만들어 리턴 */
//		return new User(member.getId(), member.getPwd(), authorities);
		
		/* 멤버의 id, pwd 외의 다른 정보를 담기 위해 User를 상속한 UserImpl 클래스를 만들고 해당 타입으로 처리 */
		UserImpl user = new UserImpl(member.getUserId(), member.getUserPwd(), authorities);
		user.setDetails(member);	// 위에서 조회해온 member를 여기에서 사용
		
		return user;
	}

	@Transactional
	@Override
	public void signUp(Member member) {
		/* BCrypt에서 사용한 rawPassword -> encodePassword */
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUserPwd(passwordEncoder.encode(member.getUserPwd()));
		
		/* MEMBER INSERT */
		memberMapper.insertMember(member);
		
		/* MEMBER_ROLE INSERT */
		MemberRole memberRole = new MemberRole();
		memberRole.setAuthorityCode(1);
		memberMapper.insertMemberRole(memberRole);
		
		/* MEMBER_GRADE INSERT */
		MemberGrade memberGrade = new MemberGrade();
		memberGrade.setUserGrade("일반회원");
		memberMapper.insertMemberGrade(memberGrade);
	}

	@Override
	public int idChk(Member member) {
		int result = memberMapper.idChk(member);
		return result;
	}
	
}
