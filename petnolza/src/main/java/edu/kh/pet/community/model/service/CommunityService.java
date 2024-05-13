package edu.kh.pet.community.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.pet.common.model.dto.UploadFile;
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

	
	
	/** 공지사항 등록
	 * @param board
	 * @return result
	 */
	int insertNotice(Board boards);

	
	
	/** 파일 업로드
	 * @param uploadFile
	 * @param boardNo
	 */
	void fileUpload(MultipartFile uploadFile, int boardNo) throws IOException;

	
	
	/** 파일 가져오기
	 * @param boardNo
	 * @return uploadFile
	 */
	UploadFile selectUploadFile(int tableNo);

	
	/** 자주 묻는 질문 리스트 조회
	 * @param codeNo
	 * @return faqList
	 */
	List<Board> selectFaqList(String codeNo);

	
	/** 자주 묻는 질문 조회
	 * @param boardNo
	 * @return board
	 */
	Board selectFaqOne(int boardNo);


	
	

	

	


}
