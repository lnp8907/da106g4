package android.com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	List<CouponVO> getAll();
	byte[] getImage(String c_no);
	CouponVO getDiscountByCode(String coupon_code);
}
