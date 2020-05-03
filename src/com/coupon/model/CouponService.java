package com.coupon.model;

import java.sql.Date;
import java.util.List;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponDAO();
	}

	public CouponVO addCoupon(String c_name, byte[] c_picture, Integer discount,
			Date start_date, Date end_date, String coupon_code) {

		CouponVO couponVO = new CouponVO();
	
		
		couponVO.setC_name(c_name);
		couponVO.setC_picture(c_picture);
		couponVO.setDiscount(discount);
		couponVO.setStart_date(start_date);
		couponVO.setEnd_date(end_date);
		couponVO.setCoupon_code(coupon_code);
		String c_no=dao.insert(couponVO);//新增完畢後隨即取得主鍵
		couponVO.setC_no(c_no);//取得主鍵後回傳
		
		return couponVO;
	}

	public CouponVO updateCoupon(String c_no, String c_name, byte[] c_picture, Integer discount,
			Date start_date, Date end_date, String coupon_code) {
		
		CouponVO couponVO = new CouponVO();

		couponVO.setC_no(c_no);
		couponVO.setC_name(c_name);
		couponVO.setC_picture(c_picture);
		couponVO.setDiscount(discount);
		couponVO.setStart_date(start_date);
		couponVO.setEnd_date(end_date);
		couponVO.setCoupon_code(coupon_code);
		dao.update(couponVO);

		return couponVO;
	}

	public void deleteCoupon(String c_no) {
		dao.delete(c_no);
	}

	public CouponVO getOneCoupon(String c_no) {
		return dao.findByPrimaryKey(c_no);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
}
