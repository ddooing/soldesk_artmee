package kr.co.softsoldesk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.softsoldesk.Service.AdminService;
import kr.co.softsoldesk.Service.CalendarService;

@Controller
@RequestMapping("/admin")
public class AdminBannerCalendarController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CalendarService calendarService;
	
	
	//메인배너 캘린더 확인
	@GetMapping("/bannerCalendar")
	public String bannerCalendar(@RequestParam(value = "type") String type) {
		return "admin/bannerCalendar";
	}
	

	@GetMapping("/bannerCalendarEvent")
	public @ResponseBody List<Map<String, Object>> bannerCalendarEvent(@RequestParam(value = "type") String type)
	{
		System.out.println("bannerCalendarEvent");
		 return calendarService.getEventList(type);
	}
	


}
