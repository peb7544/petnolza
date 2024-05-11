package edu.kh.pet.room.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import edu.kh.pet.common.model.dto.Pagination;
import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.room.model.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImple implements ReservationService {

	
	private final ReservationMapper mapper;
	
	// 회원 예약 관리 조회
	@Override
	public Map<String, Object> selectReserveList(Map<String, Object> paramMap, int cp) {
		
		// 게시글 수 조회
		int listCount = mapper.getListCount(paramMap);
		
		// Pagination 객체 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		// 페이지 목록 조회
		// - 지정된 크기 만큼 건너뛰고(offset)
		//   제한된 크기(limit)만큼의 행을 조회하는 객체
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// Mapper 메섣 호출
		List<Reserve> reserveList = mapper.selectReserveList(paramMap, rowBounds);
		
		// 서비스명
		for(Reserve reserve : reserveList) {
			
			if(reserve.getServiceNameList() != null) {
				
				String[] serviceArr = reserve.getServiceNameList().split(",");
				
				List<String> serviceList = new ArrayList<>();
				
				for(int i=0; i<serviceArr.length; i++) {
					
					serviceList.add(serviceArr[i]);
				}
				
				reserve.setServiceName(serviceList);
			
			}
		}
		
		// 목록 조회 결과
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("reserveList", reserveList);
		
		return map;
	}

}
