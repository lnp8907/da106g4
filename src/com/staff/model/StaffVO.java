package com.staff.model;

public class StaffVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String staff_id;
	private String staff_password;
	private String staff_name;
	private Integer gender;
	private String phone;
	private Integer staff_status;
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StaffVO(String staff_id, String staff_account,String email, String staff_password, String staff_name, Integer gender,
			String phone, Integer staff_status) {
		super();
		this.staff_id = staff_id;
		this.email = email;
		this.staff_password = staff_password;
		this.staff_name = staff_name;
		this.gender = gender;
		this.phone = phone;
		this.staff_status = staff_status;
	}
	public StaffVO() {
		super();
	}
	@Override
	public String toString() {
		return "StaffVO [staff_id=" + staff_id + ", staff_account=" + email + ", staff_password="
				+ staff_password + ", staff_name=" + staff_name + ", gender=" + gender + ", phone=" + phone
				+ ", staff_status=" + staff_status + "]";
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_password() {
		return staff_password;
	}
	public void setStaff_password(String staff_password) {
		this.staff_password = staff_password;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStaff_status() {
		return staff_status;
	}
	public void setStaff_status(Integer staff_status) {
		this.staff_status = staff_status;
	}

}
