package kr.co.softsoldesk.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.Beans.MainBannerBean;
import kr.co.softsoldesk.Beans.SubBannerBean;
import kr.co.softsoldesk.dao.AdminDao;

@Service
public class CalendarService {
	
	@Autowired
	private AdminDao adminDao;
	
	public List<Map<String, Object>> getEventList(String type) {
        
        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
        Map<String, Object> event;
        
        String[] colors = {"#6096B4", "#93BFCF", "#BDCDD6"};
        int colorIndex = 0; 
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        if(type.equals("main"))
        {
        	List<MainBannerBean> allMainBannerInfo = adminDao.getAllShowMainbannerInfo();
            
        	for(MainBannerBean b: allMainBannerInfo)
    		{
    			event = new HashMap<String, Object>();
    			event.put("title",b.getTitle());
    			event.put("start",b.getStart_date());
    			LocalDate endDate = LocalDate.parse(b.getEnd_date(), formatter).plusDays(1); 
    	        
    	        event.put("end", endDate.format(formatter));
    	        event.put("color", colors[colorIndex]);
    	        eventList.add(event);
    	        colorIndex++;
    		}
        }
        else if(type.equals("sub"))
        {
			List<SubBannerBean> allSubBannerInfo = adminDao.getAllShowSubbannerInfo();
			for(SubBannerBean b: allSubBannerInfo)
    		{
				
    			event = new HashMap<String, Object>();
    			event.put("title",b.getTitle());
    			event.put("start",b.getStart_date());
    			LocalDate endDate = LocalDate.parse(b.getEnd_date(), formatter).plusDays(1); 
    	        
    	        event.put("end", endDate.format(formatter));
    	        event.put("color", colors[colorIndex]);
    	        eventList.add(event);
    	        colorIndex++;
    		}			
        }

        
        return eventList;
    }
}
