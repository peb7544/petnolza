package edu.kh.pet.reserve.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.pet.reserve.model.dto.Reserve;

public interface ReserveService {
	
	/** 객실 예약 목록 조회
	 * @return reserveList
	 */
	List<Reserve> selectReserveList(Map<String, Object> paramList);

}
