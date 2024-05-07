package edu.kh.pet.reserve.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.reserve.model.dto.Reserve;

@Mapper
public interface ReserveMapper {
	
	/** 객실 예약 목록 조회
	 * @return reserveList
	 */
	List<Reserve> selectReserveList(Map<String, Object> paramList);
}
