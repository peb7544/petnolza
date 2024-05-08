package edu.kh.pet.common.model.service;

import java.util.List;

import edu.kh.pet.common.model.dto.UploadFile;

public interface UploadFileService {

	/** 업로드파일
	 * @param uploadFile
	 * @return
	 */
	List<UploadFile> selectUploadFile(UploadFile uploadFile);

}
