package com.course_browsing_history.model;


public class Course_browing_historyService {
	private Course_browing_historyDAO_interface dao;

	public Course_browing_historyService() {
		
		dao=new Course_browing_historyDAO();
	}

	public Course_browing_historyVO insert(String member_id, String course_id) {
		System.out.println("啟動服務準備新增");

		Course_browing_historyVO VO = new Course_browing_historyVO();
		VO.setMember_id(member_id);
		VO.setCourse_id(course_id);
		
		dao.insert(VO);
		return VO;
	}
	public void removelist(String member_id,String course_id) {
		System.out.println("用於移除");
		Course_browing_historyVO VO = new Course_browing_historyVO();
		VO.setMember_id(member_id);
		VO.setCourse_id(course_id);
		dao.delete(VO);
	}
	
	public void deletelist(String member_id) {
		System.out.println("用於刪除紀錄");
	
		dao.deleteAll(member_id);
	}
		
	

}
