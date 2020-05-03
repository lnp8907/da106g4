package com.authority.model;

import java.sql.Connection;
import java.util.Set;



public interface AuthorityDAO_interface {
    public void insert(String staff_id,String[] power_no);
    public void delete(String staff_id, Connection con);
    public void delete(String staff_id);
    public Set<String> findPowerByEmpno(String staff_id);
}
