package edu.kh.pet.auth.model.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import edu.kh.pet.auth.model.mapper.AuthMapper;
import edu.kh.pet.member.model.dto.Member;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
	
	private final AuthMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	private final JavaMailSender mailSender;
	
	private final SpringTemplateEngine templateEngine;

	// 로그인
	@Override
	public Member login(Member inputMember) {
		
		log.debug("inputMember : " + inputMember);
		
		// 테스트
		// bcrypt.encode(문자열) : 문자열을 암호화하여 반환
		String bcryptPassword = bcrypt.encode(inputMember.getMemberPassword());
		
//		log.debug("bcryptPassword : " + bcryptPassword);
//		
//		boolean result = bcrypt.matches(inputMember.getMemberPassword(), bcryptPassword);
//		log.debug("result : " + result);
		
		Member loginMember = mapper.login(inputMember.getMemberEmail());
		
		log.debug("loginMember : " + loginMember);
		
		if (loginMember == null) return null;
		
		if (!bcrypt.matches(inputMember.getMemberPassword(), loginMember.getMemberPassword())) {
			return null;
		}
		
		loginMember.setMemberPassword(null);
		
		return loginMember;
	}

	
	// 이메일 중복검사
	@Override
	public int checkEmail(String memberEmail) {
		
		return mapper.checkEmail(memberEmail);
	}

	
	// 닉네임 중복검사
	@Override
	public int checkNickname(String memberNickname) {
		
		return mapper.checkNickname(memberNickname);
	}

	// 회원가입
	@Override
	public int join(Member inputMember, String[] memberAddr) {
		
		
		if( !inputMember.getMemberAddr().equals(",,") ) {
			
			String address = String.join("^^^", memberAddr);
			
			inputMember.setMemberAddr(address);
			
		} else {
			
			inputMember.setMemberAddr(null);
			
		}
		
		
		String encPw = bcrypt.encode(inputMember.getMemberPassword());
		
		inputMember.setMemberPassword(encPw);
		
		return mapper.join(inputMember);
	}


	
	// 이메일 찾기
	@Override
	public String emailFind(String inputTel) {
		
		return mapper.emailFind(inputTel);
	}


	// 비밀번호(임의) 찿기
	@Override
	public int pwFind(String htmlName, String inputEmail) {
		
		int result = mapper.checkEmail(inputEmail);
		
		if(result == 0) {
			
			return 0;
			
		} else {
			
			String randomPw = createRandomPw();
			
			try {
				
				// 이메일 제목
				String subject = null;

				switch(htmlName) {
				
				case "pwFind" : subject = "[petnolza] 임시 비밀번호 입니다."; break;
				
				}
				
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				helper.setTo(inputEmail);
				helper.setSubject(subject);
				
				helper.setText( loadHtml(randomPw, htmlName), true );
				
				helper.addInline("logo", new ClassPathResource("static/images/petnolzaLogo.png"));
				
				mailSender.send(mimeMessage);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				result = 0;
			}
			
//			log.debug(randomPw);
			
			String encPw = bcrypt.encode(randomPw);
			
//			log.debug(encPw);
			
			Member member = new Member();
			
			member.setMemberEmail(inputEmail);
			member.setMemberPassword(encPw);
			
			result = mapper.randomPw(member);
			
			
		}
		
		return result;
		
	}
	
	
	private String loadHtml(String randomPw, String htmlName) {
		
		Context context = new Context();
		
		context.setVariable("randomPw", randomPw);
		
		return templateEngine.process("email/" + htmlName, context);
		
	}
	
	
	
	public String createRandomPw() {
		
		String pw = "";
		
		for(int i = 0; i < 8; i++) {
			
			int sel1 = (int) (Math.random() * 4); // 0:숫자 / 1,2:영어 / 3:특수문자
			
			if(sel1 == 0) {		// 숫자
				
				int num = (int)(Math.random() * 10);
				pw += num;
				
			} else if (sel1 == 3) {		// 특수문자
				
				char ch = (char)(Math.random() * 94 + 33);
				pw += ch;
				
			} else {	// 영어
				
				char ch2 = (char)(Math.random() * 26 + 65); // A~Z
	              
				int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
              
				if(sel2 == 0) {
					ch2 = (char)(ch2 + ('a' - 'A')); // 대문자로 변경
				}
              
				pw += ch2;
				
			}
			
		}
		
		return pw;
		
	}
	

}






















