package edu.kh.pet.common.model.dto;

import java.util.List;

import edu.kh.pet.reserve.model.dto.Reserve;
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
public class UploadFile {

	private int fileNo;            // 파일번호
	private String filePath;       // 파일경로
	private String fileOrgName;    // 파일원본명
	private String fileRename;     // 파일변경명
	private String fileUploadDate; // 업로드날짜
	private String tableName;      // 테이블명
	private int tableNo;        // 테이블번호
	private String thumbnail;      // 썸네일여부
}
