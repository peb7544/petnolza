package edu.kh.pet.room.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.common.model.dto.CodeMt;
import edu.kh.pet.common.model.dto.UploadFile;
import edu.kh.pet.reserve.model.dto.Reserve;
import edu.kh.pet.reserve.model.service.ReserveService;
import edu.kh.pet.room.model.dto.Room;
import edu.kh.pet.room.model.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("room")
@Slf4j
public class RoomController {
	
	private final RoomService service;
	private final ReserveService reserveService;

	/** 객실 관리
	 * @return
	 */
	@GetMapping("roomList")
	public String roomList(
				Model model,
				@RequestParam(value="inputRoomNm", required=false) String inputRoomNm,
				RedirectAttributes ra
			) {
		if(inputRoomNm == "") inputRoomNm = null;
		
		Map<String, Object> paramList = new HashMap<>();
		paramList.put("inputRoomNm", inputRoomNm);
		
		// 객실 예약 목록 조회 서비스 호출 후 결과 반환
		List<Reserve> reserveList = reserveService.selectReserveList(paramList);
		
		model.addAttribute("reserveList", reserveList);
		
		return "room/roomList";
	}
	
	/** 객실 관리 등록 화면
	 * @return
	 */
	@GetMapping("roomRegist")
	public String roomRegist(Model model) {
		
		/* 편의시설 조회 */
		String groupCode = "CONV";
		
		List<CodeMt> codeList = service.selectCodeList(groupCode);
		
		model.addAttribute("codeList", codeList);
		
		return "room/roomRegist";
	}
	
	/** 객실 등록
	 * @param inputRoom
	 * @param images
	 * @param ra
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("insertRoomRegist")
	public String insertRoomRegist(
				Room inputRoom,
				@RequestParam("images") List<MultipartFile> images,
				RedirectAttributes ra
			) throws IllegalStateException, IOException {
		
		int roomNo = service.insertRoom(inputRoom, images);
		
		/*for(MultipartFile img : images) {
			log.debug("ddd : " + img.getOriginalFilename());
		}*/
		
		String path = null;
		String message = null;
		
		if(roomNo > 0)  {
			
			path = "/room/roomUpdate/" + roomNo;
			message = "객실이 등록되었습니다";
		} else {
			
			path = "/room/roomRegist/";
			message = "객실이 등록 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	
	/** 객실 관리 수정
	 * @return
	 */
	@GetMapping("roomUpdate/{roomId:[0-9]+}")
	public String roomUpdate(
				@PathVariable("roomId") int roomId,
				Model model,
				RedirectAttributes ra
				
			) {
		
		/* 편의시설 조회 */
		String groupCode = "CONV";
		
		List<CodeMt> codeList = service.selectCodeList(groupCode);
		
		// 서비스 호출
		Room room = service.selectRoomDetail(roomId);
		
		String path = null;
		//List<RoomInfo> roomInfoList = new ArrayList<>();
		
		// 조회 결과가 없는 경우
		if(room == null) {
			path = "redirect:room/roomList";
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다");
		} else {
			
			if(room.getInfo() != null) {
				
				String[] codeArr = room.getInfo().split(",");
				//List<String> infoList = new ArrayList<>();
				
				for(CodeMt code : codeList) {
					
					for(int i=0; i<codeArr.length; i++) {
						
						if(code.getCodeNo().equals(codeArr[i])) {
							code.setCheckYn("Y");
							
							break;
						}
						else code.setCheckYn("N");
						
					}
				}
				
			}
			
			path = "room/roomUpdate";
			
			model.addAttribute("room", room);
		}
		
		model.addAttribute("codeList", codeList);
		
		return path;
	}
	
	@PostMapping("roomUpdate/{roomId:[0-9]+}")
	public String roomUpdate(
				@PathVariable("roomId") int roomId,
				Room inputRoom,
				@RequestParam("images") List<MultipartFile> images,
				RedirectAttributes ra,
				@RequestParam(value="deleteOrder", required=false) String deleteOrder,
				@RequestParam(value="queryString", required=false, defaultValue="") String querystring,
				@RequestParam(value="orderList", required=false) String orderList,
				@RequestParam(value="upList", required=false) String upList,
				UploadFile inputUploadFile
			) throws IllegalStateException, IOException {
		
		// 객실번호 세팅
		inputRoom.setRoomId(roomId);
		
		// 서비스 호출
		int result = service.updateRoomUpdate(inputRoom, images, deleteOrder, orderList, upList, inputUploadFile);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "게시글이 수정되었습니다";
		} else {
			message = "수정 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/room/roomUpdate/" + roomId;
	}
	
	/** 객실 삭제
	 * @param roomId
	 * @return
	 */
	@ResponseBody
	@DeleteMapping("roomDelete")
	public int roomDelete( @RequestBody int roomId ) {
		
		int result = service.deleteRoomDelete(roomId);
		
		return result;
	}
}
