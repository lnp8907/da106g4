package com.course_notice.model;

import java.util.List;

public interface CourseNoticeDAO_interface {
    public void insert(CourseNoticeVO courseNoticeVO);
    public void update(CourseNoticeVO courseNoticeVO);
    public void delete(String csnotice_no);
    public CourseNoticeVO findByPrimaryKey(String csnotice_no);
    public List<CourseNoticeVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 
}
