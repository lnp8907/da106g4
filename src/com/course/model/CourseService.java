package com.course.model;

import java.util.List;

public class CourseService {
	private CourseDAO_interface dao;
	
	public CourseService() {
		dao = new CourseDAO();
	}
	public CourseVO addCourse(String member_id, String course_type, String course_name,
			Integer num_max, java.sql.Timestamp course_start,Integer course_price,
			String course_detail,java.sql.Date end_app,byte[] course_photo,String course_loca) {

		CourseVO courseVO = new CourseVO();

		courseVO.setMember_id(member_id);
		courseVO.setCourse_type(course_type);
		courseVO.setCourse_name(course_name);
		courseVO.setNum_max(num_max);
		courseVO.setCourse_start(course_start);
		courseVO.setCourse_price(course_price);
		courseVO.setCourse_detail(course_detail);
		courseVO.setEnd_app(end_app);
		courseVO.setCourse_photo(course_photo);
		courseVO.setCourse_loca(course_loca);
		
		dao.insert(courseVO);

		return courseVO;
	}

	public CourseVO updateCourse(String course_id,String member_id, String course_type, String course_name,
			Integer num_max, java.sql.Timestamp course_start,Integer course_price,
			String course_detail,java.sql.Date end_app,byte[] course_photo,String course_loca) {
		
		CourseVO courseVO = new CourseVO();
		
		courseVO.setCourse_id(course_id);
		courseVO.setMember_id(member_id);
		courseVO.setCourse_type(course_type);
		courseVO.setCourse_name(course_name);
		courseVO.setNum_max(num_max);
		courseVO.setCourse_start(course_start);
		courseVO.setCourse_price(course_price);
		courseVO.setCourse_detail(course_detail);
		courseVO.setEnd_app(end_app);
		courseVO.setCourse_photo(course_photo);
		courseVO.setCourse_loca(course_loca);
		dao.update(courseVO);

		return courseVO;
	}

	public boolean isCourseOver(String course_id) {
		return dao.isCourseOver(course_id);
	}
	public void deleteCourse(String course_id) {
		dao.delete(course_id);
	}

	public List<CourseVO> getChefCourse(String member_id){
		return dao.getChefCourse(member_id);
	}
	public List<CourseVO> getForMycourseList(){
		return dao.getForMycourseList();
	}
	public List<CourseVO> getForHomePage(){
		return dao.getForHomePage();
	}
	
	public CourseVO getOneCourse(String course_id) {
		return dao.findByPrimaryKey(course_id);
	}
	
	public List<CourseVO> getAll() {
		return dao.getAll();
	}
	public List<CourseVO> getTopSix(){
		return dao.getTopSix();
	}
	
	 public Integer getManageNum() {
		 return dao.getManageNum();
	 }
	 
	 public List<CourseVO> getForFront(){
		 return dao.getForFront();
	 }
	
	 public List<CourseVO> getForManage(){
		 return dao.getForManage();
	 }
	 public void updateStatus(String course_id,Integer statusCode) {
		 dao.updateStatus(course_id, statusCode);
	 }
	
}

