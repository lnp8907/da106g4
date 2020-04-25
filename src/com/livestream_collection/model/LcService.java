package com.livestream_collection.model;

import java.util.Date;
import java.util.List;

public class LcService {

	private Livestream_CollectionDAO_interface dao;

	public LcService() {
		dao = new Livestream_CollectionDAO();
	}

	public Livestream_CollectionVO addLc(String livestream_id, String member_id) {

		Livestream_CollectionVO livestream_CollectionVO = new Livestream_CollectionVO();
		
		livestream_CollectionVO.setLivestream_id(livestream_id);
		livestream_CollectionVO.setMember_id(member_id);
		
		dao.insert(livestream_CollectionVO);

		return livestream_CollectionVO;
	}

	public Livestream_CollectionVO getOneLc(String livestream_id) {
		return dao.findByPrimaryKey(livestream_id);
	}
	
	public List<Livestream_CollectionVO> getAll() {
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
