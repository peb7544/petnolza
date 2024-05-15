package edu.kh.pet.community.controller;

import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.dto.BoardImg;
import edu.kh.pet.community.model.service.CommunityService;
import edu.kh.pet.community.model.service.CommunityServiceImpl;
import edu.kh.pet.member.model.dto.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("community")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

	private final CommunityService service;
	public static String NoticeBoardCodeNo = "NOTICE";
	public static String NoticeGroupCode = "BOARD";
	public static String FaqBoardCodeNo = "QNA";

	/** 공지사항 목록
	 * @return
	 */
	@GetMapping("noticeList")
	public String noticeList(@RequestParam(value="cp", defaultValue = "1") int cp,
		Model model, @RequestParam Map<String, Object> paramMap) {

		Map<String, Object> map = null;

		if(paramMap.get("key") == null || paramMap.get("query") == "") { // 검색이 아닌 경우
			map = service.selectBoardList(NoticeBoardCodeNo, cp);
		} else {
			paramMap.put("codeNo", NoticeBoardCodeNo);
			map = service.selectSearchList(paramMap, cp);
		}

		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		model.addAttribute("codeNo", NoticeBoardCodeNo);

		return "community/noticeList";
	}
	
	
	/** 공지사항 상세
	 * @return
	 */
	@GetMapping("noticeDetail/{boardNo:[0-9]+}")
	public String noticeDetail(
			@PathVariable("boardNo")   int boardNo,
			Model model,
			RedirectAttributes ra,
			@SessionAttribute(value="loginMember", required = false) Member loginMember,
			HttpServletRequest req, // 요청에 담긴 쿠키 얻어오기
			HttpServletResponse resp // 새로운 쿠키 만들어서 응답하기
	) {

		// 1) Map으로 전달할 파라미터 묶기
		Map<String, Object> map = new HashMap<>();
		map.put("codeNo", NoticeBoardCodeNo);
		map.put("boardNo", boardNo);

		// 로그인 상태인 경우에만 memberNo 추가
		if(loginMember != null) {
			map.put("memberNo", loginMember.getMemberNo());
		}

		// 2) 서비스 호출
		Board board = service.selectOne(map);
		UploadFile uploadFile = service.selectUploadFile(boardNo);

		String path = null;

		// 조회 결과가 없는 경우
		if(board == null) {
			path = "redirect:/community/noticeList"; // 목록 재요청
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다");

			// 조회 결과가 있는 경우
		} else {

			if(loginMember == null ||
					loginMember.getMemberNo() != board.getMemberNo()) {

				// 요청에 담겨있는 모든 쿠키 얻어오기
				Cookie[] cookies = req.getCookies();
				Cookie c = null;
				for(Cookie temp : cookies) {
					// 요청에 담긴 쿠키에 "readBoardNo"가 존재 할 때
					if(temp.getName().equals("readBoardNo")) {
						c = temp;
						break;
					}
				}


				int result = 0; // 조회수 증가 결과를 저장할 변수

				// "readBoardNo"가 쿠키에 없을 때
				if(c == null) {

					// 새 쿠키 생성 ("readBoardNo" , [게시글번호])
					c = new Cookie("readBoardNo" , "[" + boardNo + "]");
					result = service.updateReadCount(boardNo);

				} else {
					// "readBoardNo"가 쿠키에 있을 때
					// "readBoardNo" : [2][30][400][2000][4000]

					// 현재 글을 처음 읽은 경우
					if(c.getValue().indexOf("[" + boardNo + "]") == -1) {

						// 해당 글 번호를 쿠키에 누적 + 서비스 호출
						c.setValue(c.getValue() + "[" + boardNo + "]");
						result = service.updateReadCount(boardNo);
					}

				}

				// 조회 수 증가 성공 / 조회 성공 시
				if( result > 0 ) {

					// 먼저 조회된 board 의 readCount 값을
					//  result 값으로 변환
					board.setReadCount(result);

					// 적용 경로 설정
					c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달

					// 수명 지정

					// 현재 시간을 얻어오기
					LocalDateTime now = LocalDateTime.now();

					// 다음날 자정
					LocalDateTime nextDayMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

					// 다음날 자정까지 남은 시간 계산 (초 단위)
					long secondsUntilNextDay = Duration.between(now, nextDayMidnight).getSeconds();

					// 쿠키 수명 설정
					c.setMaxAge((int)secondsUntilNextDay);

					resp.addCookie(c); // 응답 객체를 이용해서 클라이언트에게 전달
				}

			}


			/* ********************쿠키를 이용한 조회 수 증가 (끝)************************** */

			path = "community/noticeDetail"; // noticeDetail.html로 forward

			// board - 게시글 일반내용 + imageList + commentList
			model.addAttribute("board", board);
			model.addAttribute("uploadFile", uploadFile);



		}


		return path;
	}
	
	
	@PostMapping("updateNotice")
	public String updateNotice(@ModelAttribute Board board,
							   @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
							   RedirectAttributes ra) throws Exception {
		
		
		int result = 0;
		String message = null;
		
		if(uploadFile != null) {
			
			if(!uploadFile.isEmpty()) {
				
				service.fileUpload(uploadFile, board.getBoardNo());
				
			}
			
		}
		
		
		result = service.updateNotice(board);
		
		if(result > 0) {
			
			message = "공지사항 변경이 완료되었습니다.";
			
		} else {
			
			message = "공지사항 변경에 실패하였습니다.";
			
		}

		ra.addFlashAttribute("message", message);
		return "redirect:/community/noticeList";
	}
	
	@GetMapping("deleteNotice")
	public String deleteNotice(@RequestParam("boardNo") int boardNo,
								RedirectAttributes ra) {
		
		int result = service.deleteNotice(boardNo);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			path = "community/noticeList";
			message = "게시물 삭제가 완료되었습니다.";
		} else {
			path = "community/noticeDetail/" + boardNo;
			message = "게시물 삭제 실패하였습니다";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/" + path;
		
	}
	
	
	/** 공지사항 등록
	 * @return
	 */
	@GetMapping("noticeRegist")
	public String noticeRegist(@RequestParam("codeNo") String codeNo,
							   @SessionAttribute("loginMember") Member loginMember,
							   Model model) {
		
		int memberNo = loginMember.getMemberNo();
		
		model.addAttribute("memberNo", memberNo);
		model.addAttribute("codeNo", codeNo);
		
		return "community/noticeRegist";
	}
	
	
	@PostMapping("insertNotice")
	public String insertNotice(@ModelAttribute Board board,
							@SessionAttribute(value="loginMember", required=false) Member loginMember,
							RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo();
		
		board.setMemberNo(memberNo);
		board.setGroupCode(NoticeGroupCode);
		board.setCodeNo(NoticeBoardCodeNo);
		

		int result = service.insertNotice(board);
		
		log.debug("board : " + board);
		
		String message = null;
		
		if(result > 0) {
			
			message = "공지사항 등록이 완료되었습니다.";
			
		} else {
			
			message = "공지사항 등록에 실패하였습니다.";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/community/noticeList";
	}
	
	
	/** 자주묻는질문 목록
	 * @return
	 */
	@GetMapping("faqList")
	public String faqList(Model model, 
						  @RequestParam Map<String, Object> paramMap) {
		
		List<Board> faqList = service.selectFaqList(FaqBoardCodeNo);
		model.addAttribute("faqList", faqList);
		
		return "community/faqList";
	}
	
	/** 자주묻는질문 상세
	 * @return
	 */
	@GetMapping("faqDetail/{boardNo:[0-9]+}")
	public String faqDetail(@PathVariable("boardNo") int boardNo,
							Model model,
							RedirectAttributes ra) {
		
		Board board = service.selectFaqOne(boardNo);
		
		model.addAttribute("board", board);
		
		return "community/faqDetail";
	}
	
	
	@PostMapping("updateFaq")
	public String updateFaq(@ModelAttribute Board board,
							RedirectAttributes ra) {
		
		int result = service.updateNotice(board);
		
		String message = null;
		
		if(result > 0) message = "자주 묻는 질문 수정이 완료되었습니다.";
		else message = "자주 묻는 질문 수정에 실패하였습니다.";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/community/faqList";
	}
	
	/** 자주묻는질문 등록
	 * @return
	 */
	@GetMapping("faqRegist")
	public String faqRegist() {
		
		return "community/faqRegist";
	}
	
	@PostMapping("faqRegist")
	public String faqRegist(@ModelAttribute Board board,
							@SessionAttribute(value="loginMember", required=false) Member loginMember,
							RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo();
		
		board.setMemberNo(memberNo);
		board.setGroupCode(NoticeGroupCode);
		board.setCodeNo(FaqBoardCodeNo);
		
		int result = service.insertNotice(board);
		
		String message = null;
		
		if(result > 0) message = "자주 묻는 질문 등록이 완료되었습니다.";
		else message = "자주 묻는 질문 등록에 실패하였습니다.";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/community/faqList";
	}
	
	
	@GetMapping("faqDelete")
	public String faqDelete(@RequestParam("boardNo") int boardNo,
							RedirectAttributes ra) {
		
		int result = service.deleteNotice(boardNo);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			path = "community/faqList";
			message = "게시물 삭제가 완료되었습니다.";
		} else {
			path = "community/faqDetail/" + boardNo;
			message = "게시물 삭제 실패하였습니다";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/" + path;
	}

}
