package com.order_detail.model;


import java.sql.Connection;
import java.util.List;

public interface Order_detailDAO_interface {
    public void insert(Order_detailVO order_detailvo,Connection con);
    public void addDetail(Order_detailVO order_detailvo);

    public void update(Order_detailVO order_detailvo);
    public void delete(String order_no);
    public Order_detailVO findByPrimaryKey(String order_no,String product_id);
    public List<Order_detailVO> getdetail(String order_no);
    public List<Order_detailVO> getAll();
    
    public void deleteOne(String order_no ,String product_id) ;
    public Integer getOrderDetailTotal(String order_no) ;




}
