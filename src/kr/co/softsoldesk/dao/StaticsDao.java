package kr.co.softsoldesk.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.Beans.StaticsBean;
import kr.co.softsoldesk.mapper.StaticsMapper;

@Repository
public class StaticsDao {

	@Autowired
	StaticsMapper staticsMapper;
	
	// 일별 가격 차트
	public List<StaticsBean> getsumprice() {
		return staticsMapper.getsumstatics();
	}
	
	// 일별 예매 차트
	public List<StaticsBean> getreservestatics() {
		return staticsMapper.getreservestatics();
	}
	
	// 처리전 개수 확인
	public StaticsBean getprocesscnt() {
		return staticsMapper.getprocesscnt();
	}
	
	// 일자별 요약 
	public List<StaticsBean> getdailysummary() {
		return staticsMapper.getdailysummary();
	}
	
	// 대시보드 게시판 인기
	public List<StaticsBean> getboardpopular() {
		return staticsMapper.getboardpopular();
	}
	
	// 전시회 통계
	public List<StaticsBean> getexhibitionstatics(String start_date, String end_date, String exhibition_id) {
		return staticsMapper.getexhibitionstatics(start_date, end_date, exhibition_id);
	}
	
	// 전시회 통계 값 반환
	public StaticsBean getexhibitionstaticscnt(String start_date, String end_date, String exhibition_id) {
		return staticsMapper.getexhibitionstaticscnt(start_date, end_date, exhibition_id);
	}
	
	// 전시회 통계 리스트
	public List<StaticsBean> getexhbitionstaticslist(String start_date, String end_date, String exhibition_id, RowBounds rowBounds) {
		return staticsMapper.getexhbitionstaticslist(start_date, end_date, exhibition_id, rowBounds);
	}
	
	// 전시회 통계 리스트 페이징처리 개수 반환
	public int getexhibitionstaticslistcnt(String start_date, String end_date, String exhibition_id) {
		return staticsMapper.getexhibitionstaticslistcnt(start_date, end_date, exhibition_id);
	}
	
	// 전시회 상세 페이지 남여 비율 통계
	public StaticsBean getexhibitiondetailstatics(int exhibition_id) {
		return staticsMapper.getexhibitiondetailstatics(exhibition_id);
	}
	
}
