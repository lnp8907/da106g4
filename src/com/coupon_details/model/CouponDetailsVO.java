package com.coupon_details.model;

public class CouponDetailsVO implements java.io.Serializable{
	private String c_no;
	private	String product_id;

	public CouponDetailsVO() {
		super();
	}

	public String getC_no() {
		return c_no;
	}

	public void setC_no(String c_no) {
		this.c_no = c_no;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "CouponDetailsVO [c_no=" + c_no + ", product_id=" + product_id + "]";
	}

}
