package edu.kh.pet.room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("room")
public class RoomController {

	/** 객실 관리
	 * @return
	 */
	@GetMapping("roomList")
	public String roomList() {
		return "room/roomList";
	}
	
	/** 객실 관리 등록
	 * @return
	 */
	@GetMapping("roomRegist")
	public String roomRegist() {
		return "room/roomRegist";
	}
	
}
