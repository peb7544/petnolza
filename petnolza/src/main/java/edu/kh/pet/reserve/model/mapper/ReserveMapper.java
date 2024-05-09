package edu.kh.pet.reserve.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.room.model.dto.ServiceInfo;

@Mapper
public interface ReserveMapper {
	
	/** 객실 예약 목록 조회
	 * @return reserveList
	 */
	List<Reserve> selectReserveList(Map<String, Object> paramList);

	/** 객실 예약 상세 조회
	 * @param roomId
	 * @return
	 */
	Reserve selectReserveDetail(int roomId);

	/** 객실 예약 등록
	 * @param reserve
	 * @return
	 */
	int insertRegist(Reserve reserve);

	/** 객실 서비스 예약 등록
	 * @param serviceInfoList
	 * @return
	 */
	int insertService(ServiceInfo serviceInfo);
}
