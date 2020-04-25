package com.power.model;

import java.util.List;

import com.authority.model.AuthorityVO;

public interface PowerDAO_interface {
	    public void insert(PowerVO authorityVO);
	    public void update(PowerVO authorityVO);
	    public void delete(String empno);
	    public PowerVO findByPrimaryKey(String power_no);
	    public List<PowerVO> getall();
}
