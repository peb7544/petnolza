package edu.kh.pet.auth.model.service;

import edu.kh.pet.member.model.dto.Member;

public interface AuthService {

	
	/** 로그인
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);

	
	
	/** 이메일 중복검사
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);



	/** 닉네임 중복검사
	 * @param memberNickname
	 * @return
	 */
	int checkNickname(String memberNickname);



	/** 회원가입
	 * @param inputMember
	 * @param memberAddress
	 * @return result
	 */
	int join(Member inputMember, String[] memberAddress);

}
