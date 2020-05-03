package com.instant_delivery_order.model;
import java.sql.Timestamp;

public class InstantDeliveryOrderVO implements java.io.Serializable{
//
	private String ido_no;
	private String member_id;
	private String staff_id;
	private Timestamp o_time;
	private Integer p_method;
	private Integer p_status;
	private Integer total;
	private String d_address;
	private String qrcode;
	private Integer o_status;
	private Timestamp oc_time;
	
	public String getIdo_no() {
		return ido_no;
	}
	public void setIdo_no(String ido_no) {
		this.ido_no = ido_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public Timestamp getO_time() {
		return o_time;
	}
	public void setO_time(Timestamp o_time) {
		this.o_time = o_time;
	}
	public Integer getP_method() {
		return p_method;
	}
	public void setP_method(Integer p_method) {
		this.p_method = p_method;
	}
	public Integer getP_status() {
		return p_status;
	}
	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getD_address() {
		return d_address;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public Integer getO_status() {
		return o_status;
	}
	public void setO_status(Integer o_status) {
		this.o_status = o_status;
	}
	public Timestamp getOc_time() {
		return oc_time;
	}
	public void setOc_time(Timestamp oc_time) {
		this.oc_time = oc_time;
	}
	
	@Override
	public String toString() {
		return "InstantDeliveryOrderVO [ido_no=" + ido_no + ", member_id=" + member_id + ", staff_id=" + staff_id
				+ ", o_time=" + o_time + ", p_method=" + p_method + ", p_status=" + p_status + ", total=" + total
				+ ", d_address=" + d_address + ", qrcode=" + qrcode + ", o_status=" + o_status + ", oc_time=" + oc_time
				+ "]";
	}
	

	
	
}
