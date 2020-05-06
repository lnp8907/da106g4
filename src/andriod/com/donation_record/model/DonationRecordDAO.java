package andriod.com.donation_record.model;

import java.util.*;

public interface DonationRecordDAO {
          public boolean insert(Donation_record dr);
          public void update(Donation_record donation_RecordVO);
          public void delete(String donation_id);
          public Donation_record findByPrimaryKey(String livestream_id);
          public List<Donation_record> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);
		
}
