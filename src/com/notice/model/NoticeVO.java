package com.notice.model;

import java.sql.Timestamp;

public class NoticeVO implements java.io.Serializable{
	
	private String notice_id;
	private String member_id;
	private Integer notice_category;
	private String content;
	private Timestamp notice_time;
	private Integer notice_status;

	

	
	public NoticeVO(String notice_id, String member_id, Integer notice_category, String content,
			Timestamp notice_time, Integer notice_status) {
		super();
		this.notice_id = notice_id;
		this.member_id = member_id;
		this.notice_category = notice_category;
		this.content = content;
		this.notice_time = notice_time;
		this.notice_status = notice_status;
	}
	public NoticeVO() {
		super();
	}
	
	

	
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getNotice_category() {
		return notice_category;
	}
	public void setNotice_category(Integer notice_category) {
		this.notice_category = notice_category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(Timestamp notice_time) {
		this.notice_time = notice_time;
	}
	public Integer getNotice_status() {
		return notice_status;
	}
	public void setNotice_status(Integer notice_status) {
		this.notice_status = notice_status;
	}

	

	
}
