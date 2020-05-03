package com.coupon.model;

import java.util.*;

public interface CouponDAO_interface {
          public String insert(CouponVO couponVO);
          public void update(CouponVO couponVO);
          public void delete(String c_no);
          public CouponVO findByPrimaryKey(String c_no);
          public List<CouponVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
      //  public List<RecipeVO> getAll(Map<String, String[]> map); 
}
