package edu.kh.pet.community.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Board {
	
	// BOARD 테이블 컬럼
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int readCount;
	private String boardDelFl;
	private int memberNo;
	private String groupCode;
	private String codeNo;
	
	// MEMBER 테이블 조인
	private String memberNickname;
	
	// 목록 조회 시 상관 서브 쿼리 결과
	private int commentCount;
	private int likeCount;
	
	

}
