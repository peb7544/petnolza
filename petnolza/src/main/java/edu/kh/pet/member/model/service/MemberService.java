package edu.kh.pet.member.model.service;

import java.util.Map;

import edu.kh.pet.member.model.dto.Member;

public interface MemberService {

	
	/**	멤버리스트 조회
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectMemberList(int cp);

	
	
	/**	멤버리스트 조회(검색)
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);



	/** 회원 상세 조회
	 * @param memberNo
	 * @return member
	 */
	Map<String, Object> memberReserveList(int memberNo, int cp);



	/** 회원 탈퇴 (관리자)
	 * @param memberNo
	 * @return result
	 */
	int withdrawal(int memberNo);
	

}
