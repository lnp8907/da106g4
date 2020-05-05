package android.com.livestream.model;
import java.sql.Date;
import java.sql.Timestamp;

public class Livestream implements java.io.Serializable{
	private String livestream_id;
	private String member_id;
	private Date livestream_date;//
	private byte[] video;
	private int status;//
	private byte[] picture;//
	private String introduction;//
	private String title;//
	private Integer watched_num;
	
	public String getLivestream_id() {
		return livestream_id;
	}
	public void setLivestream_id(String livestream_id) {
		this.livestream_id = livestream_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getLivestream_date() {
		return livestream_date;
	}
	public void setLivestream_date(Date livestream_date) {
		this.livestream_date = livestream_date;
	}
	public byte[] getVideo() {
		return video;
	}
	public void setVideo(byte[] video) {
		this.video = video;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduciton) {
		this.introduction = introduciton;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getWatched_num() {
		return watched_num;
	}
	public void setWatched_num(Integer watched_num) {
		this.watched_num = watched_num;
	}
}
