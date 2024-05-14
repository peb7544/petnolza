package edu.kh.pet.room.model.dto;
import java.util.List;

import edu.kh.pet.common.model.dto.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 객실 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Room {
	private int roomId; // 객실 번호
	private String roomName; // 객실명
	private int roomPrice; // 객실 가격
	private String roomInfo; // 객실 상세정보
	
	private String info; // 편의시설
	private List<String> roomInfoList; // 편의시설 리스트
	private List<UploadFile> imageList; // 객실 이미지
	private int thumnailYn; // 썸네일여부
}
