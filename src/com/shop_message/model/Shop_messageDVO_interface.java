package com.shop_message.model;

import java.util.List;

import com.order_detail.model.Order_detailVO;

public interface Shop_messageDVO_interface {
    public void insert(Shop_messageVO shop_messagevo);
    public void update(Shop_messageVO shop_messagevo);
    public void delete(String message_no);
    public Shop_messageVO findByPrimaryKey(String message_no);
    public List<Shop_messageVO> getAll();

}
