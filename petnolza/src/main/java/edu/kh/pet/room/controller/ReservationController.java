package edu.kh.pet.room.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.pet.room.model.service.ReservationService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("reservation")
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService service;

	/** 회원 예약 관리 조회
	 * @param cp
	 * @param model
	 * @return
	 */
	@GetMapping("memberRe")
	public String memberRe(
				@RequestParam(value="cp", required=false, defaultValue="1") int cp,
				@RequestParam Map<String, Object> paramMap,
				Model model
			) {
		
		Map<String, Object> map = service.selectReserveList(paramMap, cp);
		
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("reserveList", map.get("reserveList"));
		
		return "reservation/memberRe";
	}
}
