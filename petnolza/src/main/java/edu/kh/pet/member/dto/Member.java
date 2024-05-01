package edu.kh.pet.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member {
	
	private int memberNo;
	private String memberEmail;
	private String memberPassword;
	private String memberNickname;
	private String memberName;
	private String memberTel;
	private String memberAddr;
	private String enrollDate;
	private String memberDelFl;
	private String codeNo;

}
