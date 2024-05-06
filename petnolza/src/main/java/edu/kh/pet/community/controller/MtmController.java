package edu.kh.pet.community.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.community.model.service.MtmService;
import edu.kh.pet.mypage.model.dto.Mtm;
import edu.kh.pet.mypage.model.service.MypageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("mtm")
@RequiredArgsConstructor
public class MtmController {
	
	private final MtmService service;
	private final MypageService service2;

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
	@GetMapping("qnaRegi/{mtmNo:[0-9]+}")
	public String qnaRegi(
				@PathVariable("mtmNo") int mtmNo,
				Model model,
				RedirectAttributes ra
			) {
		
		// 1:1 문의 상세 서비스 호출 후 결과 반환
		Mtm mtm = service2.selectMtmDetail(mtmNo);
		
		model.addAttribute("mtm", mtm);
		
		return "mtm/qnaRegi";
	}
}
