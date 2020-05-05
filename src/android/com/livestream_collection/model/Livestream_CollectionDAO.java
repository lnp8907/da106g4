package android.com.livestream_collection.model;

import java.sql.Connection;
import java.util.*;

public interface Livestream_CollectionDAO {
          public void insert(Livestream_Collection livestream_CollectionVO);
          public void update(Livestream_Collection livestream_CollectionVO);
          public void delete(String livestream_id);
          public List<Livestream_Collection> findByPrimaryKey(String member_id);
          public List<Livestream_Collection> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);
		
}
