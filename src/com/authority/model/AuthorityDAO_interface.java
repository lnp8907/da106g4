package com.authority.model;

import java.util.List;



public interface AuthorityDAO_interface {
    public void insert(AuthorityVO authorityVO);
    public void update(AuthorityVO authorityVO);
    public void delete(String empno);
    public AuthorityVO findByPrimaryKey(String power_no);
    public AuthorityVO findByPrimaryKey_1(String power_name);
    public List<AuthorityVO> getall();
}
