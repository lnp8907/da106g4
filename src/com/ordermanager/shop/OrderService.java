package com.ordermanager.shop;

import java.util.List;

import com.order_detail.model.Order_detailDAO_interface;
import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;
import com.shop_order.model.Shop_orderDAO;
import com.shop_order.model.Shop_orderDAO_interface;
import com.shop_order.model.Shop_orderVO;

public class OrderService {
	private Shop_orderDAO_interface dao;
	private Order_detailDAO_interface daodetail;

	public OrderService() {
		dao=new Shop_orderDAO();

	}
	
	public Shop_orderVO upateOrder(Integer order_status,String dv_address,String order_no) {
		Shop_orderVO vo=new Shop_orderVO();
		vo.setOrder_status(order_status);
		vo.setDv_address(dv_address);
		vo.setOrder_no(order_no);
		dao.update(vo);
		return vo;
			}
	public Shop_orderVO changestatus(Integer order_status,String order_no) {
		Shop_orderVO vo=new Shop_orderVO();
		vo.setOrder_status(order_status);
		vo.setOrder_no(order_no);
		dao.changestatus(vo);
		return vo;
			}
	public void delete(String order_no) {
		daodetail=new Order_detailJDBCDAO();
		daodetail.delete(order_no);
		dao.delete(order_no);
	}
	public List<Shop_orderVO>getAll(){
		return dao.getAll();
	}
	public List<Shop_orderVO>getOrderBYMEMBER(String member_id){
		return dao.getOrderBYMEMBER(member_id);
	}
	public Shop_orderVO getOneOrder(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}
	//獲取訂單明細全部
	public List<Order_detailVO> getdetail(String order_no){
		daodetail=new Order_detailJDBCDAO();
		return daodetail.getdetail(order_no);
	}
	public void updatetotal(String order_no) {
		Integer uptotal = 0;
		Order_detailJDBCDAO	orderdao = new Order_detailJDBCDAO();

		uptotal = orderdao.getOrderDetailTotal(order_no);
//		//更新總金額
		Shop_orderVO shop_ordervo = new Shop_orderVO();
		shop_ordervo.setOrder_no(order_no);
		shop_ordervo.setTotal(uptotal);
		dao.updatetotal(shop_ordervo);
	}
	public Integer gettotal(String order_no) {
		Integer uptotal = 0;
		Order_detailJDBCDAO	orderdao = new Order_detailJDBCDAO();

		uptotal = orderdao.getOrderDetailTotal(order_no);
		return uptotal;
		
	}//新增訂單
	public void addOrder(Shop_orderVO shop_ordervo,List<Order_detailVO>list) {
		dao.insert(shop_ordervo, list);
	}


}