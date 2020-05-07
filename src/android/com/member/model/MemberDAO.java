package android.com.member.model;

import java.util.List;

public interface MemberDAO {
	boolean isMember(String account, String password);
	boolean isUserIdExist(String account);
	boolean add(Member member);
	boolean donate(String account);
	boolean getDonate(String member_id);
//	boolean update(Member member);
//	boolean delete(String account);
	Member findNameByAccount(String account);
	Member findIdByAccount(String account);
	Member findNickname(String member_id);
	Member findBalanceByAccount(String account);
	byte[] findPhotoByAccount(String account);
	//byte[] findPicByStatus(String status);
	//byte[] getImage(String isbn);
	List<Member> getAll();
	
	
	//CHH
	boolean balanceEnough(String member_id, Integer sum);
	Integer getBalance(String member_id);
	boolean payProduct(String member_id, Integer sum);
	Member findOneByAccountAndPassword(String account, String password);
}
