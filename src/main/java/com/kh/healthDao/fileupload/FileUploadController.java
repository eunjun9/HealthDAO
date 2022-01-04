package com.kh.healthDao.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@PostMapping("/uploadBanner")
	public String singleFileUpload(@RequestParam MultipartFile singleFile, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		
		/* 파일을 저장할 경로 */
		String root = uploadImagesPath;
		System.out.println("root : " + root);
		
		/* 파일명 확인 */
		String originFileName = singleFile.getOriginalFilename();
		System.out.println("originFileName : " + originFileName);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 파일명 변경 처리 */
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("savedName : " + savedName);
		
		/* 파일을 저장함 */
		try {
			singleFile.transferTo(new File(uploadImagesPath + "main/" + "\\" + savedName));		// 업로드 경로 지정
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return "redirect:/banner";
	}
}
