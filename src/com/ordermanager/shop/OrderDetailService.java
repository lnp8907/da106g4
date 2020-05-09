package com.ordermanager.shop;

import java.util.List;

import com.order_detail.model.Order_detailDAO;
import com.order_detail.model.Order_detailDAO_interface;
import com.order_detail.model.Order_detailVO;
import com.shop_order.model.Shop_orderJDBCDAO;
import com.shop_order.model.Shop_orderVO;

//訂單明細
public class OrderDetailService {
	private Order_detailDAO_interface dao;

	public OrderDetailService() {
		dao = new Order_detailDAO();
	}

	// 新增?
	

	// 更新
	public Order_detailVO upOneDetail(Integer quantity, Integer price, String order_no, String product_id) {
		Integer uptotal = 0;
		Order_detailVO order_detailvo = new Order_detailVO();
		order_detailvo.setQuantity(quantity);
		order_detailvo.setPrice(price);
		order_detailvo.setorder_no(order_no);
		order_detailvo.setProduct_id(product_id);
		dao.update(order_detailvo);
		// 取得總額
		uptotal = dao.getOrderDetailTotal(order_no);
//		//更新總金額
		Shop_orderJDBCDAO orderdao = new Shop_orderJDBCDAO();
		Shop_orderVO shop_ordervo = new Shop_orderVO();
		shop_ordervo.setOrder_no(order_no);
		shop_ordervo.setTotal(uptotal);
		orderdao.updatetotal(shop_ordervo);

		return order_detailvo;
	}

	// 刪除一項
	public void deleteOne(String order_no, String product_id) {
		dao.deleteOne(order_no, product_id);
		// 以下更新訂單
	}

	// 獲取特定一項
	public Order_detailVO getone(String order_no, String product_id) {
		return dao.findByPrimaryKey(order_no, product_id);
	}

	// 獲取某個訂單內的明細
	public List<Order_detailVO> getOneOrderall(String order_no) {
		return dao.getdetail(order_no);
	}

	// 獲取全部
	public List<Order_detailVO> getall() {
		return dao.getAll();
	}
	public Order_detailVO adddetail(Integer quantity, Integer price, String order_no, String product_id) {
		Order_detailVO order_detailvo = new Order_detailVO();
		order_detailvo.setorder_no(order_no);
		order_detailvo.setProduct_id(product_id);
		order_detailvo.setQuantity(quantity);
		order_detailvo.setPrice(price);
		dao.addDetail(order_detailvo);
		return order_detailvo;
	}
}
