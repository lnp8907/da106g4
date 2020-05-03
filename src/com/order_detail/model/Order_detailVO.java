package com.order_detail.model;


public class Order_detailVO implements java.io.Serializable {
	private String order_no;
	private String product_id;
	private Integer quantity;
	private Integer price;

	public Order_detailVO() {
	}

	public Order_detailVO(String order_no, String product_id, Integer quantity, Integer price) {
		this.order_no = order_no;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
	}

	public String getorder_no() {
		return order_no;
	}

	public void setorder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "order_detailVO{" +
				"order_no='" + order_no + '\'' +
				", product_id='" + product_id + '\'' +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_no == null) ? 0 : order_no.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order_detailVO other = (Order_detailVO) obj;
		
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		return true;
	}
	
}
