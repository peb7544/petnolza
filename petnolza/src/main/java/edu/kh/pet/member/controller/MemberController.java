package edu.kh.pet.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService service;

	/** 회원관리
	 * @return
	 */
	@GetMapping("memberMana")
	public String memberMana(@RequestParam(value="cp", required=false, defaultValue= "1") int cp,
							 Model model,
							 @RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = null;
		
		if(paramMap.get("key") == null) {	// 검색이 아닌 경우
			
			map = service.selectMemberList(cp);
			
		} else {
			
			map = service.searchList(paramMap, cp);
			
		}
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("memberList", map.get("memberList"));
		
		return "member/memberMana";
	}
	
	/** 회원상세정보
	 * @return
	 */
	@GetMapping("memberInfo/{memberNo:[0-9]+}")
	public String memberInfo(@PathVariable("memberNo") int memberNo,
							 Model model) {
		
		Member member = service.selectMember(memberNo);
		
		return "member/memberInfo";
	}
}
