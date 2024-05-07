package edu.kh.pet.reserve.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.reserve.model.dto.Reserve;

@Mapper
public interface ReserveMapper {
	
	/** 객실 예약 목록 조회
	 * @return reserveList
	 */
	List<Reserve> selectReserveList();

	/** 객실 예약 검색 조회
	 * @param reserveStart
	 * @param reserveEnd
	 * @param inputRoomNm
	 * @return reserveList
	 */
	List<Reserve> searchReserveList(String reserveStart, String reserveEnd, String inputRoomNm);
}
