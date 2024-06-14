package edu.kh.pet.reserve.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.reserve.model.dto.Review;
import edu.kh.pet.reserve.model.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

	private final ReviewService service;

	/*
	 * Controller / Service(Interface) / ServiceImple(Class) / Mapper(Class) /
	 * Mapper.xml
	 */

	/**
	 * 리뷰목록조회
	 * 
	 * @param req
	 * @return
	 */
	@GetMapping("reviewList/{roomId:[0-9]+}")
	public String reviewList(
			@PathVariable("roomId") int roomId,
			Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		// 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectReviewList(roomId, cp);

		model.addAttribute("roomNo", roomId);
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("reviewList", map.get("reviewList"));

		return "review/reviewList";
	}

	// review/reviewDetail/리뷰번호
	/**
	 * 리뷰상세
	 * 
	 * @param reviewNo 리뷰번호
	 * @param model
	 * @param ra
	 * @return
	 */
	@GetMapping("reviewDetail/{reviewNo:[0-9]+}")
	public String reviewDetail(
			@PathVariable("reviewNo") int reviewNo,
			@SessionAttribute("loginMember") Member loginMember,
			Model model,
			RedirectAttributes ra) {

		int loginMemberNo = loginMember.getMemberNo();

		// 서비스 호출
		Review review = service.selectReviewDetail(reviewNo);

		model.addAttribute("review", review);
		//model.addAttribute("loginMemberNo", loginMemberNo);

		return "review/reviewDetail";
	}

	@GetMapping("reviewUpdate")
	public String reivewUpdate(@RequestParam("reviewNo") int reviewNo,
			Model model) {

		Review review = service.selectReviewDetail(reviewNo);

		model.addAttribute("review", review);

		return "review/reviewUpdate";
	}

	@PostMapping("reviewUpdate")
	public String reviewUpdate(
			@ModelAttribute Review inputReview,
			Model model,
			RedirectAttributes ra) {

		int result = service.reviewUpdate(inputReview);

		String message = null;

		if (result > 0)
			message = "후기 수정을 완료했습니다";

		else
			message = "후기 수정에 실패했습니다";

		ra.addFlashAttribute("message", message);

		return "redirect:/review/reviewDetail/" + inputReview.getReviewNo();
	}
	
	@GetMapping("reviewInsrt")
	public String reviewInsert() {
	
		return "review/reviewInsert";
	}


	@PostMapping("reviewInsert/{roomId:[0-9]+}")
	public String reviewInsert(
			@ModelAttribute Review inputReview,
			@SessionAttribute("loginMember") Member loginMember,
			@PathVariable("roomId") int roomId,
			Model model,
			RedirectAttributes ra) {

		int loginMemberNo = loginMember.getMemberNo();

		/*Integer maxReviewNo = service.getMaxNoForInsert();
		if (maxReviewNo == null) {
			maxReviewNo = 0;
		}
		maxReviewNo++;*/
		
		log.debug("ddd");

		int result = service.reviewInsert(inputReview);
		/*inputReview.setReviewNo(maxReviewNo);*/
		inputReview.setMemberNo(loginMemberNo);
		inputReview.setRoomId(roomId);

		String message = null;

		if (result > 0)
			message = "후기 추가를 완료했습니다";

		else
			message = "후기 추가 실패했습니다";

		ra.addFlashAttribute("message", message);

		return "redirect:/review/reviewDetail/";
	}

	@GetMapping("reviewdeleteReview")
	public String reviewdeleteReview(@RequestParam("reviewNo") int reviewNo,
			@RequestParam("roomId") int roomId,
			Model model,
			RedirectAttributes ra) {

		log.debug("roomId : " + roomId);

		int result = service.selectReviewDelete(reviewNo);

		String message = null;

		if (result > 0)
			message = "삭제 완료";

		else
			message = "삭제에 실패하였습니다";

		ra.addFlashAttribute("message", message);

		return "redirect:/review/reviewList/" + roomId;
	}
}
