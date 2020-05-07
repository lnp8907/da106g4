package android.com.member.model;

import java.io.Serializable;

public class Member implements java.io.Serializable{
	
	 private String member_id;
    private String account;
	 public byte[] getMember_photo() {
		return member_photo;
	}
	public void setMember_photo(byte[] member_photo) {
		this.member_photo = member_photo;
	}
	private String password;
//   	private Date birthday;
	 private String member_name;
//	 private Integer gender;
	 private String cellphone;
	 private String email;
	 private String nickname;
//	  private byte[] member_photo;
//	 private Integer validation;
//	  private byte[] license;
//	 private Integer member_status;
	 private String member_address;
	 private String member_creditcard;
	 private Integer balance;
	 private byte[] member_photo;
//	 private Integer chiefapply_status;
	 
	 
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public Date getBirthday() {
//		return birthday;
//	}
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
//	public Integer getGender() {
//		return gender;
//	}
//	public void setGender(Integer gender) {
//		this.gender = gender;
//	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
//	public byte[] getMember_photo() {
//		return member_photo;
//	}
//	public void setMember_photo(byte[] member_photo) {
//		this.member_photo = member_photo;
//	}
//	public Integer getValidation() {
//		return validation;
//	}
//	public void setValidation(Integer validation) {
//		this.validation = validation;
//	}
//	public byte[] getLicense() {
//		return license;
//	}
//	public void setLicense(byte[] license) {
//		this.license = license;
//	}
//	public Integer getMember_status() {
//		return member_status;
//	}
//	public void setMember_status(Integer member_status) {
//		this.member_status = member_status;
//	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_creditcard() {
		return member_creditcard;
	}
	public void setMember_creditcard(String member_creditcard) {
		this.member_creditcard = member_creditcard;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
//	public Integer getChiefapply_status() {
//		return chiefapply_status;
//	}
//	public void setChiefapply_status(Integer chiefapply_status) {
//		this.chiefapply_status = chiefapply_status;
//	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", account=" + account + ", password=" + password + ", member_name="
				+ member_name + ", cellphone=" + cellphone + ", email=" + email + ", member_address=" + member_address
				+ ", member_creditcard=" + member_creditcard + ", balance=" + balance + "]";
	}
	
}