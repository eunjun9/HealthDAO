package com.kh.healthDao.trainer.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public String trainerMain(Model model) {
		
		return "trainer/trainer";
	}
	
	@GetMapping("/search")
	public ModelAndView trainerSearch(ModelAndView mv) {
		
		List<Trainer> trainerList = trainerService.TrainerList();
		
		mv.addObject("trainerList", trainerList);
		mv.setViewName("trainer/trainerSearch");
		
		return mv;
	}
	
	@GetMapping("/detail")
	public ModelAndView trainerDetail(ModelAndView mv, @RequestParam int tNo) {
		
		Trainer trainer = trainerService.TrainerSelect(tNo);
		
		mv.addObject("trainer", trainer);
		mv.setViewName("trainer/trainerDetail");
		
		return mv;
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public ModelAndView trainerModify(ModelAndView mv, Trainer trainer) {
		
		int result = trainerService.TrainerModify(trainer);
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
		
		List<PtOrder> trainerOrderList = trainerService.TrainerOrderList();
		
		mv.addObject("trainerOrderList", trainerOrderList);
		mv.setViewName("/trainer/trainerOrderList");
		
		return mv;
	}
	
	
//	@RequestMapping("/starSumAjax")
//	@ResponseBody
//	public Trainer starSumAjax(@RequestBody Map<String, Integer> param) {
//		System.out.println(param.get("tNo"));
//		Trainer trainer = new Trainer();
//		trainer.setAvgStar(trainerService.TrainerAvgStar(param.get("tNo")));
//		trainer.setSumReview(trainerService.TrainerSumReview(param.get("tNo")));
//		return trainer;
//	}
	


}
