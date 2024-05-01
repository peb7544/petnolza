package edu.kh.pet.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.auth.mapper.AuthMapper;
import edu.kh.pet.member.dto.Member;
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
		
		// 테스트
		// bcrypt.encode(문자열) : 문자열을 암호화하여 반환
//		String bcryptPassword = bcrypt.encode(inputMember.getMemberPassword());
//		
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

}
