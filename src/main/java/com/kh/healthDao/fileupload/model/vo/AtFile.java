package com.kh.healthDao.fileupload.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtFile {
	
	private int fNo;				// 파일번호
	private String originFile;		// 원본파일명
	private String changeFile;		// 바뀐파일명
	private String filePath;		// 파일경로
	private String status;			// 상태
	
}
