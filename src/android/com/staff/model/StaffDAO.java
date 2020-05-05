package android.com.staff.model;

import java.util.List;

import android.com.member.model.Member;

public interface StaffDAO {
	boolean isMember(String staff_account, String staff_password);
	boolean isUserIdExist(String staff_account);
//	boolean add(Member member);
//	boolean update(Member member);
//	boolean delete(String account);
	Staff findById(String staff_account);
	List<Staff> getAll();
}
