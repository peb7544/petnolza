package edu.kh.pet.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.auth.model.service.AuthService;
import edu.kh.pet.member.model.dto.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
						Model model,
						@RequestParam(value="saveId", required=false) String saveId,
						HttpServletResponse resp) {
		
		Member loginMember = service.login(inputMember);
		
		log.debug("inputMember : " + inputMember);
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "redirect:/member/login";
		}
		
		if(loginMember != null) {
			
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			cookie.setPath("/");
			
			if(saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 30);
			} else {
				cookie.setMaxAge(0);
			}
			
			resp.addCookie(cookie);
			
		}
		 
		return "redirect:/";
	}
	
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:/";
		
	}
	
	
	
	/**	이메일 찾기 
	 * @return
	 */
	@GetMapping("emailFind")
	public String emailFind() {
		
		return "member/emailFind";
		
	}
	
	@ResponseBody
	@PostMapping("emailFind")
	public String emailFind(@RequestParam(value="inputTel", required=false) String inputTel) {
		
		
		return service.emailFind(inputTel);
	}
	
	@ResponseBody
	@PostMapping("pwFind")
	public int pwFind(@RequestBody String inputEmail) {
		
		int result = service.pwFind("pwFind",inputEmail);
		
		return result;
	}
	
	
	@GetMapping("join")
	public String join() {
		return "member/join";
				
	}
	
	
	/** 회원가입
	 * @param inputMember
	 * @param memberAddress
	 * @param ra
	 * @return
	 */
	@PostMapping("join")
	public String join(@ModelAttribute Member inputMember,
					   @RequestParam("memberAddr") String[] memberAddr,
					   RedirectAttributes ra) {
		
		int result = service.join(inputMember, memberAddr);
		String path = null;
		String message = null;
		
		if(result > 0) {
			
			message = inputMember.getMemberNickname() + "님의 가입을 환영합니다 :)";
			path = "login";
			
		} else {
			
			message = "회원가입에 실패하였습니다.";
			path = "join";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	@ResponseBody
	@GetMapping("checkEmail")
	public int checkEmail(@RequestParam("memberEmail") String memberEmail) {
		
		return service.checkEmail(memberEmail);
	}
	
	@ResponseBody
	@GetMapping("checkNickname")
	public int checkNickname(@RequestParam("memberNickname") String memberNickname) {
		
		return service.checkNickname(memberNickname);
	}
	
	@ResponseBody
	@GetMapping("checkTel")
	public int checkTel(@RequestParam("memberTel") String memberTel) {
		
		return service.checkTel(memberTel);
		
	}

}




















