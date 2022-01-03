package com.example.demo.member.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	@RequestMapping(value="/member/join", method=RequestMethod.GET)
	public String trainerMain() {
		
		return "member/memberJoin";
	}

}
