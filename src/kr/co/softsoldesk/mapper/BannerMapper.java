package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.Beans.BannerApplyFormBean;
import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.ExhibitionDetailBean;

public interface BannerMapper {

	@Insert({"insert into banner_apply_form (apply_person_id, exhibition_id, start_date, end_date, command, payment, banner_type, banner_file_id,order_id,pay_state,pay_approval_state) values (#{apply_person_id}, #{exhibition_id}, TO_DATE(#{start_date, jdbcType=VARCHAR}, 'YYYY-MM-DD'),TO_DATE(#{end_date, jdbcType=VARCHAR}, 'YYYY-MM-DD'),#{command},#{payment} , #{banner_type},#{banner_file_id},#{order_id},0,0)"})
	  void insertbanner_apply(BannerApplyFormBean paramBannerApplyFormBean);
	  
	  @Insert({"INSERT INTO file_table (file_id, name, path, file_date) values (file_id_seq.nextval, #{name, jdbcType=VARCHAR}, #{path, jdbcType=VARCHAR}, sysdate)"})
	  void addfiletableBanner1(BannerApplyFormBean paramBannerApplyFormBean);
	  
	  @Select({"SELECT file_id from file_table where name=#{name}"})
	  int getFileId(String paramString);
	  
	  @Select({"SELECT e.exhibition_id,e.title FROM exhibition e "
	  		+ "INNER JOIN user_table u ON e.apply_person_id = u.user_id  "
	  		+ "WHERE u.user_id = #{user_id} AND e.exhibition_end >= CURRENT_DATE"})
	  List<ExhibitionBean> getApply_personExhibitionlist(@Param("user_id") int paramInt);
	  
	  @Select({"SELECT order_id,payment,banner_type FROM banner_apply_form where order_id=#{orderId}"})
	  BannerApplyFormBean validcheckOrderId(String paramString);
	  
	  @Update({"UPDATE banner_apply_form SET pay_state = 1, paymentkey=#{paymentKey} ,pay_approval_state=1, state = 1, requested_at = TO_TIMESTAMP_TZ(#{requested_at}, 'YYYY-MM-DD\"T\"HH24:MI:SS.FF TZH:TZM'), approved_at = TO_TIMESTAMP_TZ(#{approved_at}, 'YYYY-MM-DD\"T\"HH24:MI:SS.FF TZH:TZM'), payment_method=#{method} WHERE order_id = #{orderId}"})
	  void realPaymentState(@Param("orderId") String paramString1, @Param("requested_at") String paramString2, @Param("approved_at") String paramString3, @Param("method") String paramString4, @Param("paymentKey") String paramString5);
	  
	  @Select({"SELECT banner_apply_form_id, apply_person_id,exhibition_id,TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, command,payment,state,banner_type,banner_file_id,\r\nTO_CHAR(requested_at, 'YYYY-MM-DD HH24:MI:SS') as requested_at, TO_CHAR(approved_at, 'YYYY-MM-DD HH24:MI:SS') as approved_at, pay_state,pay_approval_state, order_id,payment_method,paymentkey FROM banner_apply_form where order_id=#{orderId}"})
	  BannerApplyFormBean getBannerPaymentInfo(String paramString);
	  
	  @Select({"SELECT requested_at FROM (SELECT requested_at FROM banner_apply_form where state IS NOT NULL ORDER BY banner_apply_form_id asc)\r\nWHERE ROWNUM = 1"})
	  String getFirstPayDate();
	  
	  //관리자 - 배너 결제 정보 가져오기 
			@Select("<script>" +
					"SELECT count(*) "+
			        "FROM banner_apply_form b " +
			        "INNER JOIN user_table u ON b.apply_person_id = u.user_id " +
			        "WHERE b.state IS NOT NULL " +
			        "<if test='startDate != null and endDate != null'>" +
			        "AND b.requested_at BETWEEN #{startDate} AND #{endDate} </if> " +
			        "<if test='payment_method != null'>AND b.payment_method = #{payment_method}</if> " +
			        "<if test='banner_type != null &amp;&amp; banner_type != 3'>AND b.banner_type = #{banner_type}</if>"+
			        "<if test='user_name != null'>AND u.name LIKE '%' || #{user_name} || '%' </if> " +
			        "ORDER BY b.banner_apply_form_id DESC" +
			        "</script>")
			public int getBannerPaymentInfoListCnt(@Param("startDate") String startDate, 
			                                                          @Param("endDate") String endDate, 
			                                                          @Param("payment_method") String paymentMethod, 
			                                                          @Param("banner_type") Integer banner_type, 
			                                                          @Param("user_name") String userName);

	 @Select("<script>" +
			        "SELECT b.banner_apply_form_id, b.payment, " +
			        "TO_CHAR(b.requested_at, 'YYYY-MM-DD HH24:MI:SS') as requested_at, " +
			        "TO_CHAR(b.approved_at, 'YYYY-MM-DD HH24:MI:SS') as approved_at, " +
			        "b.state, b.pay_state, b.pay_approval_state, b.order_id, b.payment_method, b.paymentkey, u.name, b.banner_type " +
			        "FROM banner_apply_form b " +
			        "INNER JOIN user_table u ON b.apply_person_id = u.user_id " +
			        "WHERE b.state IS NOT NULL " +
			        "<if test='startDate != null and endDate != null'>" +
			        "AND b.requested_at BETWEEN #{startDate} AND #{endDate} </if> " +
			        "<if test='payment_method != null'>AND b.payment_method = #{payment_method}</if> " +
			        "<if test='banner_type != null &amp;&amp; banner_type != 3'>AND b.banner_type = #{banner_type}</if>"+
			        "<if test='user_name != null'>AND u.name LIKE '%' || #{user_name} || '%' </if> " +
			        "ORDER BY b.banner_apply_form_id DESC" +
			        "</script>")
			public List<BannerApplyFormBean> getBannerPaymentInfoList(@Param("startDate") String startDate, 
			                                                          @Param("endDate") String endDate, 
			                                                          @Param("payment_method") String paymentMethod, 
			                                                          @Param("banner_type") Integer banner_type, 
			                                                          @Param("user_name") String user_name,RowBounds rowBounds);
	 
	  @Update({"update banner_apply_form set state=3 where banner_apply_form_id=#{banner_apply_form_id}"})
	  void getCancelBanner(int paramInt);
	  
	  @Select({"SELECT name as name, path as path from file_table where file_id = #{banner_file_id}"})
	  ExhibitionDetailBean getfileinfo(@Param("banner_file_id") int paramInt);
	  
	  @Select({"SELECT title from exhibition where exhibition_id = #{exhibition_id}"})
	  String getexhibition_title(int paramInt);
}