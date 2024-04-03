package kr.co.softsoldesk.Beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
   private boolean IdExist;
   private boolean NickExist;
      private boolean PassExist;
      private boolean userLogin;
      
      public UserBean() {
         this.userLogin = false;
      }
      
      private int user_id;
      private String name;
      private String id;
      private String password;
      private String password2;
      
      //info modify에 사용됨
	      private String icpassword;
	      private String icpasscheck;
      
      private String nickname;
      private String birth;
      private String gender;
      private String email;
      private int point;
      private String telephone;
      private int exp;
      private String create_date;
      private String modify_date;
      private int state;
   
   
	   // 테이블 외 조인 변수들
	   private String grade;
	    private String keyword;
	   // 마이페이지용
	   private int exp_to_next_level;
	   private String next_grade;
	   
	// 유저 수정페이지 개수 반환용
	   private int board_count;
	   private int comment_count;
	   private int qna_count;
	   private int banner_apply_count;
	   private int exhibition_enroll_count;

	   // 아이디 비번찾기 용 인증번호 
	   private int verification_code;
	   private String name1;
	   private String id1;
	   private String email1;

	   private String new_pw;
	   private String new_pw1;
public int getBoard_count() {
	return board_count;
}
public void setBoard_count(int board_count) {
	this.board_count = board_count;
}
public int getComment_count() {
	return comment_count;
}
public void setComment_count(int comment_count) {
	this.comment_count = comment_count;
}
public int getQna_count() {
	return qna_count;
}
public void setQna_count(int qna_count) {
	this.qna_count = qna_count;
}
public int getBanner_apply_count() {
	return banner_apply_count;
}
public void setBanner_apply_count(int banner_apply_count) {
	this.banner_apply_count = banner_apply_count;
}
public int getExhibition_enroll_count() {
	return exhibition_enroll_count;
}
public void setExhibition_enroll_count(int exhibition_enroll_count) {
	this.exhibition_enroll_count = exhibition_enroll_count;
}
public boolean isIdExist() {
	return IdExist;
}
public void setIdExist(boolean idExist) {
	IdExist = idExist;
}
public boolean isNickExist() {
	return NickExist;
}
public void setNickExist(boolean nickExist) {
	NickExist = nickExist;
}
public boolean isPassExist() {
	return PassExist;
}
public void setPassExist(boolean passExist) {
	PassExist = passExist;
}
public boolean isUserLogin() {
	return userLogin;
}
public void setUserLogin(boolean userLogin) {
	this.userLogin = userLogin;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPassword2() {
	return password2;
}
public void setPassword2(String password2) {
	this.password2 = password2;
}
public String getIcpassword() {
	return icpassword;
}
public void setIcpassword(String icpassword) {
	this.icpassword = icpassword;
}
public String getIcpasscheck() {
	return icpasscheck;
}
public void setIcpasscheck(String icpasscheck) {
	this.icpasscheck = icpasscheck;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getBirth() {
	return birth;
}
public void setBirth(String birth) {
	this.birth = birth;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getPoint() {
	return point;
}
public void setPoint(int point) {
	this.point = point;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public int getExp() {
	return exp;
}
public void setExp(int exp) {
	this.exp = exp;
}
public String getCreate_date() {
	return create_date;
}
public void setCreate_date(String create_date) {
	this.create_date = create_date;
}
public String getModify_date() {
	return modify_date;
}
public void setModify_date(String modify_date) {
	this.modify_date = modify_date;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public int getExp_to_next_level() {
	return exp_to_next_level;
}
public void setExp_to_next_level(int exp_to_next_level) {
	this.exp_to_next_level = exp_to_next_level;
}
public String getNext_grade() {
	return next_grade;
}
public void setNext_grade(String next_grade) {
	this.next_grade = next_grade;
}
public int getVerification_code() {
	return verification_code;
}
public void setVerification_code(int verification_code) {
	this.verification_code = verification_code;
}
public String getName1() {
	return name1;
}
public void setName1(String name1) {
	this.name1 = name1;
}
public String getId1() {
	return id1;
}
public void setId1(String id1) {
	this.id1 = id1;
}
public String getEmail1() {
	return email1;
}
public void setEmail1(String email1) {
	this.email1 = email1;
}
public String getNew_pw() {
	return new_pw;
}
public void setNew_pw(String new_pw) {
	this.new_pw = new_pw;
}
public String getNew_pw1() {
	return new_pw1;
}
public void setNew_pw1(String new_pw1) {
	this.new_pw1 = new_pw1;
}
   


}