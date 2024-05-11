package edu.kh.pet.room.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.kh.pet.common.model.dto.CodeMt;
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

}
