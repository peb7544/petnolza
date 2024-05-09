package edu.kh.pet.room.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

	/** 게시글 수
	 * @return
	 */
	int getListCount();

}
