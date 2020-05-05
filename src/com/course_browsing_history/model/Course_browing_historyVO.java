package com.course_browsing_history.model;

public class Course_browing_historyVO {
	 private String member_id;

    private String course_id;

	
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


	public Course_browing_historyVO(String member_id, String course_id) {
		super();
		this.member_id = member_id;
		this.course_id = course_id;
	}


	public Course_browing_historyVO() {
		super();
	}


	@Override
	public String toString() {
		return "Recipe_browing_historyVO [member_id=" + member_id + ", course_id=" + course_id + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
		result = prime * result + ((course_id == null) ? 0 : course_id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course_browing_historyVO other = (Course_browing_historyVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		if (course_id == null) {
			if (other.course_id != null)
				return false;
		} else if (!course_id.equals(other.course_id))
			return false;
		return true;
	}

	


}
