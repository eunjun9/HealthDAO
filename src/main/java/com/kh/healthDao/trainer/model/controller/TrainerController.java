package com.kh.healthDao.trainer.model.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.healthDao.manager.model.vo.Payment;
import com.kh.healthDao.member.model.vo.Member;
import com.kh.healthDao.review.model.vo.Review;
import com.kh.healthDao.trainer.model.service.TrainerService;
import com.kh.healthDao.trainer.model.vo.PtOrder;
import com.kh.healthDao.trainer.model.vo.Trainer;


@Controller
@RequestMapping("/trainer/*")
public class TrainerController {

	private TrainerService trainerService;
	
	@Autowired
	public TrainerController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
	
	@GetMapping("")
	public ModelAndView trainerMain(ModelAndView mv) {
		
		int sumPtOrder = trainerService.sumPtOrder();
		int sumTrainer = trainerService.sumTrainer();
		int sumReview = trainerService.sumReview();
		
		mv.addObject("sumPtOrder", sumPtOrder);
		mv.addObject("sumTrainer", sumTrainer);
		mv.addObject("sumReview", sumReview);
		mv.setViewName("trainer/trainer");
		return mv;
	}
	
	@PostMapping("search")
	public ModelAndView trainerSearch(ModelAndView mv, String searchTrainer) {
		
		List<Trainer> trainerList = trainerService.trainerList(searchTrainer);
		
		mv.addObject("searchTrainer", searchTrainer);
		mv.addObject("trainerList", trainerList);
		mv.setViewName("trainer/trainerSearch");
		
		return mv;
	}
	
	@GetMapping("/detail")
	public ModelAndView trainerDetail(ModelAndView mv, @RequestParam int tNo) {
		
		Trainer trainer = trainerService.trainerSelect(tNo);
		
		mv.addObject("trainer", trainer);
		mv.setViewName("trainer/trainerDetail");
		
		return mv;
	}


	
	@PostMapping("modify")
	public ModelAndView trainerModify(ModelAndView mv, Trainer trainer, @RequestParam("trainerFile") MultipartFile trainerFile, @RequestParam("centerFile") MultipartFile centerFile, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		String originFile1 = null;
		String originFile2 = null;
		String ext1 = null;
		String ext2 = null;
		String changeFile1 = null;
		String changeFile2 = null;
		String path = uploadImagesPath + "trainer/";
		int result = 0;
		
		try {
			
			if(!trainerFile.isEmpty() && !centerFile.isEmpty()) {
				
				originFile1 = trainerFile.getOriginalFilename();
				ext1 = originFile1.substring(originFile1.lastIndexOf("."));
				changeFile1 = UUID.randomUUID().toString().replace("-", "") + ext1;
				trainerFile.transferTo(new File(path + "\\" + changeFile1));
				
				originFile2 = centerFile.getOriginalFilename();
				ext2 = originFile2.substring(originFile2.lastIndexOf("."));
				changeFile2 = UUID.randomUUID().toString().replace("-", "") + ext2;
				centerFile.transferTo(new File(path + "\\" + changeFile2));
				
				File file1 = new File(path+"\\"+trainer.getChange_file1());
				File file2 = new File(path+"\\"+trainer.getChange_file2());
				
				if(file1.exists() || file2.exists()) {
					file1.delete();
					file2.delete();
				}
				
				result = trainerService.trainerModify(trainer, originFile1, originFile2, changeFile1, changeFile2);

				
			} else if(!trainerFile.isEmpty()) {
				
				originFile1 = trainerFile.getOriginalFilename();
				ext1 = originFile1.substring(originFile1.lastIndexOf("."));
				changeFile1 = UUID.randomUUID().toString().replace("-", "") + ext1;
				trainerFile.transferTo(new File(path + "\\" + changeFile1));
				
				
				File file1 = new File(path+"\\"+trainer.getChange_file1());
				if(file1.exists()) {
					file1.delete();
				}
				
				result = trainerService.trainerModify2(trainer, originFile1, changeFile1);
				
				
			} else if(!centerFile.isEmpty()) {
				
				originFile2 = centerFile.getOriginalFilename();
				ext2 = originFile2.substring(originFile2.lastIndexOf("."));
				changeFile2 = UUID.randomUUID().toString().replace("-", "") + ext2;
				centerFile.transferTo(new File(path + "\\" + changeFile2));
				
				File file2 = new File(path+"\\"+trainer.getChange_file2());
				if(file2.exists()) {
					file2.delete();
				}
				
				result = trainerService.trainerModify3(trainer, originFile2, changeFile2);
				
			}
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			mv.setViewName("redirect:detail?tNo="+trainer.getTNo());
			return mv;
		} else {
			mv.setViewName("redirect:detail?tNo="+trainer.getTNo());
			return mv;
		}
	}
	
