package edu.kh.pet.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.common.model.service.UploadFileService;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.service.ReserveService;
import edu.kh.pet.room.model.dto.ServiceInfo;
import edu.kh.pet.room.model.service.ServiceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("reserve")
@RequiredArgsConstructor
@Slf4j
public class ReserveController {
	
	private final ReserveService service;
	private final ServiceInfoService serviceInfoService; // 서비스
	private final UploadFileService uploadFileService; // 파일업로드

	/** 객실 예약 목록
	 * @param req
	 * @return
	 */
	@GetMapping("reserveList")
	public String reserveList(
				Model model,
				@RequestParam(value="inputStart", required=false) String inputStart,
				@RequestParam(value="inputEnd", required=false) String inputEnd,
				@RequestParam(value="inputRoomNm", required=false) String inputRoomNm,
				@SessionAttribute(value="loginMember", required = false) Member loginMember,
				RedirectAttributes ra
			) {
		
		/*if(loginMember == null) {
			
			ra.addFlashAttribute("message", "로그인 후 이용해주세요.");
			
			return "redirect:/";
		}*/
		
		if(inputStart == "") inputStart = null;
		if(inputEnd == "") inputEnd = null;
		if(inputRoomNm == "") inputRoomNm = null;
		
		Map<String, Object> paramList = new HashMap<>();
		
		paramList.put("inputStart", inputStart);
		paramList.put("inputEnd", inputEnd);
		paramList.put("inputRoomNm", inputRoomNm);
		
		// 객실 예약 목록 조회 서비스 호출 후 결과 반환
		List<Reserve> reserveList = service.selectReserveList(paramList);
		Map<String, String> inputDate = new HashMap<>();
		
		inputDate.put("inputStart", inputStart);
		inputDate.put("inputEnd", inputEnd);
		
		model.addAttribute("reserveList", reserveList);
		model.addAttribute("inputDate", inputDate);
		
		return "reserve/reserveList";
	}
	
	/** 객실 예약 상세
	 * @param req
	 * @return
	 */
	@GetMapping("reserveRegist/{roomId:[0-9]+}")
	public String reserveRegist(
				Model model,
				@PathVariable("roomId") int roomId,
				@RequestParam(value="reserveStart", required=false) String reserveStart,
				@RequestParam(value="reserveEnd", required=false) String reserveEnd,
				@SessionAttribute(value="loginMember", required = false) Member loginMember,
				RedirectAttributes ra,
				UploadFile uploadFile
			) {
		
		// 객실 예약 상세
		Reserve reserve = service.selectReserveDetail(roomId);
		
		// 객실 이미지 조회
		uploadFile.setTableNo(roomId);
		uploadFile.setTableName("ROOM");
		
		List<UploadFile> uploadFileList = uploadFileService.selectUploadFile(uploadFile);
		
		// 객실 서비스 조회
		List<ServiceInfo> serviceInfoList = serviceInfoService.selectService();
		
		if(loginMember == null) {
			
			ra.addFlashAttribute("message", "로그인 후 이용해주세요.");
			
			return "redirect:/reserve/reserveList";
		}
		
		if(reserveStart == "" || reserveEnd == "") {
			
			ra.addFlashAttribute("message", "시작일자와 종료일자를 선택 후 검색 조회 결과에서 예약 버튼을 클릭해주세요.");
			
			return "redirect:/reserve/reserveList";
		}
		
		// 예약날짜
		reserve.setReserveStart(reserveStart);
		reserve.setReserveEnd(reserveEnd);
		
		model.addAttribute("reserve", reserve);
		model.addAttribute("uploadFileList", uploadFileList);
		model.addAttribute("serviceInfoList", serviceInfoList);		
		
		return "reserve/reserveRegist";
	}
	
	@PostMapping("reserveRegist/{roomId:[0-9]+}")
	public String reserveRegist(
				Reserve reserve,
				@RequestParam(value="serviceNo", required = false) List<Integer> serviceNo,
				@SessionAttribute(value="loginMember", required = false) Member loginMember,
				RedirectAttributes ra
			) {
		
		reserve.setMemberNo(loginMember.getMemberNo());
		reserve.setServiceNo(serviceNo);
		
		// 등록 서비스 호출
		int result = service.insertRegist(reserve);
		
		String msg = null;
		String path = null;
		
		if(result > 0) {
			msg = "객실이 예약되었습니다";
			path = "/reserve/reserveList";
		} else {
			msg = "객실 예약이 실패되었습니다";
			path = "/reserve/reserveRegist/" + reserve.getRoomId();
		}
		
		ra.addFlashAttribute("message", msg);
		
		return "redirect:" + path;
	}
}
