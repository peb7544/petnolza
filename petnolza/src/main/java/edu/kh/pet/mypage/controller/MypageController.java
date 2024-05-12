package edu.kh.pet.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.mypage.model.dto.Mtm;
import edu.kh.pet.mypage.model.service.MypageService;
import edu.kh.pet.reserve.model.dto.Reserve;
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
	public String reserveList(
				@RequestParam(value="cp", required=false, defaultValue="1") int cp,
				Model model,
				@SessionAttribute("loginMember") Member loginMember
			) {
		
		// 서비스 호출
		Map<String, Object> map = service.selectReserveList(loginMember.getMemberNo(), cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("reserveList", map.get("reserveList"));
		
		return "mypage/reserveList";
	}
	
	/** 예약 내역 취소 및 결제
	 * @return
	 */
	@ResponseBody
	@PutMapping("reserveUpdate")
	public int reserveUpdate(
				@RequestBody Reserve reserve
			) {
		
		String status = reserve.getStatus(); // 버튼 종류
		int reserveNo = reserve.getReserveNo();
		int result = 0;
		
		if(status.equals("취소")) result = service.reserveCancel(reserveNo); // 예약 취소
		else result = service.reservePayment(reserveNo); // 예약 결제
		
		return result;
	}
	
	/** 후기 등록
	 * @return
	 */
	@GetMapping("reviewWrite/{roomNo:[0-9]+}")
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
		
		return "redirect:/mypage/memberUpdate";
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
	@ResponseBody
	@PostMapping("mtmInsert")
	public int mtmInsert(@RequestBody Mtm mtm) {
		
		return service.mtmInsert(mtm);
	}
	
	/** 1:1문의
	 * @return
	 */
	@GetMapping("mtmList")
	public String mtmList(
				@RequestParam(value="cp", required=false, defaultValue="1") int cp,
				Model model,
				@SessionAttribute("loginMember") Member loginMember
			) {
		
		int memberNo = loginMember.getMemberNo();
		
		// 1:1 문의 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectMtmList(memberNo, cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("mtmList", map.get("mtmList"));
		
		// 자주 묻는 질문 서비스 호출 후 결과 반환
		List<Board> qnaList = service.selectQnaList();
				
		model.addAttribute("qnaList", qnaList);
		
		return "mypage/mtmList";
	}
	
	/** 1:1문의 상세
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
	
	/** 1:1 문의 수정 화면
	 * @param mtmNo
	 * @param model
	 * @param ra
	 * @return
	 */
	@GetMapping("mtmUpdate/{mtmNo:[0-9]+}")
	public String mtmUpdate(
				@PathVariable("mtmNo") int mtmNo,
				Model model,
				RedirectAttributes ra
			) {
		
		// 1:1 문의 상세 서비스 호출 후 결과 반환
		Mtm mtm = service.selectMtmDetail(mtmNo);
		
		model.addAttribute("mtm", mtm);
		
		return "mypage/mtmUpdate";
	}
	
	/** 1:1문의 수정
	 * @param inputMtm
	 * @param ra
	 * @return 
	 */
	@ResponseBody
	@PostMapping("mtmUpdate")
	public int mtmUpdate(@RequestBody Mtm mtm) {
		
		return service.mtmUpdate(mtm);
	}
	
	/** 1:1문의 삭제
	 * @param inputMtm
	 * @param ra
	 * @return 
	 */
	@ResponseBody
	@DeleteMapping("mtmDelete")
	public int mtmDelete(@RequestBody int mtmNo) {
		
		return service.mtmDelete(mtmNo);
	}
	
	/*********************************************************************************************/
}
