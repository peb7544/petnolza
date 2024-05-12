package edu.kh.pet.room.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.pet.common.model.dto.CodeMt;
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
	int insertRoom(Room inputRoom, List<MultipartFile> images);

}
