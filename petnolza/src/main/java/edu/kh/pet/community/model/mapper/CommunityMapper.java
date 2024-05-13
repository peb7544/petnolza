package edu.kh.pet.community.model.mapper;

import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.community.model.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {
    int getListCount(String codeNo);

    List<Board> selectBoardList(String groupCode, RowBounds rowBounds);

    /**
     *
     * @param paramMap
     * @return
     */
    int getSearchCount(Map<String, Object> paramMap);

    List<Board> selectSearchList(Map<String, Object> paramMap, RowBounds rowBounds);


    /** 게시글 상세 조회
     * @param map
     * @return board
     */
    Board selectOne(Map<String, Object> map);

    /** 조회수 1 증가
     * @param boardNo
     * @return
     */
    int updateReadCount(int boardNo);

    /** 조회 수 조회
     * @param boardNo
     * @return
     */
    int selectReadCount(int boardNo);

    
    
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
	int insertNotice(Board board);

	
	
	/** 파일 업로드
	 * @param uf
	 * @return result
	 */
	void insertUploadFile(UploadFile uf);

	
	
	/** 파일 가져오기
	 * @param boardNo
	 * @return uploadFile
	 */
	UploadFile selectUploadFile(int tableNo);


}
