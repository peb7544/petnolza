package edu.kh.pet.room.model.service;

import java.util.List;

import edu.kh.pet.room.model.dto.ServiceInfo;

public interface ServiceInfoService {

	/** 서비스 전체 조회
	 * @return
	 */
	List<ServiceInfo> selectService();

	/** 서비스 등록
	 * @return
	 */
	int insertService(ServiceInfo serviceInfo);

	/** 서비스 삭제
	 * @param serviceNo
	 * @return
	 */
	int deleteService(int serviceNo);

}
