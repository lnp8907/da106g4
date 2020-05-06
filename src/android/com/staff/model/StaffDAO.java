package android.com.staff.model;

import java.util.List;

import android.com.member.model.Member;

public interface StaffDAO {
	boolean isMember(String staff_id, String staff_password);
	boolean isUserIdExist(String staff_id);
//	boolean add(Member member);
//	boolean update(Member member);
//	boolean delete(String account);
	Staff findById(String staff_id);
	List<Staff> getAll();
}
