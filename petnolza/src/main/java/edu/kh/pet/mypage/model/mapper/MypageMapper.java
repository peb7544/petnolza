package edu.kh.pet.mypage.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.mypage.model.dto.Mtm;

@Mapper
public interface MypageMapper {
	
	/******************************************  1:1문의  ****************************************/

	/** 1:1문의
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
	
	/*********************************************************************************************/

}
