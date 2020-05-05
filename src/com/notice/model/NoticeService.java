package com.notice.model;


public class NoticeService {
	private NoticeDAO_interface dao;

	public NoticeService() {
		
		dao=new NoticeDAO();
	}

	public NoticeVO insert(String member_id, Integer notice_category, String content,Integer notice_status) {
		System.out.println("啟動服務準備新增");

		NoticeVO VO = new NoticeVO();
		VO.setMember_id(member_id);
		VO.setNotice_category(notice_category);
		VO.setContent(content);
		VO.setNotice_status(notice_status);
		
		
		dao.insert(VO);
		return VO;
	}
//	public void removelist(String member_id,String product_id) {
//		System.out.println("用於移除");
//		Product_browing_historyVO VO = new Product_browing_historyVO();
//		VO.setMember_id(member_id);
//		VO.setProduct_id(product_id);
//		dao.delete(VO);
//	}
//	
//	public void deletelist(String member_id) {
//		System.out.println("用於刪除紀錄");
//	
//		dao.deleteAll(member_id);
//	}
//		
	

}
