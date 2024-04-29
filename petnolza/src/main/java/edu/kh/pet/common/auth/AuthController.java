package edu.kh.pet.common.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class AuthController {
	
	@GetMapping("login")
	public String login() {
		
		return "common/login";
	}
	
	@GetMapping("emailFind")
	public String emailFind() {
		
		return "common/emailFind";
		
	}
	
	@GetMapping("join")
	public String join() {
		return "common/join";
				
	}

}
