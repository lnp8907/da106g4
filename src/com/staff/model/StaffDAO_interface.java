package com.staff.model;

import java.util.List;



public interface StaffDAO_interface {
		public void insert(StaffVO staffvo);
	    public void update(StaffVO staffvo);
	    public void updateWithPassword(StaffVO staffvo);
	    public StaffVO findByPrimaryKey(String staff_id);
	    public void changeStatus(String staff_id,Integer status);
	    public void resetPassword(String staff_id, String password);
	    public List<StaffVO> getAll();
}
