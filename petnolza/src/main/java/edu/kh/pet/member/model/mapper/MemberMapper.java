package edu.kh.pet.member.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.reserve.model.dto.Reserve;

@Mapper
public interface MemberMapper {

	
	
	/** 회원 수 조회
	 * @return
	 */
	int getMemberListCount();

	
	
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


	/** 예약내역 개수 조회
	 * @return
	 */
	int getReserveListCount(int memberNo);

	
	/** 예약 정보 조회
	 * @param memberNo
	 * @return reserveList
	 */
	List<Reserve> selectReserveList(int memberNo, RowBounds rowBounds);



	/** 회원 탈퇴(관리자)
	 * @param memberNo
	 * @return result
	 */
	int withdrawal(int memberNo);



	/** 회원 재가입(탈퇴 복구)
	 * @param memberNo
	 * @return result
	 */
	int rejoin(int memberNo);



	/** 일반회원 -> 관리자
	 * @param memberNo
	 * @return result
	 */
	int alterAdmin(int memberNo);



	/** 관리자 -> 일반회원
	 * @param memberNo
	 * @return result
	 */
	int alterNormal(int memberNo);




}
