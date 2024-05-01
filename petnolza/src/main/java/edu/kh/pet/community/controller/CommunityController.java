package edu.kh.pet.community.controller;

import edu.kh.pet.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("community")
@RequiredArgsConstructor
public class CommunityController {

	private final CommunityService communityService;

	/** 공지사항 목록
	 * @return
	 */
	@GetMapping("noticeList")
	public String noticeList(@RequestParam(value="cp", defaultValue = "1") int cp,
		Model model, @RequestParam Map<String, Object> paramMap) {

		Map<String, Object> map = null;

		if(paramMap.get("key") == null) { // 검색이 아닌 경우
			map = communityService.selectBoardList("NOTICE", cp);
		} else {
			paramMap.put("boardCode", "NOTICE");
			map = communityService.selectSearchList(paramMap, cp);
		}

		model.addAttribute("pagination", map.get("paginamtion"));
		model.addAttribute("boardList", map.get("boardList"));

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
