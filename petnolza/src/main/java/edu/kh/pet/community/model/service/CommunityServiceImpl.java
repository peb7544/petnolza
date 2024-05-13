package edu.kh.pet.community.model.service;

import edu.kh.pet.common.model.dto.Pagination;
import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.common.util.Utility;
import edu.kh.pet.community.model.dto.Board;
import edu.kh.pet.community.model.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:/config.properties")
public class CommunityServiceImpl implements CommunityService{

    private final CommunityMapper mapper;
    
    @Value("${my.notice.web-path}")
    private String boardWebPath;
    
    @Value("${my.notice.folder-path}")
    private String boardFolderPath;
    
    

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

	
	// 파일 업로드
	@Override
	public void fileUpload(MultipartFile uploadFile, int boardNo) throws IOException {
		
		

		String fileRename = Utility.fileRename( uploadFile.getOriginalFilename() );
		
		
		UploadFile uf = UploadFile.builder()
						.filePath(boardWebPath)
						.fileOrgName(uploadFile.getOriginalFilename())
						.fileRename(fileRename)
						.tableName("BOARD")
						.tableNo(boardNo)
						.build();
		
		mapper.insertUploadFile(uf);
		
		uploadFile.transferTo( new File(boardFolderPath + fileRename) );
		
		
		
	}

	
	// 파일 가져오기
	@Override
	public UploadFile selectUploadFile(int tableNo) {
		
		return mapper.selectUploadFile(tableNo);
	}

	// 자주 묻는 질문 리스트 조회
	@Override
	public List<Board> selectFaqList(String codeNo) {
		
		
		return mapper.selectFaqList(codeNo);
	}

	// 자주 묻는 질문 조회
	@Override
	public Board selectFaqOne(int boardNo) {
		
		return mapper.selectFaqOne(boardNo);
	}

	

	
	
	// 파일 업로드
	/*
	@Override
	public int fileUpload(MultipartFile uploadFile) throws IOException {
		
		if(uploadFile.isEmpty()) {
			
			return 0;
		}
		
		String fileRename = Utility.fileRename( uploadFile.getOriginalFilename() );
		
		UploadFile uf = UploadFile.builder()
						.filePath(boardWebPath)
						.fileOrgName(uploadFile.getOriginalFilename())
						.fileRename(fileRename)
						.tableName("BOARD")
						.tableNo(1)
						.build();
		
		int result = mapper.insertUploadFile(uf);
		
		if(result == 0) return 0;
		
		uploadFile.transferTo( new File(boardFolderPath + fileRename) );
		
		
		
		return result;
	}
	*/

}























