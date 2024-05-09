package edu.kh.pet.community.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import edu.kh.pet.community.model.dto.Pagination;
import edu.kh.pet.community.model.mapper.MtmMapper;
import edu.kh.pet.mypage.model.dto.Mtm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MtmServiceImple implements MtmService {
	
	private final MtmMapper mapper;

	// 관리자 1:1문의 조회
	@Override
	public Map<String, Object> selectMtmList(int cp) {
		
		// 게시글 수 조회
		int listCount = mapper.getListCount();
		
		Pagination pagination = new Pagination(cp, listCount);  // 혹시 모르니 나중에 import 확인
		
		/* 특정 게시판의 지정된 페이지 목록 조회
		 * 
		 * - 지정된 크기 만큼 건너띄고(offset) 제한된 크기(limit)만큼의 행을 조회
		 * */
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Mtm> mtmList = mapper.selectMtmList(rowBounds);
		
		// 목록조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("mtmList", mtmList);
		
		return map;
	}

	// 답변 등록
	@Override
	public int mtmAnswer(Mtm mtm) {
		// TODO Auto-generated method stub
		int result = mapper.mtmAnswer(mtm);
		
		return result;
	}
}
