package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.ExhibitionDetailBean;
import kr.co.softsoldesk.Beans.ReviewBean;

public interface ExhibitionMapper {
	
	@Select({"SELECT e.exhibition_id, e.title, e.regdate, e.author, e.price, e.exhibition_start, e.exhibition_end, e.open, e.holiday, e.ticket_cnt, e.address, e.place, e.site, e.views, e.state, f1.path as main_poster_path, f1.name as main_poster_name, f2.path as detail_poster_path, f2.name as detail_poster_name FROM exhibition e JOIN file_table f1 ON e.main_poster_file_id = f1.file_id JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id AND SYSDATE BETWEEN e.exhibition_start AND e.exhibition_end AND e.state = 1 ORDER BY e.ticket_cnt desc"})
	  List<ExhibitionBean> getPopularExhibitionInfo(RowBounds paramRowBounds);
	  
	  @Select({"SELECT count(e.exhibition_id) FROM exhibition e JOIN file_table f1 ON e.main_poster_file_id = f1.file_id JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id AND SYSDATE BETWEEN e.exhibition_start AND e.exhibition_end AND e.state = 1 ORDER BY e.ticket_cnt desc"})
	  int getPopularExhibitionCnt();
	  
	  @Select({"SELECT e.exhibition_id, e.title, e.regdate, e.author, e.price, e.exhibition_start, e.exhibition_end, e.open, e.holiday, e.ticket_cnt, e.address, e.place, e.site, e.views, e.state, f1.path as main_poster_path, f1.name as main_poster_name, f2.path as detail_poster_path, f2.name as detail_poster_name FROM exhibition e JOIN file_table f1 ON e.main_poster_file_id = f1.file_id JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id AND exhibition_id = #{exhibition_id}"})
	  ExhibitionBean getExhibitionDetailInfo(int paramInt);
	  
	  @Update({"UPDATE exhibition SET views = views + 1 WHERE exhibition_id = #{exhibition_id}"})
	  void increaseViewsExhibition(int paramInt);
	  
	  @Select({"SELECT \r\n    e.exhibition_id, \r\n    e.title, \r\n    e.regdate, e.author, \r\n    e.price, \r\n    e.exhibition_start, \r\n    e.exhibition_end, \r\n    e.open, \r\n    e.holiday, \r\n    e.ticket_cnt, \r\n    e.address, \r\n    e.place, \r\n    e.site, \r\n    e.views, \r\n    e.state, \r\n    f1.path as main_poster_path, \r\n    f1.name as main_poster_name, \r\n    f2.path as detail_poster_path, \r\n    f2.name as detail_poster_name \r\n"
	  		+ "FROM exhibition e "
	  		+ "JOIN file_table f1 ON e.main_poster_file_id = f1.file_id "
	  		+ "JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id "
	  		+ "WHERE SYSDATE BETWEEN e.exhibition_start AND e.exhibition_end AND e.state = 1 ORDER BY e.exhibition_start desc"})
	  List<ExhibitionBean> getRecentExhibitionInfo(RowBounds paramRowBounds);
	  
	  @Select({"SELECT count(exhibition_id) from exhibition WHERE SYSDATE BETWEEN exhibition_start AND exhibition_end AND state = 1"})
	  int getRecentExhibitionCnt();
	  
	  @Select({"SELECT \r\n    e.exhibition_id, \r\n    e.title, \r\n    e.regdate, \r\n    e.author, \r\n    e.price, \r\n    e.exhibition_start, \r\n    e.exhibition_end, \r\n    e.open, \r\n    e.holiday, \r\n    e.ticket_cnt, \r\n    e.address, \r\n    e.place, \r\n    e.site, \r\n    e.views, \r\n    e.state, \r\n    f1.path as main_poster_path, \r\n    f1.name as main_poster_name, \r\n    f2.path as detail_poster_path, \r\n    f2.name as detail_poster_name \r\nFROM \r\n    exhibition e \r\n    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\nWHERE \r\n    e.exhibition_end BETWEEN SYSDATE AND SYSDATE + INTERVAL '30' DAY AND e.state = 1ORDER BY e.exhibition_end asc"})
	  List<ExhibitionBean> getSoonEndExhibitionInfo(RowBounds paramRowBounds);
	  
	  @Select({"SELECT count(exhibition_id) FROM exhibition WHERE exhibition_end BETWEEN SYSDATE AND SYSDATE + INTERVAL '30' DAY AND state = 1"})
	  int getsoonEndExhibitionCnt();
	  
