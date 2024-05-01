package edu.kh.pet.mypage.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.mypage.model.dto.MTM;

@Mapper
public interface MypageMapper {
	
	/******************************************  1:1문의  ****************************************/

	/** 1:1문의
	 * @param inputMtm
	 * @return result
	 */
	int mtmInsert(MTM inputMtm);
	
	/*********************************************************************************************/

}
