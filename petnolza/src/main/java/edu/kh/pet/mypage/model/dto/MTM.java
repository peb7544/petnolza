package edu.kh.pet.mypage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 *  1:1 문의
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MTM {
	
	private int mtmNo;         // 문의번호
	private String mtmTitle;   // 문의제목
	private String mtmContent; // 문의내용
	private String mtmDate;    // 문의작성일
	private String updateDate; // 문의수정일
	private int memberNo;      // 회원번호
	private String mtmAnswer;  // 문의답변
	private String answerDate; // 답변날짜
	
}
