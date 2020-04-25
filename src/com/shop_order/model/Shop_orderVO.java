package com.shop_order.model;

import java.sql.Timestamp;

public class Shop_orderVO implements java.io.Serializable{
   private String order_no;
   private String member_id;
   private Integer order_status;
   private Timestamp order_time;
   private Integer total;   
   private Integer pay_type;
   private String dv_address;

   public Shop_orderVO() {
   }

   public Shop_orderVO(String order_no, String member_id, Integer order_status, Timestamp order_time, Integer total, Integer pay_type, String dv_address) {
      this.order_no = order_no;
      this.member_id = member_id;
      this.order_status = order_status;
      this.order_time = order_time;
      this.total = total;
      this.pay_type = pay_type;
      this.dv_address = dv_address;
   }

   public String getOrder_no() {
      return order_no;
   }

   public void setOrder_no(String order_no) {
      this.order_no = order_no;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }

   public Integer getOrder_status() {
      return order_status;
   }

   public void setOrder_status(Integer order_status) {
      this.order_status = order_status;
   }

   public Timestamp getOrder_time() {
      return order_time;
   }

   public void setOrder_time(Timestamp timestamp) {
      this.order_time = timestamp;
   }

   public Integer getTotal() {
      return total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public Integer getPay_type() {
      return pay_type;
   }

   public void setPay_type(Integer pay_type) {
      this.pay_type = pay_type;
   }

   public String getDv_address() {
      return dv_address;
   }

   public void setDv_address(String dv_address) {
      this.dv_address = dv_address;
   }

@Override
public String toString() {
	return "Shop_orderVO [order_no=" + order_no + ", member_id=" + member_id + ", order_status=" + order_status
			+ ", order_time=" + order_time + ", total=" + total + ", pay_type=" + pay_type + ", dv_address="
			+ dv_address + "]";
}
   
}
