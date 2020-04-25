package com.donation_record.model;

import java.util.*;

public interface DonationRecordDAO_interface {
          public void insert(Donation_RecordVO donation_RecordVO);
          public void update(Donation_RecordVO donation_RecordVO);
          public void delete(String donation_id);
          public Donation_RecordVO findByPrimaryKey(String livestream_id);
          public List<Donation_RecordVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);
		
}
