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
	
	 private int roomId;               // 객실번호
	 private String roomName;          // 객실명
	 private int roomPrice;            // 객실가격
	 private List<String> codeName;    // 편의시설
	 private int reviewGrade;          // 평점
	 private String fileRename;        // 파일명
	 private String roomInfo;          // 객실상세
	 
	 private String reserveStart;      // 시작날짜
	 private String reserveEnd;        // 종료날짜
	 private String reserveDate;	   // 예약 신청일
	 private int    reservePrice;      // 예약가격
	 private String reserveUser;       // 예약자
	 private int memberNo;             // 회원번호
	 private int reserveNo;            // 예약번호
	 private List<String> serviceName; // 서비스명
	 
	 private String status;            // 예약상태
	 
	 private String reviewCheck;	   // 리뷰 여부 
	 
	 private String codeNameList;      // 편의시설목록
	 private String serviceNameList;   // 서비스명목록
	 private List<Integer> serviceNo;  // 서비스목록
}
