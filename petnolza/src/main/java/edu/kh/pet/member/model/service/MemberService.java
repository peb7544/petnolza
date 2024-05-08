package edu.kh.pet.member.model.service;

import java.util.Map;

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
	

}
