package edu.kh.pet.auth.model.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.auth.model.mapper.AuthMapper;
import edu.kh.pet.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
	
	private final AuthMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

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
	public int join(Member inputMember, String[] memberAddress) {
		
		
		if( !inputMember.getMemberAddr().equals(",,") ) {
			
			String address = String.join("^^^", memberAddress);
			
			inputMember.setMemberAddr(address);
			
		} else {
			
			inputMember.setMemberAddr(null);
			
		}
		
		String encPw = bcrypt.encode(inputMember.getMemberPassword());
		
		inputMember.setMemberPassword(encPw);
		
		return mapper.join(inputMember);
	}

}






















