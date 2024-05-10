package edu.kh.pet.mypage.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.member.model.dto.Member;
import edu.kh.pet.mypage.model.dto.Mtm;

public interface MypageService {

	/******************************************  1:1문의  ****************************************/
	/** 1:1문의 등록
	 * @param inputMtm
	 * @return mtmNo
	 */
	int mtmInsert(Mtm inputMtm);

	/** 1:1문의 페이지 목록 조회
	 * @param cp
	 * @param cp2 
	 * @return
	 */
	Map<String, Object> selectMtmList(int memberNo, int cp2);

	/** 자주묻는질문조회
	 * @return
	 */
	List<Board> selectQnaList();

	/** 1:1문의 상세 조회
	 * @param mtmNo
	 * @return
	 */
	Mtm selectMtmDetail(int mtmNo);

	/** 1:1 수정
	 * @param inputMtm
	 * @return
	 */
	int mtmUpdate(Mtm inputMtm);
	
	/** 1:1 삭제
	 * @param mtmNo
	 * @return
	 */
	int mtmDelete(int mtmNo);
	
	/*********************************************************************************************/

	/**	회원 정보 수정
	 * @param inputMember
	 * @param memberAddr
	 * @return result
	 */
	int memberUpdate(Member inputMember, String[] memberAddr);

	
	
	/**	비밀번호 변경
	 * @param paramMap
	 * @param memberNo
	 * @return result
	 */
	int pwUpdate(Map<String, Object> paramMap, int memberNo);

	
	
	/** 회원 탈퇴
	 * @param memberPassword
	 * @param memberNo
	 * @return result
	 */
	int withdrawal(String memberPassword, int memberNo);

	/** 예약내역확인
	 * @param codeNo
	 * @return
	 */
	Map<String, Object> selectReserveList(int memberNo, int cp);

	/** 예약 취소
	 * @param reserveNo
	 * @return
	 */
	int reserveCancel(int reserveNo);

	/** 예약 결제
	 * @param reserveNo
	 * @return
	 */
	int reservePayment(int reserveNo);

}
