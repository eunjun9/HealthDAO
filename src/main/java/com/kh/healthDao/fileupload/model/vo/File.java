package com.kh.healthDao.fileupload.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

	/*
	 * F_NO 파일번호
	 * ORIGIN_FILE 원본파일명
	 * CHANGE_FILE 바뀐파일명
	 * FILE_PATH 파일경로
	 * STATUS 상태
	 */
	
	int f_no;
	String origin_file;
	String change_file;
	String file_path;
	String status;
}
