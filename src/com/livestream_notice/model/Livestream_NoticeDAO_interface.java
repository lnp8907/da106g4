package com.livestream_notice.model;

import java.util.*;

public interface Livestream_NoticeDAO_interface {
          public void insert(Livestream_NoticeVO livestream_NoticeVO);
          public void update(Livestream_NoticeVO livestream_NoticeVO);
          public void delete(String lsnotice_id);
          public Livestream_NoticeVO findByPrimaryKey(String lsnotice_id);
          public List<Livestream_NoticeVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);
		
}
