package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.Beans.StaticsBean;

public interface StaticsMapper {

	// 대시 보드 페이지 일별 판매 금액
	@Select("WITH date_range AS (\r\n"
			+ "    SELECT TRUNC(SYSDATE) - LEVEL + 1 AS approved_date\r\n"
			+ "    FROM dual\r\n"
			+ "    CONNECT BY LEVEL <= 14\r\n"
			+ "),\r\n"
			+ "sales_data AS (\r\n"
			+ "    SELECT\r\n"
			+ "        TRUNC(approved_At) AS approved_date,\r\n"
			+ "        SUM(total_price) AS daily_total_price\r\n"
			+ "    FROM\r\n"
			+ "        reserve\r\n"
			+ "    WHERE\r\n"
			+ "        approved_At >= TRUNC(SYSDATE) - 13\r\n"
			+ "        AND approved_At < TRUNC(SYSDATE) + 1\r\n"
			+ "    GROUP BY\r\n"
			+ "        TRUNC(approved_At)\r\n"
			+ ")\r\n"
			+ "SELECT\r\n"
			+ "    TO_CHAR(dr.approved_date, 'YYYY-MM-DD') AS approved_date, -- 날짜 형식 지정\r\n"
			+ "    COALESCE(sd.daily_total_price, 0) AS daily_total_price\r\n"
			+ "FROM\r\n"
			+ "    date_range dr\r\n"
			+ "LEFT JOIN sales_data sd ON dr.approved_date = sd.approved_date\r\n"
			+ "ORDER BY\r\n"
			+ "    dr.approved_date\r\n"
			+ "")
	List<StaticsBean> getsumstatics();
	
	// 대시보드 페이지 일별예매차트
	@Select("WITH date_range AS (\r\n"
			+ "    SELECT TRUNC(SYSDATE) - LEVEL + 1 AS date_generated\r\n"
			+ "    FROM dual\r\n"
			+ "    CONNECT BY LEVEL <= 7\r\n"
			+ ")\r\n"
			+ "SELECT \r\n"
			+ "    TO_CHAR(dr.date_generated, 'YYYY-MM-DD') AS booking_date, \r\n"
			+ "    COALESCE(SUM(r.booking_count), 0) AS booking_count\r\n"
			+ "FROM \r\n"
			+ "    date_range dr\r\n"
			+ "    LEFT JOIN (\r\n"
			+ "        SELECT \r\n"
			+ "            TRUNC(approved_At) AS approved_date, \r\n"
			+ "            COUNT(*) AS booking_count\r\n"
			+ "        FROM \r\n"
			+ "            reserve\r\n"
			+ "        WHERE \r\n"
			+ "            approved_At >= TRUNC(SYSDATE) - 6\r\n"
			+ "            AND approved_At < TRUNC(SYSDATE) + 1\r\n"
			+ "        GROUP BY \r\n"
			+ "            TRUNC(approved_At)\r\n"
			+ "    ) r ON dr.date_generated = r.approved_date\r\n"
			+ "GROUP BY \r\n"
			+ "    dr.date_generated\r\n"
			+ "ORDER BY \r\n"
			+ "    booking_date")
	List<StaticsBean> getreservestatics();
	
	// 대시보드 페이지 미처리 현황
	@Select("SELECT \r\n"
			+ "  (SELECT COUNT(*) FROM QnA WHERE state = 0) AS qna_processcnt,\r\n"
			+ "  (SELECT COUNT(*) FROM banner_apply_form WHERE state = 1) AS banner_processcnt,\r\n"
			+ "  (SELECT COUNT(*) FROM exhibition_enroll WHERE enroll_state = 1) AS exhibition_processcnt\r\n"
			+ "FROM dual")
	StaticsBean getprocesscnt();
	
