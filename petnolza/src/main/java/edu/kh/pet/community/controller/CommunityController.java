package edu.kh.pet.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("community")
public class CommunityController {

	/** 공지사항 목록
	 * @return
	 */
	@GetMapping("noticeList")
	public String noticeList() {
		
		return "community/noticeList";
	}
	
	/** 공지사항 상세
	 * @return
	 */
	@GetMapping("noticeDetail")
	public String noticeDetail() {
		
		return "community/noticeDetail";
	}
	
	/** 공지사항 등록
	 * @return
	 */
	@GetMapping("noticeRegist")
	public String noticeRegist() {
		
		return "community/noticeRegist";
	}
	
	/** 자주묻는질문 목록
	 * @return
	 */
	@GetMapping("faqList")
	public String faqList() {
		
		return "community/faqList";
	}
	
	/** 자주묻는질문 상세
	 * @return
	 */
	@GetMapping("faqDetail")
	public String faqDetail() {
		
		return "community/faqDetail";
	}
	
	/** 자주묻는질문 등록
	 * @return
	 */
	@GetMapping("faqRegist")
	public String faqRegist() {
		
		return "community/faqRegist";
	}

}
