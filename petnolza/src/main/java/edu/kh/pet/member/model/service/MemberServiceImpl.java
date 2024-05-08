package edu.kh.pet.member.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.pet.member.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;
	
	// 회원 리스트 조회
	@Override
	public Map<String, Object> selectMemberList(int cp) {
		
		int listCount = mapper.getListCount();
		
		return null;
	}

	
	// 회원 리스트 조회(검색)
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		// TODO Auto-generated method stub
		return null;
	}

}
