package edu.kh.pet.room.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.room.model.dto.ServiceInfo;

@Mapper
public interface ServiceInfoMapper {

	/** 서비스 전체 조회
	 * @return
	 */
	List<ServiceInfo> selectService();

}
