package com.kh.healthDao.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.healthDao.main.model.service.BannerService;
import com.kh.healthDao.main.model.vo.Banner;

@Controller
public class FileUploadController {
	
	
	private BannerService bannerService;
	
	@Autowired
	public FileUploadController(BannerService bannerService) {
		this.bannerService = bannerService;
	}

	@PostMapping("/uploadBanner")
	public String singleFileUpload(Banner banner, @RequestParam MultipartFile imgUpload, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		
		/* 파일을 저장할 경로 */
		String root = uploadImagesPath;
		System.out.println("root : " + root);
		
		/* 파일명 확인 */
		String originFileName = imgUpload.getOriginalFilename();
		System.out.println("originFileName : " + originFileName);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 파일명 변경 처리 */
		String path = uploadImagesPath + "main/";	// 업로드 경로 지정
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("savedName : " + savedName);
		
		/* 파일을 저장함 */
		try {
			imgUpload.transferTo(new File(path + "\\" + savedName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		
		String msg = bannerService.insertBanner(banner, originFileName, path, savedName) > 0 ? "등록 성공" : "등록 실패";

		return "redirect:/banner";
	}
}
