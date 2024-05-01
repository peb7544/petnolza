package edu.kh.pet.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.member.dto.Member;

@Mapper
public interface AuthMapper {

	
	/**	로그인
	 * @param memberEmail
	 * @return loginMember
	 */
	Member login(String memberEmail);

}
