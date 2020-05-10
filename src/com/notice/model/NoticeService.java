package com.notice.model;

import java.util.List;

public class NoticeService {
	private NoticeDAO_interface dao;

	public NoticeService() {

		dao = new NoticeDAO();
	}
	
	 public void changeStatus(String notice_id, Integer notice_status) {
		 NoticeVO VO = new NoticeVO();
		 VO.setNotice_id(notice_id);
		 VO.setNotice_status(notice_status);
		 dao.changeStatus(notice_id, notice_status);
	 }

	public NoticeVO insert(String member_id, Integer notice_category, String content, Integer notice_status) {
		System.out.println("啟動服務準備新增");

		NoticeVO VO = new NoticeVO();
		VO.setMember_id(member_id);
		VO.setNotice_category(notice_category);
		VO.setContent(content);
		VO.setNotice_status(notice_status);

		dao.insert(VO);
		return VO;
	}

	public NoticeVO updateMessage(String notice_id, String member_id, Integer notice_category, String content, Integer notice_status) {

		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setNotice_id(notice_id);
		noticeVO.setMember_id(member_id);
		noticeVO.setNotice_category(notice_category);
		noticeVO.setContent(content);
		noticeVO.setNotice_status(notice_status);
		dao.update(noticeVO);
		return noticeVO;
	}

//	public void deleteMessage(String msg_no) {
//		dao.delete(msg_no);
//	}

//	public NoticeVO getOneMessage(String msg_no) {
//		return dao.findByPrimaryKey(msg_no);
//	}

	public List<NoticeVO> getAll() {
		return dao.getAll();
	}

	public List<NoticeVO> getAllByMb_id_2(String member_id) {
		return dao.getAllByMb_id_2(member_id);
	}

	public Integer countNotReads(String member_id) {
		return dao.countNotReads(member_id);
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
