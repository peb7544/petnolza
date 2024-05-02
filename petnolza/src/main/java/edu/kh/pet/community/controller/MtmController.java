package edu.kh.pet.community.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.community.model.service.MtmService;
import edu.kh.pet.mypage.model.service.MypageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("mtm")
@RequiredArgsConstructor
public class MtmController {
	
	private final MtmService service;

	/** 관리자 1:1문의
	 * @return
	 */
	@GetMapping("qna")
	public String qna(
				@RequestParam(value="cp", required=false, defaultValue="1") int cp,
				Model model
			) {
		
		// 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectMtmList(cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("mtmList", map.get("mtmList"));
		
		return "mtm/qna";
	}
	
	/** 관리자 1:1문의 답변 등록
	 * @return
	 */
	@GetMapping("qnaRegi")
	public String qnaRegi() {
		
		return "mtm/qnaRegi";
	}
}
