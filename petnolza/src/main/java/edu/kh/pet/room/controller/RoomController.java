package edu.kh.pet.room.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.common.model.service.UploadFileService;
import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.service.ReserveService;
import edu.kh.pet.room.model.service.RoomService;
import edu.kh.pet.room.model.service.ServiceInfoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("room")
public class RoomController {
	
	private final RoomService service;
	private final ReserveService reserveService;

	/** 객실 관리
	 * @return
	 */
	@GetMapping("roomList")
	public String roomList(
				Model model,
				@RequestParam(value="inputRoomNm", required=false) String inputRoomNm,
				RedirectAttributes ra
			) {
		if(inputRoomNm == "") inputRoomNm = null;
		
		Map<String, Object> paramList = new HashMap<>();
		paramList.put("inputRoomNm", inputRoomNm);
		
		// 객실 예약 목록 조회 서비스 호출 후 결과 반환
		List<Reserve> reserveList = reserveService.selectReserveList(paramList);
		
		model.addAttribute("reserveList", reserveList);
		
		return "room/roomList";
	}
	
	/** 객실 관리 등록
	 * @return
	 */
	@GetMapping("roomRegist")
	public String roomRegist(Model model) {
		
		String groupCode = "CONV";
		
		List<CodeMt> codeList = service.selectCodeList(groupCode);
		
		model.addAttribute("codeList", codeList);
		
		return "room/roomRegist";
	}
	
}
