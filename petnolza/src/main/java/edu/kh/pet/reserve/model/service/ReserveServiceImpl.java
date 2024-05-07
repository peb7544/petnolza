package edu.kh.pet.reserve.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.reserve.controller.ReserveController;
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
	public List<Reserve> selectReserveList() {
		
		List<Reserve> reserveList = mapper.selectReserveList();
		
		for(Reserve reserve : reserveList) {
			
			/*String[] codeArr = reserve.getCodeNameList().split(",");
			
			List<String> codeList = new ArrayList<>();
			
			for(int i=0; i<codeArr.length; i++) {
				
				codeList.add(codeArr[i]);
			}
			
			reserve.setCodeName(codeList);*/
		}
		
		
		
		return reserveList;
	}

	// 객실 예약 검색 조회
	@Override
	public List<Reserve> searchReserveList(String reserveStart, String reserveEnd, String inputRoomNm) {
		
		return mapper.searchReserveList(reserveStart, reserveEnd, inputRoomNm);
	}

}
