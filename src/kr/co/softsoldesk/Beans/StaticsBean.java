package kr.co.softsoldesk.Beans;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StaticsBean {

	private String approved_date;
	private int daily_total_price;
	private String booking_date;
	private int booking_count;
	// 처리 개수 확인용
	private int qna_processcnt;
	private int banner_processcnt;
	private int exhibition_processcnt;
	
	// 일자별 요약
	private String order_date;
	private int order_count;
	private int total_sales;
	private int sign_up_count;
	private int inquiry_count;
	private int review_count;
	private int canceled_count;
	
	// 전시회 통계
	private String reservation_date;
	private String daily_reservations;
	
	// 전시회 통계값 반환
	private String total_tickets_sold;
	private String total_sales_amount;
	
	// 대시보드 게시판 일주일 인기순(조회수)
	private int board_id;
	private String title;
	private String create_date;
	private String contents;
	private int views;
	
	// 전시회 통계 리스트
	private int reserve_id;
	private String name;
	private String approved_at;
	private String reserve_date;
	private int ticket_count;
	private int total_price;
	
	// 페이징처리개수반환
	private int total_rows;
	
	// 전시회 상세 페이지 통계
	private int age;
	private int total_male;
	private int total_female;
	private double male_10;
	private double male_20;
	private double male_30;
	private double male_40;
	private double male_50;
	private double male_60;
	private double female_10;
	private double female_20;
	private double female_30;
	private double female_40;
	private double female_50;
	private double female_60;
	private double male_Percentage;
	private double female_Percentage;
	public String getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}
	public int getDaily_total_price() {
		return daily_total_price;
	}
	public void setDaily_total_price(int daily_total_price) {
		this.daily_total_price = daily_total_price;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	public int getBooking_count() {
		return booking_count;
	}
	public void setBooking_count(int booking_count) {
		this.booking_count = booking_count;
	}
	public int getQna_processcnt() {
		return qna_processcnt;
	}
	public void setQna_processcnt(int qna_processcnt) {
		this.qna_processcnt = qna_processcnt;
	}
	public int getBanner_processcnt() {
		return banner_processcnt;
	}
	public void setBanner_processcnt(int banner_processcnt) {
		this.banner_processcnt = banner_processcnt;
	}
	public int getExhibition_processcnt() {
		return exhibition_processcnt;
	}
	public void setExhibition_processcnt(int exhibition_processcnt) {
		this.exhibition_processcnt = exhibition_processcnt;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public int getTotal_sales() {
		return total_sales;
	}
	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}
	public int getSign_up_count() {
		return sign_up_count;
	}
	public void setSign_up_count(int sign_up_count) {
		this.sign_up_count = sign_up_count;
	}
	public int getInquiry_count() {
		return inquiry_count;
	}
	public void setInquiry_count(int inquiry_count) {
		this.inquiry_count = inquiry_count;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	public String getDaily_reservations() {
		return daily_reservations;
	}
	public void setDaily_reservations(String daily_reservations) {
		this.daily_reservations = daily_reservations;
	}
	public String getTotal_tickets_sold() {
		return total_tickets_sold;
	}
	public void setTotal_tickets_sold(String total_tickets_sold) {
		this.total_tickets_sold = total_tickets_sold;
	}
	public String getTotal_sales_amount() {
		return total_sales_amount;
	}
	public void setTotal_sales_amount(String total_sales_amount) {
		this.total_sales_amount = total_sales_amount;
	}
	public int getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(int reserve_id) {
		this.reserve_id = reserve_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	public int getTicket_count() {
		return ticket_count;
	}
	public void setTicket_count(int ticket_count) {
		this.ticket_count = ticket_count;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getTotal_male() {
		return total_male;
	}
	public void setTotal_male(int total_male) {
		this.total_male = total_male;
	}
	public int getTotal_female() {
		return total_female;
	}
	public void setTotal_female(int total_female) {
		this.total_female = total_female;
	}
	public double getMale_10() {
		return male_10;
	}
	public void setMale_10(double male_10) {
		this.male_10 = male_10;
	}
	public double getMale_20() {
		return male_20;
	}
	public void setMale_20(double male_20) {
		this.male_20 = male_20;
	}
	public double getMale_30() {
		return male_30;
	}
	public void setMale_30(double male_30) {
		this.male_30 = male_30;
	}
	public double getMale_40() {
		return male_40;
	}
	public void setMale_40(double male_40) {
		this.male_40 = male_40;
	}
	public double getMale_50() {
		return male_50;
	}
	public void setMale_50(double male_50) {
		this.male_50 = male_50;
	}
	public double getMale_60() {
		return male_60;
	}
	public void setMale_60(double male_60) {
		this.male_60 = male_60;
	}
	public double getFemale_10() {
		return female_10;
	}
	public void setFemale_10(double female_10) {
		this.female_10 = female_10;
	}
	public double getFemale_20() {
		return female_20;
	}
	public void setFemale_20(double female_20) {
		this.female_20 = female_20;
	}
	public double getFemale_30() {
		return female_30;
	}
	public void setFemale_30(double female_30) {
		this.female_30 = female_30;
	}
	public double getFemale_40() {
		return female_40;
	}
	public void setFemale_40(double female_40) {
		this.female_40 = female_40;
	}
	public double getFemale_50() {
		return female_50;
	}
	public void setFemale_50(double female_50) {
		this.female_50 = female_50;
	}
	public double getFemale_60() {
		return female_60;
	}
	public void setFemale_60(double female_60) {
		this.female_60 = female_60;
	}
	public double getMale_Percentage() {
		return male_Percentage;
	}
	public void setMale_Percentage(double male_Percentage) {
		this.male_Percentage = male_Percentage;
	}
	public double getFemale_Percentage() {
		return female_Percentage;
	}
	public void setFemale_Percentage(double female_Percentage) {
		this.female_Percentage = female_Percentage;
	}
	public int getCanceled_count() {
		return canceled_count;
	}
	public void setCanceled_count(int canceled_count) {
		this.canceled_count = canceled_count;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}

	
	
	
}
