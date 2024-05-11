package edu.kh.pet.room.model.service;

import java.util.Map;

public interface ReservationService {

	/** 회원 예약 관리 조회
	 * @param paramMap 
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectReserveList(Map<String, Object> paramMap, int cp);

}
