package edu.kh.pet.community.model.service;

import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.dto.Pagination;
import edu.kh.pet.community.model.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityMapper mapper;

    public Map<String, Object> selectBoardList(String groupCode, int cp) {
        int listCount = mapper.getListCount(groupCode);
        Pagination pagination = new Pagination(cp, listCount);
        int limit = pagination.getLimit();
        int offset = (cp -1 ) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Board> boardList = mapper.selectBoardList(groupCode, rowBounds);

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


}