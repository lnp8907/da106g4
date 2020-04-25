package com.livestream_collection.model;

import java.util.*;

public interface Livestream_CollectionDAO_interface {
          public void insert(Livestream_CollectionVO livestream_CollectionVO);
          public void update(Livestream_CollectionVO livestream_CollectionVO);
          public void delete(String livestream_id);
          public Livestream_CollectionVO findByPrimaryKey(String livestream_id);
          public List<Livestream_CollectionVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);
		
}
