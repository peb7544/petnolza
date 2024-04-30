package edu.kh.pet.reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("reserve")
public class ReserveController {

	/** 객실 예약 목록
	 * @param req
	 * @return
	 */
	@GetMapping("reserveList")
	public String reserveList() {
		
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
