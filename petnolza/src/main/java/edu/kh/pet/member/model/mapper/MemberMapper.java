package edu.kh.pet.member.model.mapper;

import java.util.List;

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

}
