package edu.kh.pet.community.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardImg {
	
	private int imgNo;
	private String imgPath; // /image/board/
	private String imgOriginalName;
	private String imgRename;   //  test1.jpg
	private int imgOrder;
	private int boardNo;
	
	// 게시글 이미지 삽입/수정 때 사용
	private MultipartFile uploadFile;
}
