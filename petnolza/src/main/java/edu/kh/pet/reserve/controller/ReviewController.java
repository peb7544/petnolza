package edu.kh.pet.reserve.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.reserve.model.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewService service;
	
	
	/** 리뷰목록조회
	 * @param req
	 * @return
	 */
	@GetMapping("reviewList/{roomId:[0-9]+}") 
	public String reviewList(
				@PathVariable("roomId") int roomId,
				Model model ,
				@RequestParam(value="cp", required=false, defaultValue="1") int cp
			) { 
		
		// 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectReviewList(roomId, cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("reviewList", map.get("reviewList"));
		
		return "review/reviewList";
	}
	
	@GetMapping("reviewDetail")
	public String reviewDetail(HttpServletRequest req) {
		
		req.setAttribute("reviewGrade", 4);
		
		return "review/reviewDetail";
	}
}