	  @Select({"SELECT \r\n    e.exhibition_id, \r\n    e.title, \r\n    e.regdate, \r\n    e.author, \r\n    e.price, \r\n    e.exhibition_start, \r\n    e.exhibition_end, \r\n    e.open, \r\n    e.holiday, \r\n    e.ticket_cnt, \r\n    e.address, \r\n    e.place, \r\n    e.site, \r\n    e.views, \r\n    e.state, \r\n    f1.path as main_poster_path, \r\n    f1.name as main_poster_name, \r\n    f2.path as detail_poster_path, \r\n    f2.name as detail_poster_name \r\nFROM \r\n    exhibition e \r\n    "
	  		+ "JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    "
	  		+ "JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\n"
	  		+ "WHERE SYSDATE BETWEEN e.exhibition_start AND e.exhibition_end AND e.price = 0 AND e.state = 1"})
	  List<ExhibitionBean> getFreeExhibitionInfo(RowBounds paramRowBounds);
	  
	  @Select({"SELECT count(exhibition_id) FROM exhibition WHERE SYSDATE BETWEEN exhibition_start AND exhibition_end AND price = 0 AND state = 1"})
	  int getFreeExhibitionCnt();
	  
	  @Select({"SELECT e.exhibition_id, e.title, e.ticket_cnt, f.path AS main_poster_path, f.name AS main_poster_name\r\nFROM exhibition e\r\nJOIN file_table f ON e.main_poster_file_id = f.file_id\r\nORDER BY e.ticket_cnt DESC\r\nFETCH FIRST 8 ROWS ONLY"})
	  List<ExhibitionBean> getIndexPagePopularExhibitionInfo();
	  
	  @Select({"SELECT e.exhibition_id, e.title, e.ticket_cnt, f.path AS main_poster_path, f.name AS main_poster_name\r\nFROM exhibition e\r\nJOIN file_table f ON e.main_poster_file_id = f.file_id\r\nWHERE e.exhibition_start >= CURRENT_DATE\r\nORDER BY e.exhibition_start ASC\r\nFETCH FIRST 8 ROWS ONLY"})
	  List<ExhibitionBean> getIndexPageSoonExhibitionInfo();
	  
	  @Select({"SELECT e.exhibition_id, e.title, e.ticket_cnt, f.path AS main_poster_path, f.name AS main_poster_name\r\nFROM exhibition e\r\nJOIN file_table f ON e.main_poster_file_id = f.file_id\r\nWHERE e.exhibition_start <= CURRENT_DATE AND e.exhibition_end >= CURRENT_DATE\r\nORDER BY DBMS_RANDOM.VALUE\r\nFETCH FIRST 8 ROWS ONLY"})
	  List<ExhibitionBean> getIndexPagecurrentExhibitionInfo();
	  
	  @Select({"SELECT\r\n    r.contents,\r\n    CASE \r\n        WHEN LENGTH(r.contents) > 47 THEN SUBSTR(r.contents, 1, 47) || '...'\r\n        ELSE r.contents \r\n    END AS short_contents,\r\n    u.nickname,\r\n    r.expose,\r\n    r.rating,\r\n    (SELECT g.grade FROM grade g WHERE u.exp >= g.start_exp AND (u.exp <= g.end_exp OR g.end_exp IS NULL)) AS grade\r\nFROM\r\n    review r\r\n    JOIN reserve rv ON r.reserve_id = rv.reserve_id\r\n    JOIN user_table u ON rv.user_id = u.user_id\r\nWHERE\r\n    r.contents IS NOT NULL\r\n    AND r.expose = 1\r\n    AND rv.exhibition_id = #{exhibition_id}\r\nORDER BY\r\n    r.review_id DESC"})
	  List<ReviewBean> getExhibition_clickReviewAllInfo(int paramInt, RowBounds paramRowBounds);
	  
	  @Select({"SELECT COALESCE(AVG(r.rating), 0.0) AS average_rating\r\nFROM review r\r\nJOIN reserve rv ON r.reserve_id = rv.reserve_id\r\nWHERE rv.exhibition_id = #{exhibition_id}\r\n"})
	  double getExhibitionReviewAVG(@Param("exhibition_id") int paramInt);
	  
	  @Select({"SELECT\r\n    COUNT(*)\r\nFROM\r\n    review r\r\n    JOIN reserve rv ON r.reserve_id = rv.reserve_id\r\n    JOIN user_table u ON rv.user_id = u.user_id\r\nWHERE\r\n    r.expose = 1\r\n    AND rv.exhibition_id = #{exhibition_id}"})
	  int getExhibitionReviewCnt(int paramInt);
	  
	  @Select({"select title from exhibition where exhibition_id=#{exhibition_id}"})
	  String getExhibitionTitle(int paramInt);
	  
