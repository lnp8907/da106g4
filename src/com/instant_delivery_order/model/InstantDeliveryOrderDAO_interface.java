package com.instant_delivery_order.model;

import java.util.*;

public interface InstantDeliveryOrderDAO_interface {
          public void insert(InstantDeliveryOrderVO instantDeliveryOrderVO);
          public void update(InstantDeliveryOrderVO instantDeliveryOrderVO);
          public void delete(String ido_no);
          public InstantDeliveryOrderVO findByPrimaryKey(String ido_no);
          public List<InstantDeliveryOrderVO> getAll();
          //
          //更改繳費狀態
          public void changePayStatus(String ido_no,Integer p_status);
          //更改訂單狀態
          public void changeOrderStatus(String ido_no,Integer o_status);
          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
      //  public List<RecipeVO> getAll(Map<String, String[]> map); 
}
