package edu.kh.pet.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class AuthController {
	
	/** 로그인
	 * @return
	 */
	@GetMapping("login")
	public String login() {
		
		return "member/login";
	}
	
	/** 이메일/비밀번호 찾기
	 * @return
	 */
	@GetMapping("emailFind")
	public String emailFind() {
		
		return "member/emailFind";
		
	}
	
	/** 회원가입(약관동의)
	 * @return
	 */
	@GetMapping("join")
	public String join() {
		return "member/join";
				
	}

}
