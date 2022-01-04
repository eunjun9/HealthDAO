package com.kh.healthDao.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@PostMapping("/single-file")
	public String singleFileUpload(@RequestParam MultipartFile singleFile, HttpServletRequest request, Model model) {
		
		String singleFileDescrition = request.getParameter("singleFileDescription");
		System.out.println("singleFile : " + singleFile);
		System.out.println("singleFileDescrition : " + singleFileDescrition);
		
		
		/* 파일을 저장할 경로 */
		String root = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("root : " + root);
		
		String filePath = root + "\\uploadFiles";
		
		/* 해당 파일 경로 존재 여부 확인하여 없을 경우 make directory */
		File mkdir = new File(filePath);
		if(!mkdir.exists()) mkdir.mkdirs();
		
		/* 파일명 확인 */
		String originFileName = singleFile.getOriginalFilename();
		System.out.println("originFileName : " + originFileName);
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 파일명 변경 처리 */
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("savedName : " + savedName);
		
		/* 파일을 저장함 */
		try {
			singleFile.transferTo(new File(filePath + "\\" + savedName));
			model.addAttribute("message", "파일 업로드 성공!");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
}
