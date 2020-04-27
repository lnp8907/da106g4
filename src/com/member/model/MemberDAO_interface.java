
package com.member.model;

import java.util.*;

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
//          public List<MemberVO> Duplicate_Account(String account);
}
