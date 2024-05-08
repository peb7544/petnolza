package edu.kh.pet.reserve.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.mapper.ReserveMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReserveServiceImpl implements ReserveService {
	
	private final ReserveMapper mapper;
	
	// 객실 예약 목록 조회
	@Override
	public List<Reserve> selectReserveList(Map<String, Object> paramList) {
		
		List<Reserve> reserveList = mapper.selectReserveList(paramList);
		
		// 편의시설
		for(Reserve reserve : reserveList) {
			
			if(reserve.getCodeNameList() != null) {
				
				String[] codeArr = reserve.getCodeNameList().split(",");
				
				List<String> codeList = new ArrayList<>();
				
				for(int i=0; i<codeArr.length; i++) {
					
					codeList.add(codeArr[i]);
				}
				
				reserve.setCodeName(codeList);
			
			}
		}
		
		return reserveList;
	}

	// 객실 예약 상세 조회
	@Override
	public Reserve selectReserveDetail(int roomId) {
		
		Reserve reserve = mapper.selectReserveDetail(roomId);
		
		// 편의시설
		if(reserve.getCodeNameList() != null) {
			
			String[] codeArr = reserve.getCodeNameList().split(",");
			
			List<String> codeList = new ArrayList<>();
			
			for(int i=0; i<codeArr.length; i++) {
				
				codeList.add(codeArr[i]);
			}
			
			reserve.setCodeName(codeList);
		
		}
		
		return reserve;
	}

}
