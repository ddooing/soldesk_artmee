package kr.co.softsoldesk.Service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.Beans.PageBean;
import kr.co.softsoldesk.Beans.StaticsBean;
import kr.co.softsoldesk.dao.StaticsDao;

@Service
public class StaticsService {

	@Autowired
	private StaticsDao staticsDao;
	
	@Value("${admin.listcnt}")
	private int admin_listcnt;
	
	@Value("${admin.paginationcnt}")
	private int admin_paginationcnt;
	
	// 일별 가격 조회
	public List<StaticsBean> getsumprice() {
		return staticsDao.getsumprice();
	}
	
	// 일별 예매 조회
	public List<StaticsBean> getreservestatics() {
		return staticsDao.getreservestatics();
	}
	
	// 미처리 개수 확인
	public StaticsBean getprocesscnt() {
		return staticsDao.getprocesscnt();
	}
	
	// 일자별 요약
	public List<StaticsBean> getdailysummary() {
		return staticsDao.getdailysummary();
	}
	
	// 대시보드 게시판 인기순
	public List<StaticsBean> getboardpopular() {
		return staticsDao.getboardpopular();
	}
	
	// 전시회 통계
	public List<StaticsBean> getexhibitionstatics(String start_date, String end_date, String exhibition_id) {
		return staticsDao.getexhibitionstatics(start_date, end_date, exhibition_id);
	}
	
	// 전시회 통계 값 반환
	public StaticsBean getexhibitionstaticscnt(String start_date, String end_date, String exhibition_id) {
		return staticsDao.getexhibitionstaticscnt(start_date, end_date, exhibition_id);
	}
	
	// 전시회 통계 리스트
	public List<StaticsBean> getexhbitionstaticslist(String start_date, String end_date, String exhibition_id, int page) {
		
		int start = (page - 1) * admin_listcnt;
		RowBounds rowBounds = new RowBounds(start, admin_listcnt);

		return staticsDao.getexhbitionstaticslist(start_date, end_date, exhibition_id, rowBounds);
	}
	
	// 전시회 통계 리스트 페이징 처리 개수 반환
	public PageBean getexhibitionstaticslistcnt(String start_date, String end_date, String exhibition_id, int currentPage) {
		
		int content_Cnt = staticsDao.getexhibitionstaticslistcnt(start_date, end_date, exhibition_id);
		PageBean pageBean = new PageBean(content_Cnt, currentPage, admin_listcnt, admin_paginationcnt);
		
		return pageBean;
	}
	
	// 전시회 상세 페이지 남여비율 통계
	public StaticsBean getexhibitiondetailstatics(int exhibition_id) {
		return staticsDao.getexhibitiondetailstatics(exhibition_id);
	}
}