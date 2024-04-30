package edu.kh.pet.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

	/** 회원관리
	 * @return
	 */
	@GetMapping("memberMana")
	public String memberMana() {
		
		return "member/memberMana";
	}
	
	/** 회원상세정보
	 * @return
	 */
	@GetMapping("memberInfo")
	public String memberInfo() {
		
		return "member/memberInfo";
	}
}
