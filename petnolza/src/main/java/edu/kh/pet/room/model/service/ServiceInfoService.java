package edu.kh.pet.room.model.service;

import java.util.List;

import edu.kh.pet.room.model.dto.ServiceInfo;

public interface ServiceInfoService {

	// 서비스 전체 조회
	List<ServiceInfo> selectService();

}
