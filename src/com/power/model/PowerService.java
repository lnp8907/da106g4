package com.power.model;

import java.util.List;

public class PowerService {
private PowerDAO_interface dao;

public PowerService() {
	dao = new PowerDAO();
}
	public List<PowerVO> getAll() {
		return dao.getall();
	}
	
}
