package com.course_notice.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseNoticeVO implements Serializable{
	private	String csnotice_no;
	private	String member_id;
	private	String course_id;
	private	String notice;
	private	Timestamp notice_time;
	private	Integer notice_status;
	
	public String getCsnotice_no() {
		return csnotice_no;
	}
	public void setCsnotice_no(String csnotice_no) {
		this.csnotice_no = csnotice_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
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
	@Override
	public String toString() {
		return "CourseNoticeVO [csnotice_no=" + csnotice_no + ", member_id=" + member_id + ", course_id=" + course_id
				+ ", notice=" + notice + ", notice_time=" + notice_time + ", notice_status=" + notice_status + "]";
	}

}
