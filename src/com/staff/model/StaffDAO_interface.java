package com.staff.model;

import java.util.List;

import com.member.model.MemberVO;



public interface StaffDAO_interface {
		public String insert(StaffVO staffvo);
	    public void update(StaffVO staffvo);
	    public void updateWithPassword(StaffVO staffvo);
	    public StaffVO findByPrimaryKey(String staff_id);
	    public void changeStatus(String staff_id,Integer status);
	    public void resetPassword(String staff_id, String password);
	    public List<StaffVO> getAll();
	    public StaffVO findPK(String staff_id);
}
