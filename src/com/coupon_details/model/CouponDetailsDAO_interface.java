package com.coupon_details.model;

import java.util.List;

public interface CouponDetailsDAO_interface {
        public void insert(String c_no, String[] product_id);
        public void update(CouponDetailsVO couponDetailsVO, CouponDetailsVO couponDetailsVO_1);
        public void delete(String c_no, String product_id);
        public List<CouponDetailsVO> findByPrimaryKey_C_no(String c_no);
        public List<CouponDetailsVO> findByPrimaryKey_Product_id(String product_id);
        public List<CouponDetailsVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<RecipeVO> getAll(Map<String, String[]> map); 
}