	// 대시보드 페이지 일자별 요약
	@Select("SELECT \r\n"
			+ "  TO_CHAR(order_date, 'YYYY-MM-DD') AS order_date, \r\n"
			+ "  NVL(order_count, 0) AS order_count, \r\n"
			+ "  NVL(total_sales, 0) AS total_sales, \r\n"
			+ "  NVL(sign_up_count, 0) AS sign_up_count, \r\n"
			+ "  NVL(inquiry_count, 0) AS inquiry_count, \r\n"
			+ "  NVL(review_count, 0) AS review_count,\r\n"
			+ "  NVL(canceled_count, 0) AS canceled_count -- Add the canceled_count\r\n"
			+ "FROM (\r\n"
			+ "  SELECT TRUNC(SYSDATE) - LEVEL + 1 AS order_date\r\n"
			+ "  FROM dual\r\n"
			+ "  CONNECT BY LEVEL <= 7\r\n"
			+ ") dates\r\n"
			+ "LEFT JOIN (\r\n"
			+ "  SELECT TRUNC(approved_At) AS reserve_date, COUNT(*) AS order_count, SUM(payment) AS total_sales\r\n"
			+ "  FROM reserve\r\n"
			+ "  WHERE approved_At >= TRUNC(SYSDATE) - INTERVAL '6' DAY\r\n"
			+ "    AND approved_At < TRUNC(SYSDATE) + INTERVAL '1' DAY\r\n"
			+ "  GROUP BY TRUNC(approved_At)\r\n"
			+ ") reserves ON dates.order_date = reserves.reserve_date\r\n"
			+ "LEFT JOIN (\r\n"
			+ "  SELECT TRUNC(create_date) AS join_date, COUNT(*) AS sign_up_count\r\n"
			+ "  FROM user_table\r\n"
			+ "  WHERE create_date >= TRUNC(SYSDATE) - INTERVAL '6' DAY\r\n"
			+ "    AND create_date < TRUNC(SYSDATE) + INTERVAL '1' DAY\r\n"
			+ "  GROUP BY TRUNC(create_date)\r\n"
			+ ") sign_ups ON dates.order_date = sign_ups.join_date\r\n"
			+ "LEFT JOIN (\r\n"
			+ "  SELECT TRUNC(regdate) AS qna_date, COUNT(*) AS inquiry_count\r\n"
			+ "  FROM QnA\r\n"
			+ "  WHERE regdate >= TRUNC(SYSDATE) - INTERVAL '6' DAY\r\n"
			+ "    AND regdate < TRUNC(SYSDATE) + INTERVAL '1' DAY\r\n"
			+ "  GROUP BY TRUNC(regdate)\r\n"
			+ ") qnas ON dates.order_date = qnas.qna_date\r\n"
			+ "LEFT JOIN (\r\n"
			+ "  SELECT TRUNC(create_date) AS review_date, COUNT(*) AS review_count\r\n"
			+ "  FROM review\r\n"
			+ "  WHERE create_date >= TRUNC(SYSDATE) - INTERVAL '6' DAY\r\n"
			+ "    AND create_date < TRUNC(SYSDATE) + INTERVAL '1' DAY\r\n"
			+ "  GROUP BY TRUNC(create_date)\r\n"
			+ ") reviews ON dates.order_date = reviews.review_date\r\n"
			+ "LEFT JOIN (\r\n"
			+ "  SELECT TRUNC(approved_At) AS canceled_date, COUNT(*) AS canceled_count\r\n"
			+ "  FROM reserve\r\n"
			+ "  WHERE state = 0\r\n"
			+ "    AND approved_At >= TRUNC(SYSDATE) - INTERVAL '6' DAY\r\n"
			+ "    AND approved_At < TRUNC(SYSDATE) + INTERVAL '1' DAY\r\n"
			+ "  GROUP BY TRUNC(approved_At)\r\n"
			+ ") canceled_reserves ON dates.order_date = canceled_reserves.canceled_date \r\n"
			+ "ORDER BY order_date")
			List<StaticsBean> getdailysummary();
	
