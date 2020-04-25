package com.shop_order.model;

import java.util.*;

import com.order_detail.model.Order_detailVO;

public interface Shop_orderDAO_interface {
    public void insert(Shop_orderVO shop_ordervo,List<Order_detailVO>list);
    public void update(Shop_orderVO shop_ordervo);
    public void delete(String order_no);
    public Shop_orderVO findByPrimaryKey(String order_no);
    public List<Shop_orderVO> getAll();
    public List<Shop_orderVO> getOrderBYMEMBER(String member_id);
    public void updatetotal(Shop_orderVO shop_ordervo);

}
