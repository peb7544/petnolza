package edu.kh.pet.mypage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.mypage.model.dto.Mtm;

@Mapper
public interface MypageMapper {
	
	/******************************************  1:1문의  ****************************************/

	/** 1:1문의 등록
	 * @param inputMtm
	 * @return result
	 */
	int mtmInsert(Mtm inputMtm);

	/**1:1문의 Count 조회
	 * @param memberNo
	 * @return
	 */
	int getListCount(int memberNo);

	/** 1:1문의 조회
	 * @param rowBounds
	 * @return
	 */
	List<Mtm> selectMtmList(int memberNo, RowBounds rowBounds);

	/** 자주묻는질문 조회
	 * @return
	 */
	List<Board> selectQnaList();

	/** 1:1문의 상세조회
	 * @param mtmNo
	 * @return
	 */
	Mtm selectMtmDetail(int mtmNo);
	
	
	/** 1:1문의 수정
	 * @param inputMtm
	 * @return
	 */
	int mtmUpdate(Mtm inputMtm);
	
	/** 1:1삭제
	 * @param mtmNo
	 * @return
	 */
	int mtmDelete(int mtmNo);

	
	/*********************************************************************************************/

	/**	회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int memberUpdate(Member inputMember);

	
	
	/** 로그인한 회원 (암호화)비밀번호 조회
	 * @param memberNo
	 * @return
	 */
	String selectPw(int memberNo);

	
	
	/** 비밀번호 변경
	 * @param paramMap
	 * @return result
	 */
	int pwUpdate(Map<String, Object> paramMap);

	
	
	/** 회원 탈퇴
	 * @param memberNo
	 * @return result
	 */
	int withdrawal(int memberNo);

	/** 예약내역 게시글 수
	 * @param memberNo
	 * @return
	 */
	int getReserveListCount(int memberNo);
	
}
