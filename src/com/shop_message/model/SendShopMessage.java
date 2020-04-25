package com.shop_message.model;

import java.util.Random;

public class SendShopMessage {
	public static void main(String[] args) {
		
		String str[]=new String[3];
		str[0]="親愛的客戶：\r\n" + 
				"您好，因為您的問題要進一步確認，最晚在2天內回覆(不含例假日)，請稍加等候。\r\n" + 
				"如果提早處理完成也會趕快回給您，請放心。\r\n" + 
				"祝您有美好的一天";
		str[1]="的支持與寶貴意見是我們持續努力前進的最大動力，懇請您撥冗填寫 購物滿意度問卷 ，您此次購買商品的滿意度，將做為提升服務品質的重要參考依據，感謝您的協助！ ";
		str[2]="我們已經接受您的訂單，並為您安排出貨，預計最晚 XXXX/XX/XX XX:XX 前送達。 ";

		for(int i=0;i<5;i++) {
			Random r = new Random();
			String i0=(i+1)+"";
			int i1 = r.nextInt(1) ;
			int i2 = r.nextInt(5)+1;
			int i3 = r.nextInt(2);
			Shop_messageJDBCDVO dao=new Shop_messageJDBCDVO ();
			Shop_messageVO vo=new Shop_messageVO();
			vo.setMember_id("81000"+i2);
			System.out.println(dao.getorderid(i2));
			vo.setOrder_no(dao.getorderid(i2));
			vo.setMessage_status(i1);
			System.out.println(i2);
			vo.setShop_message(str[i3]);
			dao.insert(vo);				
		}
	}

}
