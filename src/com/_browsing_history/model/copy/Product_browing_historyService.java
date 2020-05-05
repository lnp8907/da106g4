package com._browsing_history.model.copy;


public class Product_browing_historyService {
	private Product_browing_historyDAO_interface dao;

	public Product_browing_historyService() {
		
		dao=new Product_browing_historyDAO();
	}

	public Product_browing_historyVO insert(String member_id, String product_id) {
		System.out.println("啟動服務準備新增");

		Product_browing_historyVO VO = new Product_browing_historyVO();
		VO.setMember_id(member_id);
		VO.setProduct_id(product_id);
		
		dao.insert(VO);
		return VO;
	}
	public void removelist(String member_id,String product_id) {
		System.out.println("用於移除");
		Product_browing_historyVO VO = new Product_browing_historyVO();
		VO.setMember_id(member_id);
		VO.setProduct_id(product_id);
		dao.delete(VO);
	}
	
	public void deletelist(String member_id) {
		System.out.println("用於刪除紀錄");
	
		dao.deleteAll(member_id);
	}
		
	

}
