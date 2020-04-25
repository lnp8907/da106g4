package com.donation_record.model;

import java.util.Date;
import java.util.List;

public class DrService {

	private DonationRecordDAO_interface dao;

	public DrService() {
		dao = new Donation_RecordDAO();
	}

	public Donation_RecordVO addDr(String livestream_id, String member_id, Integer donation_cost) {

		Donation_RecordVO donation_RecordVO = new Donation_RecordVO();
		
		donation_RecordVO.setLivestream_id(livestream_id);
		donation_RecordVO.setMember_id(member_id);
		donation_RecordVO.setDonation_cost(donation_cost);
		
		dao.insert(donation_RecordVO);

		return donation_RecordVO;
	}

	public Donation_RecordVO getOneDr(String livestream_id) {
		return dao.findByPrimaryKey(livestream_id);
	}
	
	public List<Donation_RecordVO> getAll() {
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

//	public void deleteLs(String livestream_id) {
//		dao.delete(livestream_id);
//	}

}
