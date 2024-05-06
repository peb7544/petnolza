package edu.kh.pet.mypage.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.pet.community.model.dto.Board;
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
	
	/*********************************************************************************************/

}
