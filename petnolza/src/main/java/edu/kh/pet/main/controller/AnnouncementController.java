package edu.kh.pet.main.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.service.CommunityServiceImpl;
import edu.kh.pet.member.model.dto.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("announcement")
@RequiredArgsConstructor
public class AnnouncementController {
	
	private final CommunityServiceImpl service;
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
			
		} else {
			
			paramMap.put("codeNo", NoticeBoardCodeNo);
			map = service.selectSearchList(paramMap, cp);
			
		}
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		return "announcement/announcementList";
	}
	
	/** 공지사항 상세
	 * @return
	 */
	@GetMapping("announcementDetail/{boardNo:[0-9]+}")
	public String announcementDetail(@PathVariable("boardNo") int boardNo,
									 Model model,
									 RedirectAttributes ra,
									 @SessionAttribute(value="loginMember", required = false) Member loginMember,
									 HttpServletRequest req,
									 HttpServletResponse resp) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("codeNo", NoticeBoardCodeNo);
		map.put("boardNo", boardNo);
		
		if(loginMember != null) {
			map.put("memberNo", loginMember.getMemberNo());
		}
		
		Board board = service.selectOne(map);
		
		String path = null;
		
		if(board == null) {
			
			path = "redirect:/announcement/announcementList";
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다.");
			
		} else {
			
			if(loginMember == null || loginMember.getMemberNo() != board.getMemberNo()) {
				
				Cookie[] cookies = req.getCookies();
				
				Cookie c = null;
				
				for(Cookie temp : cookies) {
					
					if(temp.getName().equals("readBoardNo")) {
						c = temp;
						break;
					}
					
				}
				
				int result = 0;
				
				if(c == null) {
					
					c = new Cookie("readBoardNo", "[" + boardNo + "]");
					result = service.updateReadCount(boardNo);
					
				} else {
					
					if(c.getValue().indexOf("[" + boardNo + "]") == -1) {
						
						c.setValue(c.getValue() + "[" + boardNo + "]");
						result = service.updateReadCount(boardNo);
						
					}
					
				}
				
				if (result > 0) {
				
					board.setReadCount(result);
					
					c.setPath("/");
					
					LocalDateTime now = LocalDateTime.now();
					
					LocalDateTime nextDayMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(result).withNano(0);
					
					long secondsUntilNextDay = Duration.between(now, nextDayMidnight).getSeconds();
					
					c.setMaxAge( (int)secondsUntilNextDay );
					
					resp.addCookie(c);
					
				}
				
			}
			
			path = "announcement/announcementDetail";
			
			model.addAttribute("board", board);
			
			
			
		}
		
		return path;
	}
}






















