package com.livestream.model;

import java.util.List;

public class LsService {

	private LivestreamDAO_interface dao;

	public LsService() {
		dao = new LivestreamDAO();
	}
	 public LivestreamVO getLatestOneLs(String member_id) {
		return dao.getLatestOneLs(member_id) ;
	 }
   
	 public void updateAfterOnline (String livestream_id,byte[] video,Integer watched_num,Integer status) {
		 status = 3; //直播結束狀態是3
		 dao.updateAfterOnline(livestream_id, video, watched_num,status);
     }
	
	
     
     
     public LivestreamVO getMostPopLS() {
		return dao.getMostPopLS();
	}
	public List<LivestreamVO> getFourForHomePage(){
		return dao.getFourForHomePage();
	}
	public LivestreamVO addLs(String member_id, java.sql.Date livestream_date, byte[] picture,
			String introduction, String title) {

		LivestreamVO livestreamVO = new LivestreamVO();

		livestreamVO.setMember_id(member_id);
		livestreamVO.setLivestream_date(livestream_date);
		livestreamVO.setPicture(picture);
		livestreamVO.setIntroduction(introduction);
		livestreamVO.setTitle(title);
		dao.insert(livestreamVO);

		return livestreamVO;
	}

//	public LivestreamVO updateLs(java.sql.Date livestream_date, Integer status, byte[] picture,
//			String introduction, String title) {
//
//		LivestreamVO livestreamVO = new LivestreamVO();
//
//		livestreamVO.setLivestream_date(livestream_date);
//		livestreamVO.setStatus(status);
//		livestreamVO.setPicture(picture);
//		livestreamVO.setIntroduction(introduction);
//		livestreamVO.setTitle(title);
//		dao.update(livestreamVO);
//
//		return livestreamVO;
//	}
	
	public LivestreamVO updateLs(String livestream_id, Integer status) {

		LivestreamVO livestreamVO = new LivestreamVO();

		livestreamVO.setLivestream_id(livestream_id);
		livestreamVO.setStatus(status);
		System.out.println(livestreamVO);
		dao.updatestatus(livestreamVO);

		return livestreamVO;
	}

	public void deleteLs(String livestream_id) {
		dao.delete(livestream_id);
	}

	public LivestreamVO getOneLs(String livestream_id) {
		return dao.findByPrimaryKey(livestream_id);
	}

	public List<LivestreamVO> getAll() {
		return dao.getAll();
	}
}
