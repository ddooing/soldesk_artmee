package kr.co.softsoldesk.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.Beans.PageBean;
import kr.co.softsoldesk.Beans.StaticsBean;
import kr.co.softsoldesk.Service.StaticsService;

@Controller
public class StatisticsController {

	@Autowired
	private StaticsService staticsService;
	
	@GetMapping("/admin/manager_dashboard")
	public String admin_dashboard(Model model) {
		
		// 일별 판매 금액
		List<StaticsBean> getsumpriceBean = staticsService.getsumprice();
		model.addAttribute("getsumpriceBean", getsumpriceBean);
		
		// 일별 예매 차트
		List<StaticsBean> getreserveBean = staticsService.getreservestatics();
		model.addAttribute("getreserveBean", getreserveBean);
		
		// 미처리 개수 확인용
		StaticsBean getprocesscntBean = staticsService.getprocesscnt();
		model.addAttribute("getprocesscntBean",getprocesscntBean);
		
		// 일자별 요약
		List<StaticsBean> getdailysummaryBean = staticsService.getdailysummary();
		model.addAttribute("getdailysummaryBean", getdailysummaryBean);
		
		// 게시판 인기순(조회수순)
		List<StaticsBean> getboardpopularBean = staticsService.getboardpopular();
		model.addAttribute("getboardpopularBean",getboardpopularBean);
		
		return "/admin/manager_dashboard";
	}
	
	@GetMapping("/admin/manager_exhibitionstatics")
	public String manager_exhibitionstatics(Model model,
			@RequestParam(value = "start_date", required = false) String start_date,
		    @RequestParam(value = "end_date", required = false) String end_date,
		    @RequestParam(value = "exhibition_id", required = true) String exhibition_id,
		    @RequestParam(value = "page", defaultValue = "1") int page) {
		
		 if (start_date == null) {
		        LocalDate nineDaysAgo = LocalDate.now().minusDays(9);
		        start_date = nineDaysAgo.toString();
		    }
		    if (end_date == null) {
		        end_date = LocalDate.now().toString();
		    }
		
		// exhibition_id 넘기기
		model.addAttribute("exhibition_id",exhibition_id);
		model.addAttribute("start_date",start_date);
		model.addAttribute("end_date",end_date);
		
		// 차트 값 보내기
		List<StaticsBean> getexhibitionStaticsBean = staticsService.getexhibitionstatics(start_date, end_date, exhibition_id);
		model.addAttribute("getexhibitionStaticsBean", getexhibitionStaticsBean);
		
		// 카드 값 보내기
		StaticsBean getexhibitionstaticscntBean = staticsService.getexhibitionstaticscnt(start_date, end_date, exhibition_id);
		model.addAttribute("getexhibitionstaticscntBean", getexhibitionstaticscntBean);
		
		// 리스트 값 보내기
		List<StaticsBean> getexhbitionstaticslistBean = staticsService.getexhbitionstaticslist(start_date, end_date, exhibition_id,page);
		model.addAttribute("getexhbitionstaticslistBean",getexhbitionstaticslistBean);
		
		// 리스트 페이징 처리
		PageBean pageBean = staticsService.getexhibitionstaticslistcnt(start_date, end_date, exhibition_id, page);
		model.addAttribute("pageBean", pageBean);
		
		return "/admin/manager_exhibitionstatics";
	}
	
	
}
 