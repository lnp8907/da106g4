package com.staff.model;

import java.util.List;

public class StaffService {
	private StaffDAO_interface dao;

	public StaffService() {
		dao = new StaffDAO();
	}

	public StaffVO insert(String password, String name, Integer gender, String phone, Integer status, String email) {
		StaffVO staffVO = new StaffVO();
		staffVO.setStaff_name(name);
		staffVO.setGender(gender);
		staffVO.setStaff_password(password);
		staffVO.setPhone(phone);
		staffVO.setStaff_status(status);
		staffVO.setEmail(email);
		dao.insert(staffVO);
		return staffVO;
	};

	public StaffVO updateWithPassword(String staff_id, String staff_password, String name, Integer gender, String phone, String email, Integer staff_status){
		StaffVO staffVO = new StaffVO();
		staffVO.setStaff_id(staff_id);
		staffVO.setStaff_name(name);
		staffVO.setGender(gender);
		staffVO.setStaff_password(staff_password);
		staffVO.setPhone(phone);
		staffVO.setEmail(email);
		staffVO.setStaff_status(staff_status);
		dao.updateWithPassword(staffVO);
		return staffVO;
	};
	
	public StaffVO update(String staff_id, Integer staff_status, String name, Integer gender, String phone, String email) {
		StaffVO staffVO = new StaffVO();
		staffVO.setStaff_id(staff_id);
		staffVO.setStaff_name(name);
		staffVO.setGender(gender);
		staffVO.setStaff_status(staff_status);
		staffVO.setPhone(phone);
		staffVO.setEmail(email);
		dao.update(staffVO);
		return staffVO;
	};

	public StaffVO findByPrimaryKey(String staff_id) {
		return dao.findByPrimaryKey(staff_id);
	};

	public void changeStatus(String staff_id, Integer status) {
		dao.changeStatus(staff_id, status);
	};

	public void resetPassword(String staff_id, String password) {
		dao.resetPassword(staff_id, password);
	};

	public List<StaffVO> getAll(){
    	
    	return dao.getAll();
    }
}
