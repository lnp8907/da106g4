package android.com.staff.model;

import java.io.Serializable;

public class Staff implements Serializable {
	private String staff_account;
	private String staff_password;
//	private String name;
//	private String email;
	private String staff_id;

	public Staff() {
		super();
	}

	public Staff(String staff_account, String staff_password, String name, String email, String staff_id) {
		super();
		this.staff_account = staff_account;
		this.staff_password = staff_password;
//		this.name = name;
//		this.email = email;
		this.staff_id = staff_id;
	}

	public String getStaff_account() {
		return staff_account;
	}

	public void setStaff_account(String staff_account) {
		this.staff_account = staff_account;
	}

	public String getStaff_password() {
		return staff_password;
	}

	public void setStaff_password(String staff_password) {
		this.staff_password = staff_password;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	@Override
	public String toString() {
		return "Staff [staff_account=" + staff_account + ", staff_password=" + staff_password + ", staff_id=" + staff_id
				+ "]";
	}

	

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
}
