package edu.kh.pet.reserve.model.service;

import java.util.List;

import edu.kh.pet.reserve.model.dto.Reserve;

public interface ReserveService {
	
	/** 객실 예약 목록 조회
	 * @return reserveList
	 */
	List<Reserve> selectReserveList();

	/** 객실 예약 검색 조회
	 * @param reserveEnd 
	 * @param reserveStart 
	 * @return reserveList
	 */
	List<Reserve> searchReserveList(String reserveStart, String reserveEnd, String inputRoomNm);

}
