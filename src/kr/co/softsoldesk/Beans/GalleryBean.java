package kr.co.softsoldesk.Beans;

public class GalleryBean {

	private int gallery_id;
	private String gallery_name;
	private String gallery_telephone;
	private String gallery_email;
	private String open;
	private String open_time;						// open_time + close_time = open
	private String close_time;
	
	private String holiday;
	private String address;
	private String place;
	private String site;

	//포함해야하는?
	private double latitude;
	private double longitude;
	private int state;
	
	public int getGallery_id() {
		return gallery_id;
	}
	public void setGallery_id(int gallery_id) {
		this.gallery_id = gallery_id;
	}
	public String getGallery_name() {
		return gallery_name;
	}
	public void setGallery_name(String gallery_name) {
		this.gallery_name = gallery_name;
	}
	public String getGallery_telephone() {
		return gallery_telephone;
	}
	public void setGallery_telephone(String gallery_telephone) {
		this.gallery_telephone = gallery_telephone;
	}
	public String getGallery_email() {
		return gallery_email;
	}
	public void setGallery_email(String gallery_email) {
		this.gallery_email = gallery_email;
	}
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getClose_time() {
		return close_time;
	}
	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	
	
 
}
