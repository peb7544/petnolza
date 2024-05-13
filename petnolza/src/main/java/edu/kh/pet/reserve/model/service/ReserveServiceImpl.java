package edu.kh.pet.reserve.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.mapper.ReserveMapper;
import edu.kh.pet.room.model.dto.ServiceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
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

	// 객실 예약 등록
	@Override
	public int insertRegist(Reserve reserve) {
		
		// 객실 예약 등록
		int result = mapper.insertRegist(reserve);
		
		if(result == 0) return 0;
		
		// 서비스가 신청된 경우
		List<ServiceInfo> serviceInfoList = null;
			
		if(reserve.getServiceNo() != null) {
			
			// 신청된 예약 번호 (mapper.xml에서 <selectKey> 태그를 이용해서 생성됨)
			int reserveNo = reserve.getReserveNo(); 
			
			serviceInfoList = new ArrayList<>();
			
			for(Integer i : reserve.getServiceNo()) {
				
				ServiceInfo serviceInfo = new ServiceInfo();
				
				serviceInfo.setServiceNo(i);
				serviceInfo.setReserveNo(reserveNo); 
				
				serviceInfoList.add(serviceInfo);
				
				result = mapper.insertService(serviceInfo);
			}
			
		}
			
		
		
		return result;
	}

}
