package com.notice.model;

import java.util.List;
public interface NoticeDAO_interface {
	  public void insert(NoticeVO noticevo);
	    public void update(NoticeVO noticevo);
	    public void delete(String noticevo);
	    public NoticeVO findByPrimaryKey(String noticevo);
	    public List<NoticeVO> getAll();

}
