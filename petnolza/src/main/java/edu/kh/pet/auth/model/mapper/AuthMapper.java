package edu.kh.pet.auth.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.member.model.dto.Member;

@Mapper
public interface AuthMapper {

	
	/**	로그인
	 * @param memberEmail
	 * @return loginMember
	 */
	Member login(String memberEmail);

	
	
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

}
