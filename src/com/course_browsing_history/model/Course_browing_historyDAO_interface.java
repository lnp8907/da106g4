package com.course_browsing_history.model;

import java.util.Set;


public interface Course_browing_historyDAO_interface {
	//新增
    public void insert(Course_browing_historyVO VO) ;

	//刪除
    public void delete(Course_browing_historyVO VO);
    public void deleteAll(String member_id );

    //比對全部
    public Set<Course_browing_historyVO> getmemberList(String member_id);

}
