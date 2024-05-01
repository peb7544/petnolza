package edu.kh.pet.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.auth.service.AuthService;
import edu.kh.pet.member.dto.Member;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@Slf4j
public class AuthController {
	
	private final AuthService service;
	
	@GetMapping("login")
	public String login() {
		
		return "member/login";
	}
	
	/** 로그인
	 * @param inputMember
	 * @param ra
	 * @param model
	 * @return "redirect:/" (메인 페이지)
	 */
	@PostMapping("login")
	public String login(@ModelAttribute Member inputMember,
						RedirectAttributes ra,
						Model model) {
		
		Member loginMember = service.login(inputMember);
		
		log.debug("inputMember : " + inputMember);
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		if(loginMember != null) {
			
			model.addAttribute("loginMember", loginMember);
			log.debug("로그인 성공");
			
		}
		 
		return "redirect:/";
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
