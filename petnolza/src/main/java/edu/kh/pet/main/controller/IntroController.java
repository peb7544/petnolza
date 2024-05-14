package edu.kh.pet.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.service.CommunityService;
import edu.kh.pet.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("main")
@RequiredArgsConstructor
public class IntroController {
	
	private final CommunityService service;
	
	public static String FaqBoardCodeNo = "QNA";
	
	/** 자주묻는질문
	 * @return
	 */
	@GetMapping("qna")
	public String qna(Model model) {
		
		List<Board> faqList = service.selectFaqList(FaqBoardCodeNo); 
		
		model.addAttribute("qnaList", faqList);
		
		return "main/qna";
	}
	
	/** 서비스
	 * @return
	 */
	@GetMapping("service")
	public String service() {
		
		return "main/service";
	}
	
	/** 오시는길
	 * @return
	 */
	@GetMapping("route")
	public String route() {
		
		return "main/route";
	}
	
	@GetMapping("comingSoon")
	public String comingSoon(@SessionAttribute("loginMember") Member loginMember,
							Model model) {
		
		model.addAttribute("codeNo", loginMember.getCodeNo());
		
		return "main/comingSoon";
	}
}
