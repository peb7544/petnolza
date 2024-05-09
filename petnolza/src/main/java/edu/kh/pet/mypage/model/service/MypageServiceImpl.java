package edu.kh.pet.mypage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.dto.Pagination;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.mypage.model.dto.Mtm;
import edu.kh.pet.mypage.model.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageServiceImpl implements MypageService {
	
	private final MypageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

	/******************************************  1:1문의  ****************************************/
	
	// 1:1문의 등록
	@Override
	public int mtmInsert(Mtm inputMtm) {
		
		int result = mapper.mtmInsert(inputMtm);
		
		// 삽입 실패 시
		if(result == 0) return 0;
		
		int mtmNo = inputMtm.getMtmNo();
		
		return mtmNo;
	}

	/** 1:1문의 페이지 목록 조회
	 *
	 */
	@Override
	public Map<String, Object> selectMtmList(int memberNo, int cp) {
		
		// 1:1문의 Count 조회
		int listCount = mapper.getListCount(memberNo);
		
		Pagination pagination = new Pagination(cp, listCount);  // 혹시 모르니 나중에 import 확인
		
		/* 특정 게시판의 지정된 페이지 목록 조회
		 * 
		 * - 지정된 크기 만큼 건너띄고(offset) 제한된 크기(limit)만큼의 행을 조회
		 * */
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Mtm> mtmList = mapper.selectMtmList(memberNo, rowBounds);
		
		// 목록조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("mtmList", mtmList);
		
		return map;
	}

	// 자주묻는 질문
	@Override
	public List<Board> selectQnaList() {
		// TODO Auto-generated method stub
		return mapper.selectQnaList();
	}

	// 1:1문의 상세
	@Override
	public Mtm selectMtmDetail(int mtmNo) {
		// TODO Auto-generated method stub
		return mapper.selectMtmDetail(mtmNo);
	}
	
	// 1:1문의 수정
	@Override
	public int mtmUpdate(Mtm inputMtm) {
		
		int result = mapper.mtmUpdate(inputMtm);
		
		// 삽입 실패 시
		if(result == 0) return 0;
		
		int mtmNo = inputMtm.getMtmNo();
		
		return mtmNo;
	}
	
	// 1:1문의 삭제
	@Override
	public int mtmDelete(int mtmNo) {
		// TODO Auto-generated method stub
		return mapper.mtmDelete(mtmNo);
	}

	
	/*********************************************************************************************/
	
	// 회원 정보 수정
	@Override
	public int memberUpdate(Member inputMember, String[] memberAddr) {
		
		if(inputMember.getMemberAddr().equals(",,")) {
			
			inputMember.setMemberAddr(null);
			
		} else {
			
			String address = String.join("^^^", memberAddr);
			
			inputMember.setMemberAddr(address);
			
		}
		
		return mapper.memberUpdate(inputMember);
	}

	
	// 비밀번호 변경
	@Override
	public int pwUpdate(Map<String, Object> paramMap, int memberNo) {
		
		String originPw = mapper.selectPw(memberNo);
		
		if( !bcrypt.matches( (String) paramMap.get("currPw") , originPw) ) {
			return 0;
		}
		
		String encPw = bcrypt.encode( (String) paramMap.get("newPw") );
		
		paramMap.put("encPw", encPw);
		paramMap.put("memberNo", memberNo);
		
		return mapper.pwUpdate(paramMap);
	}

	
	// 회원 탈퇴
	@Override
	public int withdrawal(String memberPassword, int memberNo) {
		
		String originPw = mapper.selectPw(memberNo);
		
		if(!bcrypt.matches(memberPassword, originPw)) {
			return 0;
		}
		
		return mapper.withdrawal(memberNo);
	}

}
















