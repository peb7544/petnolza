package edu.kh.pet.room.model.service;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.common.util.Utility;
import edu.kh.pet.room.model.dto.Room;
import edu.kh.pet.room.model.dto.RoomInfo;
import edu.kh.pet.room.model.exception.ImageDeleteException;
import edu.kh.pet.room.model.exception.ImageUpdateException;
import edu.kh.pet.room.model.exception.RoomInsertException;
import edu.kh.pet.room.model.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class RoomServiceImpl implements RoomService  {
	
	private final RoomMapper mapper;
	
	@Value("${my.room.web-path}")
	private String webPath;
	
	@Value("${my.room.folder-path}")
	private String folderPath;
	
	// 코드 리스트 조회
	@Override
	public List<CodeMt> selectCodeList(String groupCode) {
		
		return mapper.selectCodeList(groupCode);
	}

	// 객실 등록
	@Override
	public int insertRoom(Room inputRoom, List<MultipartFile> images) throws IllegalStateException, IOException {
		
		// 객실 등록
		int result = mapper.insertRoom(inputRoom);
		
		if(result == 0) return 0;
		
		int roomId = inputRoom.getRoomId();
		
		List<String> roomInfoList = inputRoom.getRoomInfoList();
		List<RoomInfo> roomInfo = new ArrayList<>();
		
		// 편의 시설
		if(roomInfoList != null) {
			result = 0;
			for(int i = 0; i < roomInfoList.size(); i++) {
				
				RoomInfo info = new RoomInfo();
				
				info.setRoomId(roomId);
				info.setCodeNo(roomInfoList.get(i));
				
				roomInfo.add(info);
				
			}
			
			result = mapper.insertRoomInfo(roomInfo);
			
			
			
			if(result == 0) return 0;
		
		}
		
		// 이미지
		List<UploadFile> uploadList = new ArrayList<>();
		
		for(int i=0; i<images.size(); i++) {
			
			if(!images.get(i).isEmpty()) {
				
				// 원본명
				String fileOrgName = images.get(i).getOriginalFilename();
				
				// 변경명
				String fileRename = Utility.fileRename(fileOrgName);
				
				String thumbnail = "N";
				
				if(i == inputRoom.getThumnailYn()) {
					
					thumbnail = "Y";
				}
				
				UploadFile img = UploadFile.builder()
						.filePath(webPath)
						.fileOrgName(fileOrgName)
						.fileRename(fileRename)
						.tableName("ROOM")
						.tableNo(roomId)
						.thumbnail(thumbnail)
						.uploadFile(images.get(i))
						.build();
				
				uploadList.add(img);
			}
		}
		
		if(uploadList.isEmpty()) return roomId;
		
		result = mapper.insertUploadList(uploadList);
		
		if(result == uploadList.size()) {
			
			for(UploadFile img : uploadList) {
				
				img.getUploadFile().transferTo(new File(folderPath + img.getFileRename()));
			}
		} else {
			throw new RoomInsertException("이미지가 정상 삽입되지 않음");
		}
		
		return roomId;
	}

	// 객실 상세 조회
	@Override
	public Room selectRoomDetail(int roomId) {
		
		return mapper.selectRoomDetail(roomId);
	}

	// 객실 수정
	@Override
	public int updateRoomUpdate(Room inputRoom, List<MultipartFile> images, String deleteOrder, String orderList, String upList, UploadFile inputUploadFile) throws IllegalStateException, IOException {
		
		int result = mapper.updateRoomUpdate(inputRoom);
		
		List<String> roomInfoList = inputRoom.getRoomInfoList();
		List<RoomInfo> roomInfo = new ArrayList<>();
		
		// 편의시설 개수 조회
		int cnt = mapper.roomInfoCnt(inputRoom);
		
		// 기존 편의시설 삭제
		if(cnt != 0) {
			result = mapper.deleteRoomInfo(inputRoom);
			
		}
		
		// 편의 시설
		if(roomInfoList != null) {
			
			for(int i = 0; i < roomInfoList.size(); i++) {
				
				RoomInfo info = new RoomInfo();
				
				info.setRoomId(inputRoom.getRoomId());
				info.setCodeNo(roomInfoList.get(i));
				
				roomInfo.add(info);
				
			}
			
			result = mapper.insertRoomInfo(roomInfo);
			
			
			if(result == 0) return 0;
		
		}
		
		log.debug("deleteOrder : " + deleteOrder);
			
		// 기존에 삭제된 deleteOrder(이미지)가 있는 경우
		if(deleteOrder != null && !deleteOrder.equals("")) {
			Map<String, Object> map = new HashMap<>();
			
			map.put("deleteOrder", deleteOrder);
			map.put("roomId", inputRoom.getRoomId());
			
			result = mapper.deleteImage(map);
			
			// 삭제 실패 -> 롤백
			if(result == 0) {
				throw new ImageDeleteException();
			}
		}
		
		// 파일이 존재하는 경우
		List<UploadFile> uploadList = new ArrayList<>();
		
		for(MultipartFile img : images) {
			
			// 대표 이미지 
			inputRoom.getThumnailYn();
			
			
				
			// 실제 이미지 있을 경우
			//if(img.getOriginalFilename() != null && !img.getOriginalFilename().equals("")) {
				
				log.debug(img.getOriginalFilename() + "파일 왜들어가 ㅠㅜ");
				if(!img.getOriginalFilename().isEmpty()) {
					
					log.debug(img.getOriginalFilename());
					
					// 원본명
					String fileOrgName = img.getOriginalFilename();
					
					// 변경명
					String fileRename = Utility.fileRename(fileOrgName);
					
					// 파일번호
					//String[] codeArr = upList.split(",");
					
					//log.debug(codeArr.length + "개수");
					//log.debug(upList + " : upList");
					//log.debug(upList.trim().length() + " : upList222");
					
					
					//String[] codeArr = upList.split(",");
					
					//for(int i=0; i<codeArr.length; i++) {
						
						//log.debug(codeArr[i] + "  /  ");
						
						// 대표이미지
						//String thumbnail = "N";
						
						//if(i == inputRoom.getThumnailYn()) {
							
							//thumbnail = "Y";
						//}
						
						if(!img.getOriginalFilename().isEmpty()) {
							UploadFile imgs = UploadFile.builder()
									.filePath(webPath)
									.fileOrgName(fileOrgName)
									.fileRename(fileRename)
									.tableName("ROOM")
									.tableNo(inputRoom.getRoomId())
									//.thumbnail(thumbnail)
									.uploadFile(img)
									.build();
						
						
							/*if(upList.trim().length() != 0) {
							
								imgs.setFileNo(Integer.parseInt(codeArr[i]));
								
							} */
							
							log.debug("uploadList : " + img);
							
							uploadList.add(imgs);
							
							//result = mapper.updateImage(imgs); 
							
							//if(result == 0) {
								
								result = mapper.insertImage(imgs);
								
							//}
						
						
						log.debug("uploadList2 : " + imgs.getUploadFile());
						
						log.debug("uploadList3 : " + uploadList);
						}
					//}
					
					if(result == 0) {
						
						throw new ImageUpdateException();
					}
					
					// 이미지 파일 서버에 저장
					for(UploadFile imgs : uploadList) {
						
						
						
						if(imgs.getUploadFile() != null) {
							
							imgs.getUploadFile().transferTo(new File(folderPath + imgs.getFileRename()));
						}
					}
					
				} //else {
					
					String[] codeArr = orderList.split(",");
					
					for(int i=0; i<codeArr.length; i++) {
						
						log.debug(codeArr[i] + "  /  ");
					
						String thumbnail = "N";
						
						if(i == inputRoom.getThumnailYn()) {
							
							thumbnail = "Y";
						}
						
						UploadFile imgs = new UploadFile();
						
						imgs.setTableName("ROOM");
						imgs.setTableNo(inputRoom.getRoomId());
						imgs.setThumbnail(thumbnail);
						
						int fileNo = inputUploadFile.getFileNo();
						
						log.debug(codeArr[i] + "");
						
						if(codeArr[i] != null && !codeArr[i].equals("")) {
							imgs.setFileNo(Integer.parseInt(codeArr[i]));
						} else {
							
							imgs.setFileNo(fileNo);
						}
						
						uploadList.add(imgs);
						
						result = mapper.updateThumbnail(imgs);
					//}
				}
			//}
			
		}
		
		
		
		//선택한 파일이 없는 경우
		if(uploadList.isEmpty()) {
			return result;
		}
		
		return result;
	}
	
	// 객실 삭제
	@Override
	public int deleteRoomDelete(int roomId) {
		// TODO Auto-generated method stub
		return mapper.deleteRoomDelete(roomId);
	}

}
