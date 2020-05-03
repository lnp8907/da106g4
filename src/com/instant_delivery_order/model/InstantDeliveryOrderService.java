package com.instant_delivery_order.model;

import java.util.List;


public class InstantDeliveryOrderService {
private InstantDeliveryOrderDAO_interface dao;

public InstantDeliveryOrderService() {
		dao = new InstantDeliveryOrderDAO();
}
public List<InstantDeliveryOrderVO>getAll(){
	return dao.getAll();
}
//
public InstantDeliveryOrderVO getOneOrder(String ido_no) {
	return dao.findByPrimaryKey(ido_no);
}
public void changeOrderStatus(String ido_no,Integer o_status) {
	
	dao.changeOrderStatus(ido_no, o_status);
	
}
public void changePayStatus(String ido_no,Integer p_status) {
	
	dao.changePayStatus(ido_no, p_status);
	
}


}
