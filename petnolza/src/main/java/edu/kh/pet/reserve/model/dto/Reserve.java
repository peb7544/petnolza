package edu.kh.pet.reserve.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 객실 예약 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Reserve {
	
	 private int roomId;            // 객실번호
	 private String roomName;       // 객실명
	 private int roomPrice;         // 객실가격
	 private List<String> codeName; // 편의시설
	 private int reviewGrade;       // 평점
	 
	 private String codeNameList;
	 
	 /*private String reserveStart;   // 시작날짜
	 private String reserveEnd;     // 종료날짜*/
	
}
