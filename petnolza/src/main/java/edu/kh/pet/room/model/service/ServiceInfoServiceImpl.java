package edu.kh.pet.room.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.room.model.dto.ServiceInfo;
import edu.kh.pet.room.model.mapper.ServiceInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServiceInfoServiceImpl implements ServiceInfoService {
	
	private final ServiceInfoMapper mapper;
	
	// 서비스 전체조회
	@Override
	public List<ServiceInfo> selectService() {
		// TODO Auto-generated method stub
		return mapper.selectService();
	}
}
