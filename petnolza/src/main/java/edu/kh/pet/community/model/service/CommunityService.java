package edu.kh.pet.community.model.service;

import java.util.Map;

import edu.kh.pet.community.model.dto.Board;

public interface CommunityService {

	Map<String, Object> selectBoardList(String noticeBoardCodeNo, int cp);

	Map<String, Object> selectSearchList(Map<String, Object> paramMap, int cp);

	Board selectOne(Map<String, Object> map);

	int updateReadCount(int boardNo);

	
	
	/** 공지사항 수정
	 * @param board
	 * @return result
	 */
	int updateNotice(Board board);

	
	
	/** 공지사항 삭제
	 * @param boardNo
	 * @return result
	 */
	int deleteNotice(int boardNo);

}
