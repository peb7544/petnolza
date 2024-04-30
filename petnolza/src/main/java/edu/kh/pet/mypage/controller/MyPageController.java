package edu.kh.pet.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mypage")
public class MyPageController {

	/** 예약 내역 확인
	 * @return
	 */
	@GetMapping("reserveList")
	public String reserveList() {
		
		return "mypage/reserveList";
	}
	
	/** 후기 등록
	 * @return
	 */
	@GetMapping("reviewWrite")
	public String reviewWrite() {
		
		return "mypage/reviewWrite";
	}
	
	/** 1:1문의
	 * @return
	 */
	@GetMapping("mtmList")
	public String mtmList() {
		
		return "mypage/mtmList";
	}
	
	/** 1:1문의 등록
	 * @return
	 */
	@GetMapping("mtmWrite")
	public String mtmWrite() {
		
		return "mypage/mtmWrite";
	}
	
	/** 1:1문의 답변
	 * @return
	 */
	@GetMapping("mtmDetail")
	public String mtmDetail() {
		
		return "mypage/mtmDetail";
	}
}
