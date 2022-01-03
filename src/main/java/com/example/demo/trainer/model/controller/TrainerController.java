package com.example.demo.trainer.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.trainer.model.service.TrainerService;
import com.example.demo.trainer.model.vo.Trainer;

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

}
