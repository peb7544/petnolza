package edu.kh.pet.mypage.model.service;

import edu.kh.pet.mypage.model.dto.MTM;

public interface MypageService {

	/******************************************  1:1문의  ****************************************/
	/** 1:1문의 등록
	 * @param inputMtm
	 * @return mtmNo
	 */
	int mtmInsert(MTM inputMtm);
	
	/*********************************************************************************************/

}
