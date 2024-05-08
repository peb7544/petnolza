package edu.kh.pet.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttribute;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.mypage.model.dto.Mtm;
import edu.kh.pet.mypage.model.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("mypage")
@RequiredArgsConstructor
@Slf4j
@SessionAttributes({"loginMember"})
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
	
	/**	마이페이지 - 정보수정
	 * @return
	 */
	@GetMapping("memberUpdate")
	public String memberUpdate(@SessionAttribute("loginMember") Member loginMember,
							   Model model) {
		
		String memberAddr = loginMember.getMemberAddr();
		
		if(memberAddr != null) {
			
			String[] arr = memberAddr.split("\\^\\^\\^");
			
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);
			
		}
		
		return "mypage/memberUpdate";
	}
	
	
	/** 회원 정보 수정
	 * @param inputMember
	 * @param loginMember
	 * @param memberAddr
	 * @param ra
	 * @return
	 */
	@PostMapping("memberUpdate")
	public String memberUpdate(	@ModelAttribute Member inputMember,
								@SessionAttribute("loginMember") Member loginMember,
								@RequestParam("memberAddr") String[] memberAddr,
								RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo();
		inputMember.setMemberNo(memberNo);
		
		int result = service.memberUpdate(inputMember, memberAddr);
		
		String message = null;
		
		if(result > 0) {
			message = "회원정보 수정이 완료되었습니다.";
			
			loginMember.setMemberName( inputMember.getMemberName() );
			loginMember.setMemberNickname( inputMember.getMemberNickname() );
			loginMember.setMemberTel( inputMember.getMemberTel() );
			loginMember.setMemberAddr( inputMember.getMemberAddr() );
			
		} else {
			message = "회원정보 수정에 실패하였습니다.";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	@GetMapping("pwUpdate")
	public String pwUpdate() {
		
		return "mypage/pwUpdate";
	}
	
	
	/**	비밀번호 변경
	 * @param paramMap
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@PostMapping("pwUpdate")
	public String pwUpdate(@RequestParam Map<String, Object> paramMap,
						   @SessionAttribute("loginMember") Member loginMember,
						   RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo();
		
		int result = service.pwUpdate(paramMap, memberNo);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			
			path = "memberUpdate";
			message = "비밀번호 변경이 완료되었습니다.";
			
		} else {
			
			path = "/mypage/pwUpdate";
			message = "현재 비밀번호가 일치하지 않습니다";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
		
	}
	
	
	/** 탈퇴하기
	 * @return
	 */
	@GetMapping("withdrawal")
	public String withdrawal() {
		
		return "mypage/withdrawal";
	}
	
	@PostMapping("withdrawal")
	public String withdrawal(@RequestParam("memberPassword") String memberPassword,
							 @SessionAttribute("loginMember") Member loginMember,
							 SessionStatus status,
							 RedirectAttributes ra) {
		
		int memberNo = loginMember.getMemberNo();
		
		int result = service.withdrawal(memberPassword, memberNo);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			
			message = "회원 탈퇴가 완료되었습니다.";
			path = "/";
			
			status.setComplete();
		} else {
			
			message = "비밀번호가 일치하지 않습니다.";
			path = "withdrawal";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
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
				RedirectAttributes ra,
				@SessionAttribute("loginMember") Member loginMember
			) {
		
		// 로그인한 회원번호 세팅
		inputMtm.setMemberNo(loginMember.getMemberNo());
		
		// 서비스 메서드 호출 후 문의번호 받기
		int mtmNo = service.mtmInsert(inputMtm);
		
		String path = null;
		String message = null;
		
		if(mtmNo > 0) {
			
			path = "/mypage/mtmDetail/" + mtmNo;
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
		
		// 1:1 문의 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectMtmList(memberNo, cp); // 로그인ID 변경
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("mtmList", map.get("mtmList"));
		
		// 자주 묻는 질문 서비스 호출 후 결과 반환
		List<Board> qnaList = service.selectQnaList();
		
		model.addAttribute("qnaList", qnaList);
		
		return "mypage/mtmList";
	}
	
	/** 1:1문의 답변
	 * @return
	 */
	@GetMapping("mtmDetail/{mtmNo:[0-9]+}")
	public String mtmDetail(
				@PathVariable("mtmNo") int mtmNo,
				Model model,
				RedirectAttributes ra
			) {
		
		// 1:1 문의 상세 서비스 호출 후 결과 반환
		Mtm mtm = service.selectMtmDetail(mtmNo);
		
		model.addAttribute("mtm", mtm);
		
		return "mypage/mtmDetail";
	}
	
	/*********************************************************************************************/
}
