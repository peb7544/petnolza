package edu.kh.pet.room.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kh.pet.room.model.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImple implements ReservationService {

	
	private final ReservationMapper mapper;
	
	// 회원 예약 관리 조회
	@Override
	public Map<String, Object> selectReserveList(int cp) {
		
		// 게시글 수 조회
		int listCount = mapper.getListCount();
		
		return null;
	}

}
