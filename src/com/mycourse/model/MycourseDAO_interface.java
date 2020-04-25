package com.mycourse.model;

import java.util.List;
import java.util.Map;

public interface MycourseDAO_interface {
    public void insert(MycourseVO mycourseVO);
    public void update(MycourseVO mycourseVO);
    public void delete(String course_id, String member_id);
    public void changeOneStatus(Map<String,String[]> changeStatus);
    public void changeOneStatus(String course_id,String member_id, String course_status);
    public void changeAllStatus(String course_id, Integer statusNum);
    public boolean isApplied(String course_id, String member_id);
    public boolean isFull(Integer num_max,String course_id);
    public Integer appliedNum(String course_id);
    public Integer getOneCheckNum(String course_id);
    public Integer getAllCheckNum();
    public MycourseVO findByPrimaryKey(String course_id, String member_id);
    public List<MycourseVO> findJoinedMemberByPrimaryKey(String course_id);
    public List<MycourseVO> findJoingCourseByPrimaryKey(String member_id);
    public List<MycourseVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 
}