	// 대시보드 최근 일주일 게시판 인기순(조회순)
	@Select("SELECT *\r\n"
			+ "FROM (\r\n"
			+ "    SELECT\r\n"
			+ "        board_id,\r\n"
			+ "        title,\r\n"
			+ "        create_date,\r\n"
			+ "        views,\r\n"
			+ "        CASE\r\n"
			+ "            WHEN LENGTH(contents) > 15 THEN SUBSTR(contents, 1, 15) || '...'\r\n"
			+ "            ELSE contents\r\n"
			+ "        END AS contents\r\n"
			+ "    FROM\r\n"
			+ "        board\r\n"
			+ "    WHERE\r\n"
			+ "        create_date >= (CURRENT_DATE - INTERVAL '7' DAY)\r\n"
			+ "        AND state = 1\r\n"
			+ "    ORDER BY\r\n"
			+ "        views DESC\r\n"
			+ ")\r\n"
			+ "WHERE ROWNUM <= 5")
	List<StaticsBean> getboardpopular();
	
	// 전시회 기간별 통계
	@Select("WITH date_series AS (\r\n"
			+ "  SELECT TO_DATE(#{start_date}, 'YYYY-MM-DD') + LEVEL - 1 AS generated_date\r\n"
			+ "  FROM DUAL\r\n"
			+ "  CONNECT BY LEVEL <= TO_DATE(#{end_date}, 'YYYY-MM-DD') - TO_DATE(#{start_date}, 'YYYY-MM-DD') + 1\r\n"
			+ ")\r\n"
			+ "SELECT\r\n"
			+ "  To_char(ds.generated_date,'YYYY-MM-DD') AS reservation_date,\r\n"
			+ "  NVL(COUNT(r.reserve_id), 0) AS daily_reservations\r\n"
			+ "FROM\r\n"
			+ "  date_series ds\r\n"
			+ "LEFT JOIN reserve r\r\n"
			+ "  ON ds.generated_date = TRUNC(r.approved_At)\r\n"
			+ "  AND r.exhibition_id = #{exhibition_id}\r\n"
			+ "LEFT JOIN exhibition e\r\n"
			+ "  ON r.exhibition_id = e.exhibition_id\r\n"
			+ "  AND e.exhibition_start <= r.approved_At\r\n"
			+ "  AND e.exhibition_end >= r.approved_At\r\n"
			+ "WHERE\r\n"
			+ "  ds.generated_date BETWEEN TO_DATE(#{start_date}, 'YYYY-MM-DD') AND TO_DATE(#{end_date}, 'YYYY-MM-DD')\r\n"
			+ "GROUP BY\r\n"
			+ "  ds.generated_date\r\n"
			+ "ORDER BY\r\n"
			+ "  ds.generated_date")
	List<StaticsBean> getexhibitionstatics(@Param("start_date") String start_date, 
	                                       @Param("end_date") String end_date, 
	                                       @Param("exhibition_id") String exhibition_id);

	@Select("SELECT\r\n"
			+ "     COALESCE(SUM(r.ticket_count), 0) AS total_tickets_sold,\r\n"
			+ "    COALESCE(SUM(r.total_price), 0) AS total_sales_amount\r\n"
			+ "FROM\r\n"
			+ "    reserve r\r\n"
			+ "WHERE\r\n"
			+ "    r.exhibition_id = #{exhibition_id}\r\n"
			+ "    AND r.approved_At BETWEEN TO_DATE(#{start_date}, 'YYYY-MM-DD') AND TO_DATE(#{end_date}, 'YYYY-MM-DD') + INTERVAL '1' DAY - INTERVAL '1' SECOND")
	StaticsBean getexhibitionstaticscnt(@Param("start_date") String start_date, 
	                                    @Param("end_date") String end_date, 
	                                    @Param("exhibition_id") String exhibition_id);

