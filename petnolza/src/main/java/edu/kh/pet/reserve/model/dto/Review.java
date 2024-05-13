package edu.kh.pet.reserve.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Review {
	
	private int reviewNo;
	private String reviewTitle;
	private int reviewGrade;
	private int memberNo;
	private String writeDate;
	private String memberName;
	private int roomId;
	private String reviewContent;
	
}


