package com.course.model;

import java.util.List;



public interface CourseDAO_interface {
    public void insert(CourseVO courseVO);
    public void update(CourseVO courseVO);
    public void delete(String course_id);
    public CourseVO findByPrimaryKey(String course_id);
    public List<CourseVO> getAll();
	public List<CourseVO> getTopSix();
	public List<CourseVO> getForHomePage();
	public List<CourseVO> getForManage();
	public List<CourseVO> getForFront();
	public List<CourseVO> getChefCourse(String member_id);
	public List<CourseVO> getForMycourseList();
    public Integer getManageNum();
    public void updateStatus(String course_id,Integer statusCode);
    public boolean isCourseOver(String course_id);
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 

}
