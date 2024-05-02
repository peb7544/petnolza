package edu.kh.pet.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.mypage.model.dto.Mtm;
import edu.kh.pet.mypage.model.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("mypage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
	
	private final MypageService service;
	
	/** 예약 내역 확인
	 * @return
	 */
	@GetMapping("reserveList")
	public String reserveList() {
		
		return "mypage/reserveList";
	}
	
	/** 후기 등록
	 * @return
	 */
	@GetMapping("reviewWrite")
	public String reviewWrite() {
		
		return "mypage/reviewWrite";
	}
	
	/** 1:1문의 답변
	 * @return
	 */
	@GetMapping("mtmDetail")
	public String mtmDetail() {
		
		return "mypage/mtmDetail";
	}
	
	/**	마이페이지 - 정보수정
	 * @return
	 */
	@GetMapping("memberUpdate")
	public String memberUpdate() {
		
		return "mypage/memberUpdate";
	}
	
	
	/** 탈퇴하기
	 * @return
	 */
	@GetMapping("withdrawal")
	public String withdrawal() {
		
		return "mypage/withdrawal";
	}
	
	/******************************************  1:1문의  ****************************************/
	
	/** 1:1문의 등록 화면
	 * @return
	 */
	@GetMapping("mtmWrite")
	public String mtmWrite() {
		
		return "mypage/mtmWrite";
	}
	
	
	/** 1:1문의 등록
	 * @param inputMtm
	 * @param ra
	 * @return 
	 */
	@PostMapping("mtmInsert")
	public String mtmInsert(
				Mtm inputMtm,
				RedirectAttributes ra
			) {
		
		// 로그인한 회원번호 세팅
		inputMtm.setMemberNo(2);  // session 처리 후 수정 inputMtm.setMemberNo(loginMember.getMemberNo());
		
		// 서비스 메서드 호출 후 문의번호 받기
		int mtmNo = service.mtmInsert(inputMtm);
		
		String path = null;
		String message = null;
		
		if(mtmNo > 0) {
			
			path = "/mypage/mtmDetail";
			message = "문의가 정상적으로 접수되었습니다.";
		} else {
			
			path = "mtmInsert";
			message = "문의 접수 실패!";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	/** 1:1문의
	 * @return
	 */
	@GetMapping("mtmList")
	public String mtmList(
				@RequestParam(value="cp", required=false, defaultValue="1") int cp,
				Model model
			) {
		
		int memberNo = 2; // 로그인 처리후 변경
		
		// 1:1 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectMtmList(memberNo, cp); // 로그인ID 변경
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("mtmList", map.get("mtmList"));
		
		// 자주 묻는 질문 서비스 호출 후 결과 반환
		List<Board> qnaList = service.selectQnaList();
		
		model.addAttribute("qnaList", qnaList);
		
		return "mypage/mtmList";
	}
	
	/*********************************************************************************************/
}
