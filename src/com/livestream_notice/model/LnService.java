package com.livestream_notice.model;

import java.util.Date;
import java.util.List;

public class LnService {

	private Livestream_NoticeDAO_interface dao;

	public LnService() {
		dao = new Livestream_NoticeDAO();
	}

	public Livestream_NoticeVO addLn(String livestream_id, String member_id, Integer donation_cost) {

		Livestream_NoticeVO livestream_NoticeVO = new Livestream_NoticeVO();
		
		livestream_NoticeVO.setLivestream_id(livestream_id);
		livestream_NoticeVO.setMember_id(member_id);
//		livestream_NoticeVO.setDonation_cost(donation_cost);
		
		dao.insert(livestream_NoticeVO);

		return livestream_NoticeVO;
	}

	public Livestream_NoticeVO getOneLn(String lsnotice_id) {
		return dao.findByPrimaryKey(lsnotice_id);
	}
	
	public List<Livestream_NoticeVO> getAll() {
		return dao.getAll();
	}
	
//	public Donation_RecordVO updateDr(String livestream_id, Integer status) {
//
//		LivestreamVO livestreamVO = new LivestreamVO();
//
//		livestreamVO.setLivestream_id(livestream_id);
//		livestreamVO.setStatus(status);
//		System.out.println(livestreamVO);
//		dao.updatestatus(livestreamVO);
//
//		return livestreamVO;
//	}

//	public void deleteLn(String livestream_id) {
//		dao.delete(livestream_id);
//	}

}
