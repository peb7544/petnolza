package edu.kh.pet.community.model.service;

import java.util.Map;

public interface MtmService {

	/** 관리자 1:1문의
	 * @param cp
	 * @return map
	 */
	Map<String, Object> selectMtmList(int cp);

}
