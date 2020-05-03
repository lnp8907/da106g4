package com.coupon_details.model;

import java.util.List;

public class CouponDetailsService {
	private CouponDetailsDAO_interface dao;

	public CouponDetailsService() {
		dao = new CouponDetailsDAO();
	}
	 public void insert(String c_no, String[] product_id) {
		 dao.insert(c_no, product_id);
	 }
	
	 public List<CouponDetailsVO> findByPrimaryKey_C_no(String c_no){
		 return dao.findByPrimaryKey_C_no(c_no);
	 }
}
