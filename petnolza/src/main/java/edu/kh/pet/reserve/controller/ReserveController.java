package edu.kh.pet.reserve.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.service.ReserveService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("reserve")
@RequiredArgsConstructor
@Slf4j
public class ReserveController {
	
	private final ReserveService service;

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
		
		log.debug("inputStart : " + inputStart);
		log.debug("inputEnd : " + inputEnd);
		log.debug("inputRoomNm : " + inputRoomNm);
		
		if(inputStart == "") inputStart = null;
		if(inputEnd == "") inputEnd = null;
		if(inputRoomNm == "") inputRoomNm = null;
		
		log.debug("inputStart2 : " + inputStart);
		log.debug("inputEnd2 : " + inputEnd);
		log.debug("inputRoomNm2 : " + inputRoomNm);
			
		
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
	@GetMapping("reserveRegist")
	public String reserveRegist(HttpServletRequest req) {
		
		req.setAttribute("reviewGrade", 4);
		
		return "reserve/reserveRegist";
	}
}
