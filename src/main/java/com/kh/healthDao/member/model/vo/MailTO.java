package com.kh.healthDao.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailTO {
	private String address;		// 메일주소
    private String title;		// 제목
    private String message;		// 내용

}
