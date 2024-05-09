package edu.kh.pet.member.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	
	
	/** 회원 수 조회
	 * @return
	 */
	int getListCount();

	
	
	/** 회원 목록 조회
	 * @param rowBounds
	 * @return
	 */
	List<Member> selectMemberList(RowBounds rowBounds);



	/** 검색한 회원 수 조회
	 * @param paramMap
	 * @return count
	 */
	int getSearchCount(Map<String, Object> paramMap);



	/** 검색한 회원 목록 조회
	 * @param paramMap
	 * @param rowBounds
	 * @return memberList
	 */
	List<Member> selectSearchList(Map<String, Object> paramMap, RowBounds rowBounds);



	/** 회원 상세 조회
	 * @param memberNo
	 * @return member
	 */
	Member selectMember(int memberNo);

}
