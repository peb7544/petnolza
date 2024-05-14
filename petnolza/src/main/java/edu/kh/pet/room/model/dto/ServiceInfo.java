package edu.kh.pet.room.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 서비스 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceInfo {
	private int serviceNo;        // 서비스번호
	private String serviceName;   // 서비스명
	private String servicePrice;  // 서비스가격
	private String serviceDetail; // 서비스상세
	private String serviceDelFl;  // 서비스삭제여부
	
	private int srvRsrNo;         // 서비스예약번호
	private int reserveNo;        // 예약번호
}
