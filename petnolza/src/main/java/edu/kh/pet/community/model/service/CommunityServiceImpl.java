package edu.kh.pet.community.model.service;

import edu.kh.pet.common.model.dto.Pagination;
import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityMapper mapper;

    public Map<String, Object> selectBoardList(String codeNo, int cp) {
        int listCount = mapper.getListCount(codeNo);
        Pagination pagination = new Pagination(cp, listCount);
        int limit = pagination.getLimit();
        int offset = (cp -1 ) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Board> boardList = mapper.selectBoardList(codeNo, rowBounds);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("pagination", pagination);
        map.put("boardList", boardList);

        return map;
    }

    public Map<String, Object> selectSearchList(Map<String, Object> paramMap, int cp) {
        int listCount = mapper.getSearchCount(paramMap);
        Pagination pagination = new Pagination(cp, listCount);
        int limit = pagination.getLimit();
        int offset = (cp -1 ) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Board> boardList = mapper.selectSearchList(paramMap, rowBounds);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("pagination", pagination);
        map.put("boardList", boardList);
        return map;
    }

    public Board selectOne(Map<String, Object> map) {
        return mapper.selectOne(map);
    }

    public int updateReadCount(int boardNo) {

        // 1. 조회 수 1 증가
        int result = mapper.updateReadCount(boardNo);

        // 2. 현재 조회 수 조회
        if(result > 0) {
            return mapper.selectReadCount(boardNo);
        }

        return -1; // 실패한 경우 -1 반환
    }

    
    // 공지사항 수정
	@Override
	public int updateNotice(Board board) {
		
		return mapper.updateNotice(board);
	}

	// 공지사항 삭제
	@Override
	public int deleteNotice(int boardNo) {
		
		return mapper.deleteNotice(boardNo);
	}

	// 공지사항 등록
	@Override
	public int insertNotice(Board board) {
		
		return mapper.insertNotice(board);
	}


}
