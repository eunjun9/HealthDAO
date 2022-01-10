package com.kh.healthDao.trainer.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/search")
	public ModelAndView trainerSearch(ModelAndView mv) {
		
		List<Trainer> trainerList = trainerService.trainerList();
		
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
	public ModelAndView trainerModify(ModelAndView mv, Trainer trainer) {
		
		int result = trainerService.trainerModify(trainer);
		if(result > 0) {
			mv.addObject("msg", "수정 성공");
			mv.setViewName("redirect:detail?tNo="+trainer.getTNo());
			return mv;
		} else {
			mv.addObject("msg", "수정 실패");
			mv.setViewName("redirect:detail?tNo="+trainer.getTNo());
			return mv;
		}
	}
	
	@GetMapping("/orderList")
	public ModelAndView trainerOrderList(ModelAndView mv) {
		
		List<PtOrder> trainerOrderList = trainerService.trainerOrderList();
		
		mv.addObject("trainerOrderList", trainerOrderList);
		mv.setViewName("/trainer/trainerOrderList");
		
		//세션 userNo 받아와서 orderList 로직 등록
		
		return mv;
	}
	
	@GetMapping("/review")
	public ModelAndView trainerReviewList(ModelAndView mv, @RequestParam int tNo) {
		
		List<Review> reviewList = trainerService.trainerReviewList(tNo);
		Trainer trainer = trainerService.trainerSelect(tNo);
		mv.addObject("trainer", trainer);
		mv.addObject("reviewList", reviewList);
		mv.setViewName("trainer/trainerReview");
		
		return mv;
	}

	@PostMapping("insert")
	public ModelAndView trainerInsert(ModelAndView mv, Trainer trainer) {
		// 세션으로 유저 id 받아와서 등록
		int result = trainerService.trainerInsert(trainer);
		if(result > 0) {
			mv.addObject("msg", "수정 성공");
			mv.setViewName("redirect:");
			return mv;
		} else {
			mv.addObject("msg", "수정 실패");
			mv.setViewName("redirect:");
			return mv;
		}
	}

}
