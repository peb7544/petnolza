package edu.kh.pet.room.model.service;

import java.util.List;

import edu.kh.pet.common.model.dto.CodeMt;

public interface RoomService {

	/** 코드 리스트 조회
	 * @param groupCode
	 * @return
	 */
	List<CodeMt> selectCodeList(String groupCode);

}
