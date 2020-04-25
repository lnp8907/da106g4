package com.mycourse.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MycourseVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private	String course_id;
	private	String member_id ;
	private	Integer	app_status;
	private	Timestamp create_time;
	private	Integer	pay_price;
	
	@Override
	public String toString() {
		return "MycourseVO [course_id=" + course_id + ", member_id=" + member_id + ", app_status=" + app_status
				+ ", create_time=" + create_time + ", pay_price=" + pay_price + "]";
	}
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getApp_status() {
		return app_status;
	}
	public void setApp_status(Integer app_status) {
		this.app_status = app_status;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Integer getPay_price() {
		return pay_price;
	}
	public void setPay_price(Integer pay_price) {
		this.pay_price = pay_price;
	}
}
