package kr.co.softsoldesk.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.Beans.UserBean;

public interface UserMapper {
	
	 @Select({"select user_id, id, password, state from user_table where id = #{id} and password = #{password} and (state=1 or state=3 or state=4)"})
	  UserBean getLoginUserInfo(UserBean paramUserBean);
	  
	  @Select({"select user_id, id, name, password, nickname, TO_CHAR(birth, 'YYYY-MM-DD') AS birth, gender, email, point, REGEXP_REPLACE(telephone, '(.{3})(.+)(.{4})', '\\1-\\2-\\3') telephone, exp, create_date, modify_date, state from user_table where user_id = #{user_id}"})
	  UserBean getLoginUserAllInfo(int paramInt);
	  
	  @Select({"SELECT g.grade as grade FROM user_table u JOIN grade g ON u.exp BETWEEN g.start_exp AND NVL(g.end_exp, u.exp) WHERE u.user_id = #{user_id}"})
	  UserBean getUserGrade(int paramInt);
	  
	  @Update({"UPDATE user_table set point = point + #{point} where user_id = #{user_id}"})
	  void UpdatepointPlus(@Param("point") int paramInt1, @Param("user_id") int paramInt2);
	  
	  @Update({"UPDATE user_table set point = point - #{point} where user_id = #{user_id}"})
	  void UpdatepointMinus(@Param("point") int paramInt1, @Param("user_id") int paramInt2);
	  
	  @Select({"SELECT g.grade as grade FROM user_table u JOIN grade g ON u.exp BETWEEN g.start_exp AND NVL(g.end_exp, u.exp) WHERE u.user_id = #{user_id}"})
	  String getLevel(int paramInt);
	  
	  @Update({"UPDATE user_table set exp = exp + #{exp} where user_id = #{user_id}"})
	  void IncreaseExp(@Param("exp") int paramInt1, @Param("user_id") int paramInt2);
	  
	  @Update({"update user_table set point=point + #{point}, exp=exp+100 where user_id= #{user_id}"})
	  void point_expIncrease(@Param("user_id") int paramInt1, @Param("point") int paramInt2);
	  
	  @Select({"select name from user_table where id = #{id}"})
	  String checkIdExist(String paramString);
	  
	  @Select({"select name from user_table where nickname = #{nickname}"})
	  String checkNickExist(String paramString);
	  
	  @Insert({"insert into user_table(user_id, id, name, password, nickname, birth, gender, email, telephone, create_date, modify_date,state)values (user_id_seq.nextval, #{id}, #{name}, #{password}, #{nickname, jdbcType=VARCHAR}, TO_DATE(#{birth, jdbcType=VARCHAR}, 'YYYY-MM-DD'), #{gender, jdbcType=VARCHAR}, #{email}, #{telephone}, sysdate, null,1)"})
	  void addUserInfo(UserBean paramUserBean);
	  
	  @Select({"select id, name, password from user_table where user_id = #{user_id}"})
	  UserBean getModifyUserInfo(int paramInt);
	  
	  @Update({"UPDATE user_table\r\nset nickname = #{nickname}, \r\nemail = #{email},\r\npassword = #{icpassword}, \r\nmodify_date = sysdate \r\nWHERE user_id = #{user_id} AND password = #{password}"})
	  void modifyUserInfo(UserBean paramUserBean);
	  
	  @Update({"update user_table set state = 2 where id = #{id} and password = #{password}"})
	  void deleteUserInfo(UserBean paramUserBean);
	  
	  @Update({"update user_table set point = point - #{pointMinus} where user_id= #{user_id}"})
	  void getPointMinus(@Param("pointMinus") int paramInt1, @Param("user_id") int paramInt2);
	  
	  @Select({"select user_id from user_table where id= #{id}"})
	  int getUserId(String paramString);
	  
	  @Select({"SELECT COUNT(*) FROM user_table WHERE name = #{name1, jdbcType=VARCHAR} AND email = #{email1, jdbcType=VARCHAR}"})
	  int findUserByNameAndEmail(@Param("name1") String paramString1, @Param("email1") String paramString2);
	  
	  @Select({"SELECT id FROM user_table WHERE name = #{name1, jdbcType=VARCHAR} AND email = #{email1, jdbcType=VARCHAR}"})
	  String findUserId(@Param("name1") String paramString1, @Param("email1") String paramString2);
	  
	  @Select({"SELECT COUNT(*) FROM user_table WHERE name = #{name1, jdbcType=VARCHAR} AND email= #{email1, jdbcType=VARCHAR} AND id = #{id1, jdbcType=VARCHAR}"})
	  int findUserByNameAndEmailAndId(@Param("name1") String paramString1, @Param("email1") String paramString2, @Param("id1") String paramString3);
	  
	  @Select({"SELECT user_id FROM user_table WHERE name = #{name1, jdbcType=VARCHAR} AND email= #{email1, jdbcType=VARCHAR} AND id = #{id1, jdbcType=VARCHAR}"})
	  int finduser_id(@Param("name1") String paramString1, @Param("email1") String paramString2, @Param("id1") String paramString3);
	  
	  @Update({"UPDATE user_table SET password = #{new_pw1} WHERE user_id = #{user_id}"})
	  void changepw(@Param("new_pw1") String paramString, @Param("user_id") int paramInt);
	  
	  @Select({"SELECT COUNT(*) FROM user_table WHERE email = #{email}"})
	  int existemail(@Param("email") String paramString);
}
