package edu.kh.pet.room.model.mapper;

import java.util.List;
import java.util.Map;

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

	/** 객실 상세 조회
	 * @param roomId
	 * @return
	 */
	Room selectRoomDetail(int roomId);

	/** 객실 수정
	 * @param insertRoom
	 * @return
	 */
	int updateRoomUpdate(Room inputRoom);

	/** 객실 기존 이미지 삭제
	 * @param map
	 * @return
	 */
	int deleteImage(Map<String, Object> map);

	/** 객실 이미지 수정
	 * @param img
	 * @return
	 */
	int updateImage(UploadFile img);

	
	/** 객실 이미지 삽입
	 * @param img
	 * @return
	 */
	int insertImage(UploadFile img);

	/** 편의시설 개수
	 * @param inputRoom
	 * @return
	 */
	int roomInfoCnt(Room inputRoom);

	/** 기존 편의시설 삭제
	 * @param inputRoom
	 * @return
	 */
	int deleteRoomInfo(Room inputRoom);
	
	/** 대표 이미지 수정
	 * @param img
	 * @return
	 */
	int updateThumbnail(UploadFile img);

	/** 객실 삭제
	 * @param roomId
	 * @return
	 */
	int deleteRoomDelete(int roomId);
	
	

}
