package com.example.demo.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TrainerController {

	@RequestMapping(value="/trainer", method=RequestMethod.GET)
	public String trainerMain() {
		
		return "trainer/trainer";
	}
	
	@RequestMapping(value="/trainer/Search", method=RequestMethod.GET)
	public String trainerSearch() {
		
		return "trainer/trainerSearch";
	}
	
}
