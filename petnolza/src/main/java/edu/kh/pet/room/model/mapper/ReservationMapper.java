package edu.kh.pet.room.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.reserve.model.dto.Reserve;

@Mapper
public interface ReservationMapper {

	/** 게시글 수
	 * @return
	 */
	int getListCount(Map<String, Object> paramMap);

	/** 회원 예약 정보
	 * @param rowBounds
	 * @return
	 */
	List<Reserve> selectReserveList(Map<String, Object> paramMap, RowBounds rowBounds);

}
