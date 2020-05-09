package com.notice.model;

import java.util.List;
public interface NoticeDAO_interface {
	  public void insert(NoticeVO noticevo);
	    public void update(NoticeVO noticevo);
	    public void delete(String noticevo);
	    public NoticeVO findByPrimaryKey(String noticevo);
	    public List<NoticeVO> getAll();
	    
	    public List<NoticeVO> getAllByMb_id_2(String member_id);
	    public Integer countNotReads(String mb_id_2);
	    public void changeStatus(String notice_id, Integer notice_status);

}
