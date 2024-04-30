package edu.kh.pet.auth;

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
		
		return "member/login";
	}
	
	@GetMapping("emailFind")
	public String emailFind() {
		
		return "member/emailFind";
		
	}
	
	@GetMapping("join")
	public String join() {
		return "member/join";
				
	}

}
