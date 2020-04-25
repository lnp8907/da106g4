package com.course.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class CourseVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String course_id;
	private String member_id;
	private String course_type;
	private String course_name;
	private Integer course_status;
	private Integer app_num;
	private Integer num_max;
	private Timestamp create_time;
	private Timestamp course_start;
	private Integer course_price;
	private String course_detail;
	private Date end_app;
	private byte[] course_photo;
	private String course_loca;

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

	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public Integer getCourse_status() {
		return course_status;
	}

	public void setCourse_status(Integer course_status) {
		this.course_status = course_status;
	}

	public Integer getApp_num() {
		return app_num;
	}

	public void setApp_num(Integer app_num) {
		this.app_num = app_num;
	}

	public Integer getNum_max() {
		return num_max;
	}

	public void setNum_max(Integer num_max) {
		this.num_max = num_max;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getCourse_start() {
		return course_start;
	}

	public void setCourse_start(Timestamp course_start) {
		this.course_start = course_start;
	}


	public Integer getCourse_price() {
		return course_price;
	}

	public void setCourse_price(Integer course_price) {
		this.course_price = course_price;
	}

	public String getCourse_detail() {
		return course_detail;
	}

	public void setCourse_detail(String course_detail) {
		this.course_detail = course_detail;
	}

	public Date getEnd_app() {
		return end_app;
	}

	public void setEnd_app(Date end_app) {
		this.end_app = end_app;
	}

	public byte[] getCourse_photo() {
		return course_photo;
	}

	public void setCourse_photo(byte[] course_photo) {
		this.course_photo = course_photo;
	}

	public String getCourse_loca() {
		return course_loca;
	}

	public void setCourse_loca(String course_loca) {
		this.course_loca = course_loca;
	}

	@Override
	public String toString() {
		return "CourseVO [course_id=" + course_id + ", member_id=" + member_id + ", course_type=" + course_type
				+ ", course_name=" + course_name + ", course_status=" + course_status + ", app_num=" + app_num
				+ ", num_max=" + num_max + ", create_time=" + create_time + ", course_start=" + course_start
				+ ", course_end=" + ", course_price=" + course_price + ", course_detail=" + course_detail
				+ ", end_app=" + end_app + ", course_photo=" + Arrays.toString(course_photo) + ", course_loca="
				+ course_loca + "]";
	}
}
