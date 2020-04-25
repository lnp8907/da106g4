package com.shop_order.model;

import java.util.Random;

import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;

public class InsertOrder {
	public static void main(String[] args) {
		String address[] = new String[6];
		address[0] = "台北市中正區重慶南路一段122號";
		address[1] = "台中市西屯區四川東街33號 ";
		address[2] = "台南市中西區萬昌街15號";
		address[3] = "高雄市鼓山區大順一路459號";
		address[4] = "高雄市三民區慶雲街105號";
		address[5] = "桃園市中壢區中山路123號";

		int i=1;

		for(int b=0;b<=5;b++) {
			Shop_orderJDBCDAO dao = new Shop_orderJDBCDAO();
			Shop_orderVO vo = new Shop_orderVO();
			String b2=(b+1)+"";
				vo.setMember_id("81000"+b2);
				vo.setOrder_status(0);
				vo.setTotal(9999);
				vo.setPay_type(1);
				vo.setDv_address(address[i-1]);
				dao.insert(vo, null);
				///先產生一筆訂單
				Random r = new Random();
				int a = r.nextInt(3)+1;
				InsertOrder.buy(a, vo);
	i++;
		}
		System.out.println("希望筆數OK");
		
	}
	
	
	public static Order_detailVO buy(int a,Shop_orderVO vo) {
		
		
		
		
		Shop_orderJDBCDAO dao = new Shop_orderJDBCDAO();
		Order_detailJDBCDAO orderdao=new Order_detailJDBCDAO();
	
		int p=0;
		
		Integer total=0;
		Order_detailVO ordervo=new Order_detailVO();
		for(int i=0;i<a;i++) {
			Random r = new Random();
			int r2 = r.nextInt(20)+1;
			String po=null;
			p+=r2;
			if(p<10) {
				po="0"+p;
			}
			else {
				po=p+"";
			}
			
			int i2 = r.nextInt(3)+1;
			String productid="3200"+po;
			ordervo.setorder_no(dao.getfresh());
			ordervo.setProduct_id(productid);
			ordervo.setPrice(orderdao.getprice(productid));
			ordervo.setQuantity(i2);
			//明細送出
			orderdao.insert(ordervo, null);
			
			//計算剛剛商品的總金額
			Integer pto=(ordervo.getQuantity())*(ordervo.getPrice());
			//資料送入新增商城訂單
			total+=pto;
			p+=i2;
		}
		
		vo.setTotal(total);
		vo.setOrder_no(dao.getfresh());
		dao.updatetotal(vo);
		return ordervo;
		
	}

}
