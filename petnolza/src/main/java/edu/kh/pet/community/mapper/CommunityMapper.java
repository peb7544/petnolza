package edu.kh.pet.community.mapper;

import edu.kh.pet.community.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {
    int getListCount(String groupCode);

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
    Board selectOne(Map<String, Integer> map);

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


}
