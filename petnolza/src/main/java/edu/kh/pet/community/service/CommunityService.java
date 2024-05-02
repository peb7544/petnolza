package edu.kh.pet.community.service;

import edu.kh.pet.community.dto.Board;
import edu.kh.pet.community.dto.Pagination;
import edu.kh.pet.community.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityMapper communityMapper;

    public Map<String, Object> selectBoardList(String groupCode, int cp) {
        int listCount = communityMapper.getListCount(groupCode);
        Pagination pagination = new Pagination(cp, listCount);
        int limit = pagination.getLimit();
        int offset = (cp -1 ) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Board> boardList = communityMapper.selectBoardList(groupCode, rowBounds);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("pagination", pagination);
        map.put("boardList", boardList);

        return map;
    }

    public Map<String, Object> selectSearchList(Map<String, Object> paramMap, int cp) {
        int listCount = communityMapper.getSearchCount(paramMap);
        Pagination pagination = new Pagination(cp, listCount);
        int limit = pagination.getLimit();
        int offset = (cp -1 ) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        List<Board> boardList = communityMapper.selectSearchList(paramMap, rowBounds);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("pagination", pagination);
        map.put("boardList", boardList);
        return map;
    }
}
