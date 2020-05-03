package com.product_browsing_history.model;

public class Product_browing_historyVO {
	 private String member_id;

    private String product_id;

	
	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getProduct_id() {
		return product_id;
	}


	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public Product_browing_historyVO(String member_id, String product_id) {
		super();
		this.member_id = member_id;
		this.product_id = product_id;
	}


	public Product_browing_historyVO() {
		super();
	}


	@Override
	public String toString() {
		return "Product_browing_historyVO [member_id=" + member_id + ", product_id=" + product_id + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
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
		Product_browing_historyVO other = (Product_browing_historyVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		return true;
	}

	


}
