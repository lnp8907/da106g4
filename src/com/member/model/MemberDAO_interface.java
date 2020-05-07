
package com.member.model;

import java.util.*;

import com.course.model.CourseVO;

public interface MemberDAO_interface {
          public void insert(MemberVO empVO);
          public void update(MemberVO empVO);
          public void delete(String empno); 
          public MemberVO findByPrimaryKey(String empno);
          public List<MemberVO> getAll();
          public void update_To_Chef(MemberVO empVO);
          public void updateStoredValue(MemberVO empVO);
        
          public void update_by_self(MemberVO empVO);
          public void update_Success(MemberVO empVO);
          public MemberVO findPK(String member_id);
          public void updateCardNumber(MemberVO empVO);
          public void updateback_end(MemberVO empVO);
          public List<MemberVO> getChiefapplyStatus();
          public void UpdateChiefapplyStatus(MemberVO empVO);
          public void UpdateValidation(MemberVO empVO);
          public List<MemberVO> getValidation();
      	  public List<MemberVO> getliving() ;
      	  public void changeOnline(String member_id,Integer num);
          public void changeOffline(String member_id,Integer num);

          
          
          
          
          
          
          
//          public List<MemberVO> Duplicate_Account(String account);
          
}
