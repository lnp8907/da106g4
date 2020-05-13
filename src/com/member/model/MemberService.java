package com.member.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.course.model.CourseVO;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {

		dao = new MemberDAO();
	}

//		public List<MemberVO> Duplicate_Account() {
//			return dao.Duplicate_Account();
//		}
	
	
	public List<MemberVO> getliving() {
		return dao.getliving();
	}

	public List<MemberVO> getValidation() {
		return dao.getValidation();
	}

	public List<MemberVO> getChiefapplyStatus() {
		return dao.getChiefapplyStatus();
	}

	public MemberVO updateback_end(String member_id, String account, String member_name, String email,
			Integer validation) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		memberVO.setAccount(account);

		memberVO.setMember_name(member_name);

		memberVO.setEmail(email);

		memberVO.setValidation(validation);

		dao.updateback_end(memberVO);
		return memberVO;

	}

	public MemberVO UpdateValidation(String member_id, Integer validation) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		memberVO.setValidation(validation);

		dao.UpdateValidation(memberVO);
		return memberVO;

	}

	public MemberVO UpdateChiefapplyStatus(String member_id, Integer chiefapply_status, Integer member_status) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);

		memberVO.setChiefapply_status(chiefapply_status);
		memberVO.setMember_status(member_status);

		dao.UpdateChiefapplyStatus(memberVO);
		return memberVO;

	}

	public MemberVO updateSuccess(String account, Integer validation) {
		MemberVO memberVO = new MemberVO();
		// memberVO.setMember_id(member_id);
		memberVO.setEmail(account);
		memberVO.setValidation(validation);

		dao.update_Success(memberVO);
		return memberVO;

	}

	public MemberVO insertmem(String account, String password, String email) {
		MemberVO memberVO = new MemberVO();
		// memberVO.setMember_id(member_id);
		memberVO.setAccount(account);
		memberVO.setPassword(password);

		memberVO.setEmail(email);

		dao.insert(memberVO);
		return memberVO;

	}

	public MemberVO updateCardNumber(String member_id, String member_creditcard) {
		MemberVO memberVO = new MemberVO();
		// memberVO.setMember_id(member_id);
		memberVO.setMember_id(member_id);
		memberVO.setMember_creditcard(member_creditcard);

		dao.updateCardNumber(memberVO);
		return memberVO;

	}

	public MemberVO update_by_self(String member_id, String member_name,String nickname, String account, String password, String email,
			Date birthday, String cellphone, Integer gender, String member_address, byte[] member_photo) {
		MemberVO memberVO = new MemberVO();

		memberVO.setMember_id(member_id);
		memberVO.setAccount(account);
		memberVO.setNickname(nickname);
		memberVO.setPassword(password);
		memberVO.setMember_name(member_name);
		memberVO.setGender(gender);
		memberVO.setBirthday(birthday);
		memberVO.setCellphone(cellphone);
		memberVO.setEmail(email);
		memberVO.setMember_address(member_address);
		memberVO.setMember_photo(member_photo);

		dao.update_by_self(memberVO);

		return memberVO;
	}

//	public MemberVO update_To_Chef(String member_id, String account
//			, String member_name, byte[] license , Integer chiefapply_status) {
//		MemberVO memberVO=new MemberVO();
//		memberVO.setMember_id(member_id);
//		memberVO.setAccount(account);	
//		memberVO.setMember_name(member_name);
//		memberVO.setLicense(license);
//		memberVO.setChiefapply_status(chiefapply_status);
//		dao.update_To_Chef(memberVO);			
//		
//		return memberVO;
//	}

	public MemberVO updateStoredValue(String member_id, Integer balance) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		memberVO.setBalance(balance);
		dao.updateStoredValue(memberVO);

		return memberVO;
	}
	
	  public void changeOnline(String member_id,Integer num) {
		  
		  dao.changeOnline(member_id, num);  
		  
	  };
      public void changeOffline(String member_id,Integer num) {
    	  
    	  dao.changeOffline(member_id, num);
    	  
      };
	
	

//		public MemberVO update(String account,
//				String password, String member_name, Integer gender , Date birthday, String cellphone, String email, String nickname, byte[] member_photo,
//				Integer validation, byte[] license, Integer member_status, String member_address, String member_creditcard, Integer balance,
//				Integer chiefapply_status) {
//			MemberVO memberVO=new MemberVO();
//			memberVO.setAccount(account);
//			memberVO.setPassword(password);
//			memberVO.setMember_name(member_name);
//			memberVO.setGender(gender);
//			memberVO.setBirthday(birthday);
//			memberVO.setCellphone(cellphone);
//			memberVO.setEmail(email);
//			memberVO.setNickname(nickname);
//			memberVO.setMember_photo(member_photo);
//			memberVO.setValidation(validation);
//			memberVO.setLicense(license);
//			memberVO.setMember_status(member_status);
//			memberVO.setMember_address(member_address);
//			memberVO.setMember_creditcard(member_creditcard);
//			memberVO.setBalance(balance);
//			memberVO.setChiefapply_status(chiefapply_status);
//						
//			
//			return memberVO;
//		}

	public MemberVO update(String member_id, String account, String password, String member_name, Integer gender,
			Date birthday, String cellphone, String email, String nickname, Integer validation, Integer member_status,
			String member_address, String member_creditcard, Integer balance, Integer chiefapply_status,
			byte[] member_photo, byte[] license) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setMember_name(member_name);
		memberVO.setGender(gender);
		memberVO.setBirthday(birthday);
		memberVO.setCellphone(cellphone);
		memberVO.setEmail(email);
		memberVO.setNickname(nickname);

		memberVO.setValidation(validation);

		memberVO.setMember_status(member_status);
		memberVO.setMember_address(member_address);
		memberVO.setMember_creditcard(member_creditcard);
		memberVO.setBalance(balance);
		memberVO.setChiefapply_status(chiefapply_status);
		memberVO.setMember_photo(member_photo);
		memberVO.setLicense(license);
		dao.update(memberVO);

		return memberVO;
	}

	public MemberVO update_To_Chef(String member_id, byte[] license,
			Integer chiefapply_status) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
	
	
		memberVO.setLicense(license);
		memberVO.setChiefapply_status(chiefapply_status);
		dao.update_To_Chef(memberVO);

		return memberVO;
	}

	public void delete(String member_id) {
		dao.delete(member_id);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO getOneMember(String member_id) {
		return dao.findByPrimaryKey(member_id);
	}

	public MemberVO getfindOnePK(String account) {
		return dao.findPK(account);
	}
}
