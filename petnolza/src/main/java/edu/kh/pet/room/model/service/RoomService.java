package edu.kh.pet.room.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.room.model.dto.Room;

public interface RoomService {

	/** 코드 리스트 조회
	 * @param groupCode
	 * @return
	 */
	List<CodeMt> selectCodeList(String groupCode);

	/** 객실 등록
	 * @param inputRoom
	 * @param images
	 * @return
	 */
	int insertRoom(Room inputRoom, List<MultipartFile> images) throws IllegalStateException, IOException;

	/** 객실 상세
	 * @param roomId
	 * @return
	 */
	Room selectRoomDetail(int roomId);

	/** 객실 수정
	 * @param insertRoom
	 * @param images
	 * @param deleteOrder 
	 * @return
	 */
	int updateRoomUpdate(Room inputRoom, List<MultipartFile> images, String deleteOrder, String orderList, String upList,UploadFile inputUploadFile) throws IllegalStateException, IOException;

	/** 객실 삭제
	 * @param roomId
	 * @return
	 */
	int deleteRoomDelete(int roomId);

}
