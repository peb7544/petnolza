package edu.kh.pet.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.kh.pet.member.model.dto.Member;

@Controller
public class MainController {

	@RequestMapping("/")
	public String mainPage(@SessionAttribute("loginMember") Member loginMember,
						   Model model) {
		
		String codeNo = loginMember.getCodeNo();
		
		model.addAttribute("codeNo", codeNo);
		
		return "main/intro";
	}
	
}
