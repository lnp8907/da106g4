package android.com.livestream_collection.model;
import java.sql.Date;
import java.sql.Timestamp;

public class Livestream_Collection implements java.io.Serializable{
	private String livestream_id;
	private String member_id;
	
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
}
