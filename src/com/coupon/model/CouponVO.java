package com.coupon.model;
import java.sql.Date;
import java.util.Arrays;

public class CouponVO implements java.io.Serializable{

	private String c_no;
	private String c_name;
	private byte[] c_picture;
	private Integer discount;
	private Date start_date;
	private Date end_date;
	private String coupon_code;
	
	public CouponVO() {
		super();
	}

	public String getC_no() {
		return c_no;
	}

	public void setC_no(String c_no) {
		this.c_no = c_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public byte[] getC_picture() {
		return c_picture;
	}

	public void setC_picture(byte[] c_picture) {
		this.c_picture = c_picture;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	@Override
	public String toString() {
		return "CouponVO [c_no=" + c_no + ", c_name=" + c_name + ", c_picture=" + Arrays.toString(c_picture)
				+ ", discount=" + discount + ", start_date=" + start_date + ", end_date=" + end_date + ", coupon_code="
				+ coupon_code + "]";
	}
	


	
	
}
