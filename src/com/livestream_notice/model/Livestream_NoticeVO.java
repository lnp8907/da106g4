package com.livestream_notice.model;
import java.sql.Date;
import java.sql.Timestamp;

public class Livestream_NoticeVO implements java.io.Serializable{
	private String lsnotice_id;
	private String livestream_id;
	private String member_id;
	private Timestamp notice_time;
	private String content;
	private Integer co_status;
	
	public String getLsnotice_id() {
		return lsnotice_id;
	}
	public void setLsnotice_id(String lsnotice_id) {
		this.lsnotice_id = lsnotice_id;
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
	public Timestamp getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(Timestamp notice_time) {
		this.notice_time = notice_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCo_status() {
		return co_status;
	}
	public void setCo_status(Integer co_status) {
		this.co_status = co_status;
	}
	

}