	@GetMapping("/orderList")
	public ModelAndView trainerOrderList(ModelAndView mv, @RequestParam int userNo) {
		
		List<PtOrder> trainerOrderList = trainerService.trainerOrderList(userNo);
		
		mv.addObject("trainerOrderList", trainerOrderList);
		mv.setViewName("/trainer/trainerOrderList");
		
		return mv;
	}
	
	@GetMapping("/review")
	public ModelAndView trainerReviewList(ModelAndView mv, @RequestParam int tNo) {
		
		List<Review> reviewList = trainerService.trainerReviewList(tNo);
		Trainer trainer = trainerService.trainerSelect(tNo);
		
		mv.addObject("reviewList", reviewList);
		mv.addObject("trainer", trainer);
		mv.setViewName("trainer/trainerReview");
		
		return mv;
	}

	@PostMapping("insert")
	public ModelAndView trainerInsert(ModelAndView mv, Trainer trainer, @RequestParam("trainerFile") MultipartFile trainerFile, @RequestParam("centerFile") MultipartFile centerFile, @Value("${custom.path.upload-images}") String uploadImagesPath) {
		
		String originFile1 = trainerFile.getOriginalFilename();
		String ext1 = originFile1.substring(originFile1.lastIndexOf("."));
		
		String originFile2 = centerFile.getOriginalFilename();
		String ext2 = originFile2.substring(originFile2.lastIndexOf("."));
		
		String changeFile1 = UUID.randomUUID().toString().replace("-", "") + ext1;
		
		String changeFile2 = UUID.randomUUID().toString().replace("-", "") + ext2;
		
		String path = uploadImagesPath + "trainer/";
		
		try {
			trainerFile.transferTo(new File(path + "\\" + changeFile1));
			centerFile.transferTo(new File(path + "\\" + changeFile2));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		int result = trainerService.trainerInsert(trainer, originFile1, originFile2, changeFile1, changeFile2);
		
		if(result > 0) {
			mv.addObject("msg", "등록 성공");
			mv.setViewName("redirect:");
			return mv;
		} else {
			mv.addObject("msg", "등록 실패");
			mv.setViewName("redirect:");
			return mv;
		}
	}
	
	@GetMapping("/order")
	public ModelAndView trainerOrder(ModelAndView mv, @RequestParam int tNo, Principal principal) {
		
		Member member = new Member();
		member.setUserId(principal.getName());
		member = trainerService.mOrderSelect(member);
		
		Trainer trainer = trainerService.trainerSelect(tNo);
		
		mv.addObject("trainer", trainer);
		mv.addObject("member", member);
		mv.setViewName("trainer/trainerOrder");
		return mv;
	}
	
	@PostMapping("/review/insert")
	public String trainerReviewInsert(Review review, @RequestParam int tNo) {
		
		Review rvStatus = trainerService.rvStatus(review, tNo);
		if(rvStatus != null) {
			review.setPayNo(rvStatus.getPayNo());
			
			int result = trainerService.trainerReviewInsert(review);
			
			if(result > 0) {
				return "redirect:?tNo="+tNo;
			} else {
				return "redirect:?tNo="+tNo;
			}
		} else {
			return "redirect:?tNo="+tNo;
		}
		
	}
	
	@PostMapping("pay")
	@ResponseBody
	public String trainerPay(Payment payment) {
		
		int result = trainerService.trainerPay(payment);
		
		if(result > 0) {
			return "결제 성공";
		}else {
			return "결제 실패";
		}
		
	}
	
	

}
