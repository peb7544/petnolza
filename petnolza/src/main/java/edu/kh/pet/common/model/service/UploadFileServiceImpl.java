package edu.kh.pet.common.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.common.model.mapper.UploadFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UploadFileServiceImpl implements UploadFileService {
	
	private final UploadFileMapper mapper;
	
	// 파일 조회
	@Override
	public List<UploadFile> selectUploadFile(UploadFile uploadFile) {
		// TODO Auto-generated method stub
		
		log.debug(uploadFile.getTableName());
		
		return mapper.selectUploadFile(uploadFile);
	}

}
