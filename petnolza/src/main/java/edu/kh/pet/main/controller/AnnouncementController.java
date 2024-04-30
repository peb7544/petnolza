package edu.kh.pet.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("announcement")
public class AnnouncementController {

	/** 공지사항 목록
	 * @return
	 */
	@GetMapping("announcementList")
	public String announcementList() {
		
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
