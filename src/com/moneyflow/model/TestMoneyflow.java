package com.moneyflow.model;

import java.util.Random;

public class TestMoneyflow {
	public static void main(String[] args) {
	
		for(int i=0;i<5;i++) {
			int ii=i+1;
			MoneyflowJDBCDAO dao=new MoneyflowJDBCDAO();
			MoneyflowVO vo=new MoneyflowVO();
			Random r = new Random();
			int i1 = r.nextInt(9999)+100 ;
			int i2 = r.nextInt(2)+1;
			i2--;
			vo.setMember_id("81000"+ii);
			vo.setMoneyflow_status(i2);
			vo.setMoney(i1);
			dao.insert(vo);
		}


		
	}

}
