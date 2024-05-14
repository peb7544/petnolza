package edu.kh.pet.reserve.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.reserve.model.dto.Review;
import edu.kh.pet.reserve.model.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewService service;
	
	
	/* Controller / Service(Interface) / ServiceImple(Class) / Mapper(Class) / Mapper.xml */
	
	
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
	
	//review/reviewDetail/리뷰번호
	/** 리뷰상세
	 * @param reviewNo 리뷰번호
	 * @param model 
	 * @param ra
	 * @return
	 */
	@GetMapping("reviewDetail/{reviewNo:[0-9]+}")
	public String reviewDetail(
				@PathVariable("reviewNo") int reviewNo,
				Model model ,
				RedirectAttributes ra
			) {
		
		// 서비스 호출
		Review review = service.selectReviewDetail(reviewNo);
		
		model.addAttribute("review", review);
		
		return "review/reviewDetail";
	}
}
