package edu.kh.pet.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.community.model.dto.Pagination;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.member.model.mapper.MemberMapper;
import edu.kh.pet.reserve.model.dto.Reserve;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;
	
	// 회원 리스트 조회
	@Override
	public Map<String, Object> selectMemberList(int cp) {
		
		int listCount = mapper.getMemberListCount();
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.selectMemberList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		
		return map;
	}

	
	// 회원 리스트 조회(검색)
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		
		int listCount = mapper.getSearchCount(paramMap);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp -1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.selectSearchList(paramMap, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		
		return map;
	}

	// 회원 상세 조회
	@Override
	public Map<String, Object> memberReserveList(int memberNo, int cp) {
		
		Member member = mapper.selectMember(memberNo);
		
		String memberAddr = member.getMemberAddr();
		
		int listCount = mapper.getReserveListCount(memberNo);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int limit = pagination.getLimit();
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		Map<String, Object> map = new HashMap<>();
		
		if(memberAddr != null) {
			
			String[] arr = memberAddr.split("\\^\\^\\^");
			
			map.put("postcode", arr[0]);
			map.put("address", arr[1]);
			map.put("detailAddress", arr[2]);
		}
		
		
		List<Reserve> reserveList = mapper.selectReserveList(memberNo, rowBounds);
		
		map.put("pagination", pagination);
		map.put("member", member);
		map.put("reserveList", reserveList);
		
		return map;
	}


	// 회원 탈퇴(관리자)
	@Override
	public int withdrawal(int memberNo) {
		
		return mapper.withdrawal(memberNo);
	}


	// 회원 재가입(탈퇴 복구)
	@Override
	public int rejoin(int memberNo) {
		
		return mapper.rejoin(memberNo);
	}


	// 일반회원 -> 관리자
	@Override
	public int alterAdmin(int memberNo) {
		
		return mapper.alterAdmin(memberNo);
	}


	// 관리자 -> 일반회원
	@Override
	public int alterNormal(int memberNo) {
		
		return mapper.alterNormal(memberNo);
	}

}






















