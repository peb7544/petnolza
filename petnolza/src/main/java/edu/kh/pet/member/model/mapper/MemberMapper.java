package edu.kh.pet.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	
	
	/** 회원 수 조회
	 * @return
	 */
	int getListCount();

}
