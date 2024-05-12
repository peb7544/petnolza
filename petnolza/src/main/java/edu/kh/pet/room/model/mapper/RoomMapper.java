package edu.kh.pet.room.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.room.model.dto.Room;

@Mapper
public interface RoomMapper {

	/** 코드 리스트 조회
	 * @param groupCode
	 * @return
	 */
	List<CodeMt> selectCodeList(String groupCode);

	/** 객실 등록
	 * @param inputRoom
	 * @return
	 */
	int insertRoom(Room inputRoom);

}
