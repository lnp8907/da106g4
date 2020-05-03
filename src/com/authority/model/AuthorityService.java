package com.authority.model;

import java.util.Set;

public class AuthorityService {
	private AuthorityDAO_interface dao;
	
	public AuthorityService() {
		dao = new AuthorityDAO();
	}
    public void insert(String staff_id,String[] power_no) {
    dao.insert(staff_id, power_no);	
    }
    public void delete(String staff_id) {
    	dao.delete(staff_id);
    }
    public Set<String> findPowerByEmpno(String staff_id){
    	return dao.findPowerByEmpno(staff_id);
    }
	
}
