package com.member_follow.model;

import java.util.List;


public interface Member_followDAO_interface {
    public void insert(Member_followVO empVO);
    public void update(Member_followVO empVO);
    public void delete(String empno);
    public Member_followVO findByPrimaryKey(String member_id);
    public Member_followVO findByPrimaryKey_1(String member_id);
    public List<Member_followVO> getAllMemberByFollowed(String followed);
    public List<Member_followVO> getall();
}
