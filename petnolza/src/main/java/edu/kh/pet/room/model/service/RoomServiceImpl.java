package edu.kh.pet.room.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.room.model.dto.Room;
import edu.kh.pet.room.model.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService  {
	
	private final RoomMapper mapper;
	
	// 코드 리스트 조회
	@Override
	public List<CodeMt> selectCodeList(String groupCode) {
		
		return mapper.selectCodeList(groupCode);
	}

	// 객실 등록
	@Override
	public int insertRoom(Room inputRoom, List<MultipartFile> images) {
		
		// 객실 등록
		int roomId = mapper.insertRoom(inputRoom);
		
		return 0;
	}

}
