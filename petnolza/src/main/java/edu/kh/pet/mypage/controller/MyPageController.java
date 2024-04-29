package edu.kh.pet.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mypage")
public class MyPageController {

	@GetMapping("reserveList")
	public String reserveList() {
		
		return "mypage/reserveList";
	}
	
	@GetMapping("reviewWrite")
	public String reviewWrite() {
		
		return "mypage/reviewWrite";
	}
}
