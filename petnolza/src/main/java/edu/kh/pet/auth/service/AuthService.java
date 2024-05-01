package edu.kh.pet.auth.service;

import edu.kh.pet.member.dto.Member;

public interface AuthService {

	
	/** 로그인
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);

}
