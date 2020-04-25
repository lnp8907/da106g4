package com.livestream.model;

import java.util.*;

public interface LivestreamDAO_interface {
          public void insert(LivestreamVO livestreamVO);
          public void update(LivestreamVO livestreamVO);
          public void updatestatus(LivestreamVO livestreamVO);
          public void delete(String livestream_id);
          public LivestreamVO findByPrimaryKey(String livestream_id);
          public List<LivestreamVO> getAll();
          public List<LivestreamVO> getFourForHomePage();
          public LivestreamVO getMostPopLS();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
        //public Set<LivestreamVO> getLSByLivestreamId (String livestream_id);
//        public Set<EmpVO> getEmpsByDeptno(Integer deptno);
		
}
