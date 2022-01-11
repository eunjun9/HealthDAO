package com.kh.healthDao.admin.model.service;

import java.util.Map;

import com.kh.healthDao.mypage.model.vo.MemberSound;

public interface MemberSoundService {

	int memberSoundInsert(MemberSound ms);

	Map<String, Object> memberSoundList(int page);

}
