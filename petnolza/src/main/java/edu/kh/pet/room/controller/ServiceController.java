package edu.kh.pet.room.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.pet.reserve.model.service.ReserveService;
import edu.kh.pet.room.model.dto.ServiceInfo;
import edu.kh.pet.room.model.service.RoomService;
import edu.kh.pet.room.model.service.ServiceInfoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("service")
public class ServiceController {
	
	private final ServiceInfoService service;

	/** 서비스 관리 화면
	 * @param model
	 * @return
	 */
	@GetMapping("serviceMana")
	public String serviceMana( Model model ) {
		
		List<ServiceInfo> serviceInfo = service.selectService();
		
		model.addAttribute("serviceInfo", serviceInfo);
		
		return  "service/serviceMana";
	}
	
	/** 서비스 등록
	 * @param serviceInfo
	 * @return
	 */
	@ResponseBody
	@PostMapping("insertService")
	public int insertService( @RequestBody ServiceInfo serviceInfo) {
		
		int result = service.insertService(serviceInfo);
		
		return result;
	}
	
	/** 서비스 삭제
	 * @param roomId
	 * @return
	 */
	@ResponseBody
	@DeleteMapping("deleteService")
	public int roomDelete( @RequestBody int serviceNo ) {
		
		int result = service.deleteService(serviceNo);
		
		return result;
	}
}
