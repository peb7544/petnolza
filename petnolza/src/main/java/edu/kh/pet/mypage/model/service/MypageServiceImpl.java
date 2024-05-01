package edu.kh.pet.mypage.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.mypage.model.dto.MTM;
import edu.kh.pet.mypage.model.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageServiceImpl implements MypageService {
	
	private final MypageMapper mapper;

	/******************************************  1:1문의  ****************************************/
	
	// 1:1문의 등록
	@Override
	public int mtmInsert(MTM inputMtm) {
		
		int result = mapper.mtmInsert(inputMtm);
		
		return result;
	}
	
	/*********************************************************************************************/

}
