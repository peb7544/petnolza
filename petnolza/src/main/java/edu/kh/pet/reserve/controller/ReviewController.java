package edu.kh.pet.reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("review")
public class ReviewController {
	
	@GetMapping("reviewList")
	public String reviewList(HttpServletRequest req) {
		
		req.setAttribute("reviewGrade", 4);
		
		return "review/reviewList";
	}
	
	@GetMapping("reviewDetail")
	public String reviewDetail(HttpServletRequest req) {
		
		req.setAttribute("reviewGrade", 4);
		
		return "review/reviewDetail";
	}
}