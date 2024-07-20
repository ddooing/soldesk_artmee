package kr.co.softsoldesk.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.softsoldesk.Beans.GalleryBean;
import kr.co.softsoldesk.Beans.GalleySignFormBean;


public interface GalleryMapper {

	//회원가입
	@Insert("insert into gallery(gallery_id , gallery_name , gallery_email , gallery_telephone"
			+ ",address,open,holiday,site,gallery_user_id )"
			+ "values (gallery_id_seq.nextval, #{name}, #{email}, #{telephone}, "
			+ "#{address},  #{open}, #{holiday}, #{site}, #{user_id})")
	void addUserGalleyInfo(GalleySignFormBean joinUserGalleyBean);

	// 전시회 등록/배너 신청 시, 전시관 정보 가져오기 
	@Select("select gallery_id ,gallery_name,open,holiday ,address ,site "
			+ "from gallery "
			+ "where gallery_user_id =#{user_id} ")
	GalleryBean getGalleryInfo(int user_id);
}
