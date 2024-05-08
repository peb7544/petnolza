package edu.kh.pet.common.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.pet.common.model.dto.UploadFile;

@Mapper
public interface UploadFileMapper {

	/** 이미지 조회
	 * @param roomId
	 * @param tableName
	 * @return
	 */
	List<UploadFile> selectUploadFile(UploadFile uploadFile);

}
