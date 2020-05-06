package andriod.com.donation_record.model;

import java.sql.Timestamp;    //

public class Donation_record  implements java.io.Serializable{
	private String donation_id;
	private String livestream_id;
	private String member_id;
	private Timestamp donation_date;
	private Integer donation_cost;
	
	public String getDonation_id() {
		return donation_id;
	}
	public void setDonation_id(String donation_id) {
		this.donation_id = donation_id;
	}
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
	public Timestamp getDonation_date() {
		return donation_date;
	}
	public void setDonation_date(Timestamp donation_date) {
		this.donation_date = donation_date;
	}
	public Integer getDonation_cost() {
		return donation_cost;
	}
	public void setDonation_cost(Integer donation_cost) {
		this.donation_cost = donation_cost;
	}
	

}
