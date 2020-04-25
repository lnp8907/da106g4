package com.moneyflow.model;

import java.util.List;
public interface MoneyflowDAO_interface {
	  public void insert(MoneyflowVO moneyfloevo);
	    public void update(MoneyflowVO moneyfloevo);
	    public void delete(String moneyflow_no);
	    public MoneyflowVO findByPrimaryKey(String moneyflow_no);
	    public List<MoneyflowVO> getAll();

}
