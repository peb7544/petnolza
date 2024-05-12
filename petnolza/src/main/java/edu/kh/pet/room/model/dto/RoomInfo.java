package edu.kh.pet.room.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 객실 편의시설 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomInfo {

	private int infoNo; // 상세정보 번호
	private int roomId; // 객실번호
	private String groupCode;
	private String codeNo; //편의시설 코드
}
