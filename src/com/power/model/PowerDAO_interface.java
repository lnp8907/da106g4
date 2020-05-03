package com.power.model;

import java.util.List;

import com.power.model.PowerVO;

public interface PowerDAO_interface {
	    public void insert(PowerVO powerVO);
	    public void update(PowerVO powerVO);
	    public void delete(String power_no);
	    public PowerVO findByPrimaryKey(String power_no);
	    public List<PowerVO> getall();
}
