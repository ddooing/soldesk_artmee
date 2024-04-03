package kr.co.softsoldesk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.Beans.GalleryBean;
import kr.co.softsoldesk.Beans.GalleySignFormBean;
import kr.co.softsoldesk.mapper.GalleryMapper;

@Repository
public class GalleryDao {

	@Autowired
	private GalleryMapper galleryMapper;
	
	//회원가입
	public void addUserGalleyInfo(GalleySignFormBean joinUserGalleyBean) {
		galleryMapper.addUserGalleyInfo(joinUserGalleyBean);
	}
	
	// 전시회 등록 신청 시, 전시관 정보 가져오기 
	public GalleryBean getGalleryInfo(int user_id)
	{
		return galleryMapper.getGalleryInfo(user_id);
	}
}
