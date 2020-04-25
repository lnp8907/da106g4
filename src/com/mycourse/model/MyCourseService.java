package com.mycourse.model;

import java.util.List;
import java.util.Map;

public class MyCourseService {
	private MycourseDAO_interface dao;

	public MyCourseService() {
		dao = new MycourseDAO();
	}

	public void insert(String course_id, String member_id, Integer pay_price) {

		MycourseVO mycourseVO = new MycourseVO();
		mycourseVO.setCourse_id(course_id);
		mycourseVO.setMember_id(member_id);
		mycourseVO.setPay_price(pay_price);
		
		dao.insert(mycourseVO);
	}
	public void changeOneStatus(String course_id,String member_id, String course_status) {
		dao.changeOneStatus(course_id, member_id, course_status);
	}
	public void changeOneStatus(Map<String,String[]> changeStatus) {
		dao.changeOneStatus(changeStatus);
	}
	public void changeAllStatus(String course_id, Integer statusNum){
		dao.changeAllStatus(course_id, statusNum);
	}

	public boolean isApplied(String course_id, String member_id){
		return dao.isApplied(course_id, member_id);
	}

	public boolean isFull(Integer num_max, String course_id){
		return dao.isFull(num_max, course_id);
	}

	public Integer appliedNum(String course_id){
		return dao.appliedNum(course_id);
	}

	public MycourseVO findByPrimaryKey(String course_id, String member_id){
		return dao.findByPrimaryKey(course_id, member_id);
	}

	public List<MycourseVO> findJoinedMemberByPrimaryKey(String course_id){
		return dao.findJoinedMemberByPrimaryKey(course_id);
	}

	public List<MycourseVO> findJoingCourseByPrimaryKey(String member_id){
		return dao.findJoingCourseByPrimaryKey(member_id);
	}

	public List<MycourseVO> getAll(){
		return dao.getAll();
	}
    public Integer getOneCheckNum(String course_id) {
    	return dao.getOneCheckNum(course_id);
    }
    public Integer getAllCheckNum() {
    	return dao.getAllCheckNum();
    }
	
}
