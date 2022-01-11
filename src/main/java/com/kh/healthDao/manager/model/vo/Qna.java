package com.kh.healthDao.manager.model.vo;

import java.util.Date;

import com.kh.healthDao.admin.model.vo.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
	
	private int qNo;					// 문의번호 
	private String qDeptCode;			// 문의유형코드
	private int userNo;					// 작성자
	private String qTitle;				// 제목
	private String qContent;			// 내용
	private Date qDate;					// 등록일
	private String qStatus;				// 상태
	private String reply;				// 답변
	private Date replyDate;			// 답변등록일
	private Date replyModifyDate;		// 답변수정일	
	private String qDept;			// 문의유형
	private String userNickName;	// 사용자 닉네임
	private String userPhone;		// 사용자 전화번호


}
