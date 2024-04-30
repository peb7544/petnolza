package edu.kh.pet.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mtm")
public class MtmController {

	/** 관리자 1:1문의
	 * @return
	 */
	@GetMapping("qna")
	public String qna() {
		
		return "mtm/qna";
	}
}
