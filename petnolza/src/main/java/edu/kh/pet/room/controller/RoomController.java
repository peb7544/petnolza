package edu.kh.pet.room.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.common.model.service.UploadFileService;
import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.service.ReserveService;
import edu.kh.pet.room.model.dto.Room;
import edu.kh.pet.room.model.dto.RoomInfo;
import edu.kh.pet.room.model.service.RoomService;
import edu.kh.pet.room.model.service.ServiceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("room")
@Slf4j
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
	
	/** 객실 관리 등록 화면
	 * @return
	 */
	@GetMapping("roomRegist")
	public String roomRegist(Model model) {
		
		/* 편의시설 조회 */
		String groupCode = "CONV";
		
		List<CodeMt> codeList = service.selectCodeList(groupCode);
		
		model.addAttribute("codeList", codeList);
		
		return "room/roomRegist";
	}
	
	/** 객실 등록
	 * @param inputRoom
	 * @param images
	 * @param ra
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("insertRoomRegist")
	public String insertRoomRegist(
				Room inputRoom,
				@RequestParam("images") List<MultipartFile> images,
				RedirectAttributes ra
			) throws IllegalStateException, IOException {
		
		int roomNo = 0;
		//service.insertRoom(inputRoom, images);
		
		for(MultipartFile img : images) {
			log.debug("ddd : " + img.getOriginalFilename());
		}
		
		String path = null;
		String message = null;
		
		if(roomNo > 0)  {
			
			path = "/room/roomUpdate/" + roomNo;
			message = "객실이 등록되었습니다";
		} else {
			
			path = "/room/roomRegist/";
			message = "객실이 등록 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	
	/** 객실 관리 수정
	 * @return
	 */
	@GetMapping("roomUpdate")
	public String roomUpdate(Model model) {
		
		/* 편의시설 조회 */
		String groupCode = "CONV";
		
		List<CodeMt> codeList = service.selectCodeList(groupCode);
		
		
		
		model.addAttribute("codeList", codeList);
		
		return "room/roomUpdate";
	}
}
