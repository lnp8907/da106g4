package com.shop_message.model;

import java.sql.Timestamp;

public class Shop_messageVO implements java.io.Serializable{
    private String message_no;
    private String member_id;
    private String order_no;
    private String shop_message;
    private Timestamp message_time;
    private Integer message_status;
    public Shop_messageVO() {
    }

    public Shop_messageVO(String message_no, String member_id, String order_no, String shop_message, Timestamp message_time, Integer message_status) {
        this.message_no = message_no;
        this.member_id = member_id;
        this.order_no = order_no;
        this.shop_message = shop_message;
        this.message_time = message_time;
        this.message_status = message_status;
    }

    public String getMessage_no() {
        return message_no;
    }

    public void setMessage_no(String message_no) {
        this.message_no = message_no;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getShop_message() {
        return shop_message;
    }

    public void setShop_message(String shop_message) {
        this.shop_message = shop_message;
    }

    public Timestamp getMessage_time() {
        return message_time;
    }

    public void setMessage_time(Timestamp message_time) {
        this.message_time = message_time;
    }

    public Integer getMessage_status() {
        return message_status;
    }

    public void setMessage_status(Integer message_status) {
        this.message_status = message_status;
    }

    @Override
    public String toString() {
        return "Shop_messageVO{" +
                "message_no='" + message_no + '\'' +
                ", member_id='" + member_id + '\'' +
                ", order_no='" + order_no + '\'' +
                ", shop_message='" + shop_message + '\'' +
                ", message_time=" + message_time +
                ", message_status=" + message_status +
                '}';
    }
}
