package com.recipe_order_details.model;

public class RecipeOrderDetailsVO implements java.io.Serializable{
	private String ido_no;
	private	String product_id;
	private	Integer quantity;
	private	Integer price;

	public RecipeOrderDetailsVO() {
		super();
	}

	public String getIDO_no() {
		return ido_no;
	}

	public void setIDO_no(String ido_no) {
		this.ido_no = ido_no;
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
		return "RecipeOrderDetailsVO [ido_no=" + ido_no + ", product_id=" + product_id + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}


}
