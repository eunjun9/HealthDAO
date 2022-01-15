package com.kh.healthDao.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.healthDao.main.model.service.BannerService;
import com.kh.healthDao.main.model.vo.Banner;

@Controller
public class FileUploadController {
	
	
	private BannerService bannerService;
	
	@Autowired
	public FileUploadController(BannerService bannerService) {
		this.bannerService = bannerService;
	}

	@PostMapping("/banner/upload")
	public String singleFileUpload(Banner banner, @RequestParam MultipartFile imgUpload1, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		
		/* 파일을 저장할 경로 */
		String root = uploadImagesPath;
		/* 파일명 확인 */
		String originFileName = imgUpload1.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 파일명 변경 처리 */
		String path = uploadImagesPath + "main/";	// 업로드 경로 지정
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("savedName : " + savedName);
		
		/* 파일을 저장함 */
		try {
			imgUpload1.transferTo(new File(path + "\\" + savedName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		bannerService.insertBanner(banner, originFileName, savedName);
		
		return "redirect:/banner?page=1";
	}
	
	@PostMapping("/banner/update")
	public String updateBanner(Banner banner, @RequestParam MultipartFile imgUpload, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		System.out.println("배너 " + banner);
		System.out.println("경로 " + uploadImagesPath);
		System.out.println(imgUpload.getOriginalFilename() != null && imgUpload.getOriginalFilename() != "");
		
		if(imgUpload.getOriginalFilename() != null && imgUpload.getOriginalFilename() != "") {	// 파일 추가 했을 경우
			System.out.println("ee");
			String fileName = bannerService.findFileName(banner.getF_no());
			System.out.println("fileName : " +fileName);			// 이전에 저장되어 있던 이미지명

			/* 파일을 저장할 경로 */
			String root = uploadImagesPath;
			/* 파일명 확인 */
			String originFileName = imgUpload.getOriginalFilename();	// 파일명
			String ext = originFileName.substring(originFileName.lastIndexOf("."));	// 포맷

			/* 파일명 변경 처리 */
			String path = uploadImagesPath + "main/";	// 업로드 경로 지정
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			System.out.println("savedName : " + savedName);
			
			/* 파일을 저장함 */
			try {
				imgUpload.transferTo(new File(path + "\\" + savedName));
				
				File prevFile = new File(path + "\\" + fileName);	// 이전 파일
				prevFile.delete();	// 삭제

				bannerService.updateBanner(banner, originFileName, savedName);	// 파일 업데이트
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		bannerService.updateOnlyBanner(banner);	// 이미지 말고 다른거 업데이트
		
		return "redirect:/banner?page=1";
	}
	
	@ResponseBody
	@PostMapping("/banner/delete")
	public int deleteBanner(int[] addList, int[] addList2, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		System.out.println("경로 " + uploadImagesPath);
		String path = uploadImagesPath + "main/";	// 경로 지정
		int result = 0;
		for(int i = 0; i < addList.length; i++) {
			String fileName = bannerService.findFileName(addList[i]);	// main_no로 파일 이름 가져오기
			System.out.println("fileName : " + fileName);
			File prevFile = new File(path + "\\" + fileName);
			prevFile.delete();	// 삭제
			result += bannerService.deleteBanner(addList[i], addList2[i]);			
		}
		System.out.println(result);
		
		return result;
	}

}