	  @Update({"update exhibition set ticket_cnt= ticket_cnt + #{ticket_cnt}  where exhibition_id=#{exhibition_id}"})
	  void increase_exhibitionTotalTicket(@Param("exhibition_id") int paramInt1, @Param("ticket_cnt") int paramInt2);
	  
	  @Select({"SELECT \r\n    e.exhibition_id, \r\n    e.title, \r\n    e.regdate, \r\n    e.author, \r\n    e.price, \r\n    e.exhibition_start, \r\n    e.exhibition_end, \r\n    e.open, \r\n    e.holiday, \r\n    e.ticket_cnt, \r\n    e.address, \r\n    e.place, \r\n    e.site, \r\n    e.views, \r\n    e.state, \r\n    f1.path as main_poster_path, \r\n    f1.name as main_poster_name, \r\n    f2.path as detail_poster_path, \r\n    f2.name as detail_poster_name \r\nFROM \r\n    exhibition e \r\n    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\nWHERE \r\n    UPPER(e.title) LIKE '%' || upper(#{title}) || '%' and state = 1order by exhibition_id desc"})
	  List<ExhibitionBean> SearchExhibition(@Param("title") String paramString, RowBounds paramRowBounds);
	  
	  @Select({"SELECT \r\n    e.exhibition_id, \r\n    e.title, \r\n    e.regdate, \r\n    e.author, \r\n    e.price, \r\n    e.exhibition_start, \r\n    e.exhibition_end, \r\n    e.open, \r\n    e.holiday, \r\n    e.ticket_cnt, \r\n    e.address, \r\n    e.place, \r\n    e.site, \r\n    e.views, \r\n    e.state, \r\n    f1.path as main_poster_path, \r\n    f1.name as main_poster_name, \r\n    f2.path as detail_poster_path, \r\n    f2.name as detail_poster_name \r\nFROM \r\n    exhibition e \r\n    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\nWHERE \r\n    UPPER(e.title) LIKE '%' || upper(#{title}) || '%' and state = 1order by exhibition_id desc FETCH FIRST 4 ROWS ONLY"})
	  List<ExhibitionBean> allSearchExhibition(String paramString);
	  
	  @Select({"SELECT \r\n    count(*)\r\nFROM \r\n    exhibition e \r\n    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\nWHERE \r\n    UPPER(e.title) LIKE '%' || upper(#{title}) || '%' and state = 1"})
	  int SearchExhibitionCnt(@Param("title") String paramString);
	  
	  @Select({"SELECT \r\n    e.exhibition_id, \r\n    e.title, \r\n    e.regdate, \r\n    e.author, \r\n    e.price, \r\n    e.exhibition_start, \r\n    e.exhibition_end, \r\n    e.open, \r\n    e.holiday, \r\n    e.ticket_cnt, \r\n    e.address, \r\n    e.place, \r\n    e.site, \r\n    e.views, \r\n    e.state, \r\n    f1.path as main_poster_path, \r\n    f1.name as main_poster_name, \r\n    f2.path as detail_poster_path, \r\n    f2.name as detail_poster_name \r\nFROM \r\n    exhibition e \r\n    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id\r\nwhere state = 1 order by exhibition_id desc"})
	  List<ExhibitionBean> AllExhibition(RowBounds paramRowBounds);
	  
	  @Select({"SELECT \r\n    count(*)\r\nFROM \r\n    exhibition e \r\n    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id where state = 1"})
	  int AllExhibitionCnt();
	  
	  @Insert({"INSERT INTO exhibition_enroll (title, place, regdate, author, address, price, enroll_state, exhibition_start, exhibition_end, open, holiday, site, apply_person, main_poster_file_id, detail_poster_file_id) VALUES (#{title}, #{place}, sysdate, #{author}, #{address, jdbcType=VARCHAR}, #{price}, #{enroll_state}, TO_DATE(#{exhibition_start}, 'YYYY-MM-DD'), TO_DATE(#{exhibition_end}, 'YYYY-MM-DD'), #{open}, #{holiday}, #{site}, #{apply_person}, #{main_poster_file_id}, #{detail_poster_file_id})"})
	  void AddExhibition_Enroll(ExhibitionDetailBean paramExhibitionDetailBean);
	  
	  @Select({"Select exhibition_id, title from exhibition"})
	  List<ExhibitionBean> getexhibitionallTitle();
	  
	  @Update({"update exhibition set ticket_cnt  = ticket_cnt - #{ticket_count} where exhibition_id=#{exhibition_id}"})
	  void setExhibitionTicketCntMinus(@Param("ticket_count") int paramInt1, @Param("exhibition_id") int paramInt2);
}	
