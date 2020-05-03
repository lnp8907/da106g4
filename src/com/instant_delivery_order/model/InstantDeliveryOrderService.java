package com.instant_delivery_order.model;

import java.sql.Connection;
import java.util.List;

import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;
import com.recipe_order_details.model.RecipeOrderDetailsDAO;
import com.recipe_order_details.model.RecipeOrderDetailsDAO_interface;
import com.recipe_order_details.model.RecipeOrderDetailsVO;


public class InstantDeliveryOrderService {
private InstantDeliveryOrderDAO_interface dao;
private RecipeOrderDetailsDAO_interface daodetail;
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
//獲取訂單明細全部
	public List<RecipeOrderDetailsVO> getdetail(String ido_no){
		daodetail=new RecipeOrderDetailsDAO();
		return daodetail.findByPrimaryKey_IDO_no(ido_no);
	}



}
