package edu.kh.pet.auth.model.service;

import edu.kh.pet.member.model.dto.Member;

public interface AuthService {

	
	/** 로그인
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);

}
