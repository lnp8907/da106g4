package android.com.livestream.model;

import java.sql.Connection;
import java.util.*;

import android.com.livestream.model.Livestream;

public interface LivestreamDAO {
          boolean insert(Livestream livestream);
//          public void update(Livestream livestreamVO);
//          public void updatestatus(Livestream livestreamVO);
//          public void delete(String livestream_id);
          public Livestream findByPrimaryKey(String livestream_id);
          public Livestream findByStatus(String member_id);
          public Livestream findTitleById(String livestream_id);
          public Livestream findCidByLsid(String livestream_id);
          public List<Livestream> findByCategory(int status);
          byte[] findPicByStatus(String status);
//          public List<Livestream> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
        //public Set<LivestreamVO> getLSByLivestreamId (String livestream_id);
//        public Set<EmpVO> getEmpsByDeptno(Integer deptno);
}
