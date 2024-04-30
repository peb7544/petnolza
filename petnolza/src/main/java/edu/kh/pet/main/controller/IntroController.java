package edu.kh.pet.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class IntroController {
	
	/** 자주묻는질문
	 * @return
	 */
	@GetMapping("qna")
	public String qna() {
		
		return "main/qna";
	}
	
	/** 서비스
	 * @return
	 */
	@GetMapping("service")
	public String service() {
		
		return "main/service";
	}
	
	/** 오시는길
	 * @return
	 */
	@GetMapping("route")
	public String route() {
		
		return "main/route";
	}
}
