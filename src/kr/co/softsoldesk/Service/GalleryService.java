package kr.co.softsoldesk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.Beans.GalleryBean;
import kr.co.softsoldesk.Beans.GalleySignFormBean;
import kr.co.softsoldesk.dao.GalleryDao;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	
	//회원가입
	public void addUserGalleyInfo(GalleySignFormBean joinUserGalleyBean) {
		// 전시시간 String으로 open 에 set해줌
		String open = (joinUserGalleyBean.getOpen_time() + " - " + joinUserGalleyBean.getClose_time());
		joinUserGalleyBean.setOpen(open);
		
		galleryDao.addUserGalleyInfo(joinUserGalleyBean);
	}
	// 전시회 등록 신청 시, 전시관 정보 가져오기 
	public GalleryBean getGalleryInfo(int user_id)
	{
		return galleryDao.getGalleryInfo(user_id);
	}
	
}
