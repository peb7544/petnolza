package edu.kh.pet.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class IntroController {
	
	@GetMapping("qna")
	public String qna() {
		
		return "main/qna";
	}
}
