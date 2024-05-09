package edu.kh.pet.member.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
@Slf4j
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
							 Model model,
							 @RequestParam (value="cp", required=false, defaultValue="1") int cp) {
		
		Map<String, Object> map = service.memberReserveList(memberNo, cp);
		
//		log.debug("map : " + map);
		
		model.addAttribute("member", map.get("member"));
		model.addAttribute("reserveList", map.get("reserveList"));
		model.addAttribute("postcode", map.get("postcode"));
		model.addAttribute("address", map.get("address"));
		model.addAttribute("detailAddress", map.get("detailAddress"));
		model.addAttribute("pagination", map.get("pagination"));
		
		
		return "member/memberInfo";
	}
	
	
	@GetMapping("withdrawal")
	public String withdrawal(@RequestParam("memberNo") int memberNo,
							@RequestParam("member") Member member,
							RedirectAttributes ra) {
		
		int result = service.withdrawal(memberNo);
		
		String message = null;
		
		if(result > 0) {
			
			message = member.getMemberNickname() + " 님 탈퇴가 완료되었습니다.";
			
		} else {
			
			message = member.getMemberNickname() + " 님 탈퇴가 실패하였습니다.";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		log.debug("memberNo : " + member.getMemberNo());
		
		return "redirect:member/memberInfo/" + member.getMemberNo();
		
	}
	
}




















