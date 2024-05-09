package edu.kh.pet.community.model.service;

import java.util.Map;

import edu.kh.pet.mypage.model.dto.Mtm;

public interface MtmService {

	/** 관리자 1:1문의
	 * @param cp
	 * @return map
	 */
	Map<String, Object> selectMtmList(int cp);

	/** 관리자 1:1 답변
	 * @param mtm
	 * @return
	 */
	int mtmAnswer(Mtm mtm);

}
