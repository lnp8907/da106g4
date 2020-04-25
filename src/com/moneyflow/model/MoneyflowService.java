package com.moneyflow.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.moneyflow.model.MoneyflowDAO_interface;
import com.moneyflow.model.MoneyflowJDBCDAO;
import com.moneyflow.model.MoneyflowVO;

public class MoneyflowService {
	private MoneyflowDAO_interface dao;

	public MoneyflowService() {
	
		dao = new MoneyflowJDBCDAO();
	}

	
	public MoneyflowVO insert(
			String member_id, Integer moneyflow_status, Integer money) {
		MoneyflowVO moneyflowVO=new MoneyflowVO();
		//moneyflowVO.setMember_id(member_id);
		moneyflowVO.setMember_id(member_id);
		moneyflowVO.setMoneyflow_status(moneyflow_status);
		
		moneyflowVO.setMoney(money);
		
		dao.insert(moneyflowVO);
		return moneyflowVO;
		
		
		
		
	}
//	public MoneyflowVO update(String account,
//			String password, String member_name, Integer gender , Date birthday, String cellphone, String email, String nickname, byte[] member_photo,
//			Integer validation, byte[] license, Integer member_status, String member_address, String member_creditcard, Integer balance,
//			Integer chiefapply_status) {
//		MoneyflowVO moneyflowVO=new MoneyflowVO();
//		moneyflowVO.setAccount(account);
//		moneyflowVO.setPassword(password);
//		moneyflowVO.setMember_name(member_name);
//		moneyflowVO.setGender(gender);
//		moneyflowVO.setBirthday(birthday);
//		moneyflowVO.setCellphone(cellphone);
//		moneyflowVO.setEmail(email);
//		moneyflowVO.setNickname(nickname);
//		moneyflowVO.setMember_photo(member_photo);
//		moneyflowVO.setValidation(validation);
//		moneyflowVO.setLicense(license);
//		moneyflowVO.setMember_status(member_status);
//		moneyflowVO.setMember_address(member_address);
//		moneyflowVO.setMember_creditcard(member_creditcard);
//		moneyflowVO.setBalance(balance);
//		moneyflowVO.setChiefapply_status(chiefapply_status);
//					
//		
//		return moneyflowVO;
//	}
	
	
//	public MoneyflowVO update(String moneyflow_no, String member_id, Integer moneyflow_status , Integer money, Timestamp charge_time) {
//		MoneyflowVO moneyflowVO=new MoneyflowVO();
//		moneyflowVO.setMember_id(member_id);
//		moneyflowVO.setAccount(account);
//		moneyflowVO.setPassword(password);
//		moneyflowVO.setMember_name(member_name);
//		moneyflowVO.setGender(gender);
//		
//		dao.update(moneyflowVO);			
//		
//		return moneyflowVO;
//	}
//	
//	
//
//	public void delete(String member_id) {
//		dao.delete(member_id);
//	}
//	
	public List<MoneyflowVO> getAll() {
		return dao.getAll();
	}
//
	public MoneyflowVO getOneMoneyflow(String moneyflow_no) {
		return dao.findByPrimaryKey(moneyflow_no);
	}
	
	
	
}
