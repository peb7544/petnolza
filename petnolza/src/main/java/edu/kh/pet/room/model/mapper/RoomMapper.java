package edu.kh.pet.room.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.room.model.dto.Room;
import edu.kh.pet.room.model.dto.RoomInfo;

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

	/** 객실 편의시설 등록
	 * @param info
	 * @return
	 */
	int insertRoomInfo(List<RoomInfo> roomInfo);

	/** 객실 이미지 삽입
	 * @param uploadList
	 * @return
	 */
	int insertUploadList(List<UploadFile> uploadList);
	

}
