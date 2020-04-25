package com.ordermanager.shop;

import com.shop_message.model.Shop_messageDVO_interface;
import com.shop_message.model.Shop_messageJDBCDVO;

public class ShopMessageService {
	private Shop_messageDVO_interface dao;
//建立空的建構子
	public ShopMessageService() {
		dao=new Shop_messageJDBCDVO();
	}
	
	

}
