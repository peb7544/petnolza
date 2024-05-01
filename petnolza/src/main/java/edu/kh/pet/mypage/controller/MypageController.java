package edu.kh.pet.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.mypage.model.dto.MTM;
import edu.kh.pet.mypage.model.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("mypage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
	
	private final MypageService service;
	
	/**************************************  화면전환   ******************************************/

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
	
	/** 1:1문의
	 * @return
	 */
	@GetMapping("mtmList")
	public String mtmList() {
		
		return "mypage/mtmList";
	}
	
	/** 1:1문의 등록
	 * @return
	 */
	@GetMapping("mtmWrite")
	public String mtmWrite() {
		
		return "mypage/mtmWrite";
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
	
	/*********************************************************************************************/
	
	
	/******************************************  1:1문의  ****************************************/
	
	/** 1:1문의 등록
	 * @param inputMtm
	 * @param ra
	 * @return 
	 */
	@PostMapping("mtmInsert")
	public String mtmInsert(
				MTM inputMtm,
				RedirectAttributes ra
			) {
		
		// 로그인한 회원번호 세팅
		inputMtm.setMemberNo(2);  // session 처리 후 수정 inputMtm.setMemberNo(loginMember.getMemberNo());
		
		// 서비스 메서드 호출 후 문의번호 받기
		int mtmNo = service.mtmInsert(inputMtm);
		
		return "";
	}
	
	/*********************************************************************************************/
}