	// 전시회 통계 리스트
	@Select("SELECT\r\n"
			+ "    r.reserve_id,\r\n"
			+ "    u.name,\r\n"
			+ "    TO_CHAR(r.approved_at, 'YYYY-MM-DD HH24:MI:SS') AS approved_at,\r\n"
			+ "    r.reserve_date,\r\n"
			+ "    r.ticket_count,\r\n"
			+ "    r.total_price\r\n"
			+ "FROM\r\n"
			+ "    reserve r\r\n"
			+ "INNER JOIN user_table u ON r.user_id = u.user_id\r\n"
			+ "WHERE\r\n"
			+ "    r.exhibition_id = #{exhibition_id}\r\n"
			+ "    AND r.approved_at BETWEEN TO_DATE(#{start_date}, 'YYYY-MM-DD') AND TO_DATE(#{end_date}, 'YYYY-MM-DD') + 1\r\n"
			+ "ORDER BY\r\n"
			+ "    r.reserve_id DESC")
	List<StaticsBean> getexhbitionstaticslist(@Param("start_date") String start_date, 
									          @Param("end_date") String end_date, 
									          @Param("exhibition_id") String exhibition_id,
									          RowBounds rowBounds);
	
	// 전시회 통계 리스트 페이징 처리 개수 반환
	@Select("SELECT\r\n"
			+ "    COUNT(*) AS total_rows\r\n"
			+ "FROM\r\n"
			+ "    reserve r\r\n"
			+ "INNER JOIN user_table u ON r.user_id = u.user_id\r\n"
			+ "WHERE\r\n"
			+ "    r.exhibition_id = #{exhibition_id}\r\n"
			+ "    AND r.approved_at BETWEEN TO_DATE(#{start_date}, 'YYYY-MM-DD') AND TO_DATE(#{end_date}, 'YYYY-MM-DD') + 1")
	int getexhibitionstaticslistcnt(@Param("start_date") String start_date, 
						            @Param("end_date") String end_date, 
						            @Param("exhibition_id") String exhibition_id);
	
	// 전시회 상세 페이지 남여 비율 통계
	@Select("WITH UserData AS (\r\n"
			+ "  SELECT\r\n"
			+ "    u.user_id,\r\n"
			+ "    u.gender,\r\n"
			+ "    TRUNC(MONTHS_BETWEEN(SYSDATE, u.birth) / 12) AS age\r\n"
			+ "  FROM user_table u\r\n"
			+ "  JOIN reserve r ON u.user_id = r.user_id\r\n"
			+ "  WHERE r.exhibition_id = #{exhibition_id}\r\n"
			+ "), TotalCounts AS (\r\n"
			+ "  SELECT\r\n"
			+ "    COUNT(*) AS total_users,\r\n"
			+ "    SUM(CASE WHEN gender = 'male' THEN 1 ELSE 0 END) AS total_male,\r\n"
			+ "    SUM(CASE WHEN gender = 'female' THEN 1 ELSE 0 END) AS total_female\r\n"
			+ "  FROM UserData\r\n"
			+ ")\r\n"
			+ "SELECT\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'male' AND age BETWEEN 0 AND 19 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS male_10,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'female' AND age BETWEEN 0 AND 19 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS female_10,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'male' AND age BETWEEN 20 AND 29 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS male_20,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'female' AND age BETWEEN 20 AND 29 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS female_20,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'male' AND age BETWEEN 30 AND 39 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS male_30,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'female' AND age BETWEEN 30 AND 39 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS female_30,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'male' AND age BETWEEN 40 AND 49 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS male_40,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'female' AND age BETWEEN 40 AND 49 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS female_40,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'male' AND age BETWEEN 50 AND 59 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS male_50,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'female' AND age BETWEEN 50 AND 59 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS female_50,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'male' AND age >= 60 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS male_60,\r\n"
			+ "  ROUND(COALESCE(SUM(CASE WHEN gender = 'female' AND age >= 60 THEN 1 ELSE 0 END), 0) / MAX(total_users) * 100, 2) AS female_60,\r\n"
			+ "  ROUND(MAX(total_male) / MAX(total_users) * 100, 2) AS male_Percentage,\r\n"
			+ "  ROUND(MAX(total_female) / MAX(total_users) * 100, 2) AS female_Percentage\r\n"
			+ "FROM UserData, TotalCounts\r\n"
			+ "GROUP BY total_users")
	StaticsBean getexhibitiondetailstatics(@Param("exhibition_id") int exhibition_id);
	
}
