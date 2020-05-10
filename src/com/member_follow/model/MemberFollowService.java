package com.member_follow.model;

import java.util.List;

public class MemberFollowService { //getAllMemberByFollowed
	private Member_followDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	 public MemberFollowService() {
	  dao = new Member_followDAO();
	 }
	 
	 public String[] getAllMemberByFollowed(String followed) {
	  List<Member_followVO> list = dao.getAllMemberByFollowed(followed);
	  String[] mbs_id = new String[list.size()];
	//  for (int i = 0; i < mbs_id.length; i++) {
	//   mbs_id[i]=list[i].getMb_id();
	//  }
	  int i =0;
	  for(Member_followVO follow:list) {
	   mbs_id[i]=follow.getFollowed();
	   i++;
	  }
	  return mbs_id;
	 }
	 
//	 public String getByMb_idPlus(String mb_id) {
//	  List<Mb_followVO> list = dao.findByMbId(mb_id);
//	  StringBuffer sb= new StringBuffer();
//	  for(Mb_followVO follow:list) {
//	   sb.append(follow.getMb_id_followed()+"+");
//	  }
//	  return sb.toString();
//	 }
//	 
//	 public void insertByString(String mb_id, String mb_id_followed) {
//	  dao.insertByString(mb_id, mb_id_followed);
//	 }
//	 
//	 public void deleteByString(String mb_id, String mb_id_followed) {
//	  dao.deleteByString(mb_id, mb_id_followed);
//	 }
//	 
//	 // 是否有追蹤
//	 public boolean hasFollow(String mb_id, String mb_id_followed) {
//	  return dao.hasFollow(mb_id, mb_id_followed);
//	 }
}
