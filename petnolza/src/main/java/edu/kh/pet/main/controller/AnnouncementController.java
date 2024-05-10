package edu.kh.pet.main.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.community.model.service.CommunityService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("announcement")
@RequiredArgsConstructor
public class AnnouncementController {
	
	private final CommunityService service;
	public static String NoticeBoardCodeNo = "NOTICE";


	/** 공지사항 목록
	 * @return
	 */
	@GetMapping("announcementList")
	public String announcementList(@RequestParam(value="cp", required=false, defaultValue="1") int cp,
								   Model model,
								   @RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> map = null;
		
		if(paramMap.get("key") == null || paramMap.get("query") == "") {
			
			map = service.selectBoardList(NoticeBoardCodeNo, cp);
			
		}
		
		return "announcement/announcementList";
	}
	
	/** 공지사항 상세
	 * @return
	 */
	@GetMapping("announcementDetail")
	public String announcementDetail() {
		
		return "announcement/announcementDetail";
	}
}










