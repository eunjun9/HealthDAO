package com.kh.healthDao.mypage.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
	private int qNo;						// 문의번호
	private String qDeptCode;				// 문의유형코드
	private String qDept;					// 문의유형
	private int userNo;						// 작성자 유저코드
	private String userId;					// 작성자
	private String qTitle;					// 제목
	private String qContent;				// 내용
	private Date qDate;						// 등록일
	private String qStatus;					// 상태
	private String reply;					// 관리자 답변
	private Date replyDate;					// 관리자 답변일
	private Date replyModifyDate;			// 관리자 답변 수정일
}
