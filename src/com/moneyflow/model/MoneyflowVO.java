package com.moneyflow.model;

import java.sql.Timestamp;

public class MoneyflowVO implements java.io.Serializable{
	
	private String moneyflow_no;
	private String member_id;
	private Integer moneyflow_status;
	private Integer money;
	private Timestamp charge_time;
	public MoneyflowVO(String moneyflow_no, String member_id, Integer moneyflow_status, Integer money,
			Timestamp charge_time) {
		super();
		this.moneyflow_no = moneyflow_no;
		this.member_id = member_id;
		this.moneyflow_status = moneyflow_status;
		this.money = money;
		this.charge_time = charge_time;
	}
	public MoneyflowVO() {
		super();
	}
	public String getMoneyflow_no() {
		return moneyflow_no;
	}
	public void setMoneyflow_no(String moneyflow_no) {
		this.moneyflow_no = moneyflow_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getMoneyflow_status() {
		return moneyflow_status;
	}
	public void setMoneyflow_status(Integer moneyflow_status) {
		this.moneyflow_status = moneyflow_status;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Timestamp getCharge_time() {
		return charge_time;
	}
	public void setCharge_time(Timestamp charge_time) {
		this.charge_time = charge_time;
	}

}
