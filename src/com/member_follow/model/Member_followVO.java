package com.member_follow.model;

public class Member_followVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String member_id;
	private String followed;
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getFollowed() {
		return followed;
	}
	public void setFollowed(String followed) {
		this.followed = followed;
	}
	@Override
	public String toString() {
		return "Member_followVO [member_id=" + member_id + ", followed=" + followed + "]";
	}
	
	
	

}
