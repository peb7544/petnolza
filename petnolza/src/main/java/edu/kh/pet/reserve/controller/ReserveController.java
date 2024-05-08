package edu.kh.pet.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.common.model.service.UploadFileService;
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
				@RequestParam(value="inputRoomNm", required=false) String inputRoomNm
			) {
		
		if(inputStart == "") inputStart = null;
		if(inputEnd == "") inputEnd = null;
		if(inputRoomNm == "") inputRoomNm = null;
			
		
		Map<String, Object> paramList = new HashMap<>();
		
		paramList.put("inputStart", inputStart);
		paramList.put("inputEnd", inputEnd);
		paramList.put("inputRoomNm", inputRoomNm);
		
		// 객실 예약 목록 조회 서비스 호출 후 결과 반환
		List<Reserve> reserveList = service.selectReserveList(paramList);
		
		model.addAttribute("reserveList", reserveList);
		
		return "reserve/reserveList";
	}
	
	/** 예약(결제하기)
	 * @param req
	 * @return
	 */
	@GetMapping("reserveRegist/{roomId:[0-9]+}")
	public String reserveRegist(
				Model model,
				@PathVariable("roomId") int roomId,
				@RequestParam(value="reserveStart", required=false) String reserveStart,
				@RequestParam(value="reserveEnd", required=false) String reserveEnd,
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
		
		// 예약날짜
		reserve.setReserveStart(reserveStart);
		reserve.setReserveEnd(reserveEnd);
		
		model.addAttribute("reserve", reserve);
		model.addAttribute("uploadFileList", uploadFileList);
		model.addAttribute("serviceInfoList", serviceInfoList);		
		
		return "reserve/reserveRegist";
	}
}
