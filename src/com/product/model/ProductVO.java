package com.product.model;

import java.util.Arrays;

public class ProductVO implements java.io.Serializable{
    private String product_id;
    private String recipe_id;
    private String product_type;
    private String product_name;
    private Integer product_price;
    private byte[] product_photo;
    private Integer product_status;

    private Double carbohydrate;
    private Double protein;
    private Double fat;
    private Double calorie;
    private Double vitamin_B;
    private Double vitamin_C;
    private Double salt;
    private Double vagetbale;  
    private String content;


    public ProductVO() {
    }
	public ProductVO(String product_id, String recipe_id, String product_type, String product_name,
			Integer product_price, byte[] product_photo, Integer product_status, Double carbohydrate, Double protein,
			Double fat, Double calorie, Double vitamin_B, Double vitamin_C, Double salt, Double vagetbale,
			String content) {
		super();
		this.product_id = product_id;
		this.recipe_id = recipe_id;
		this.product_type = product_type;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_photo = product_photo;
		this.product_status = product_status;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.calorie = calorie;
		this.vitamin_B = vitamin_B;
		this.vitamin_C = vitamin_C;
		this.salt = salt;
		this.vagetbale = vagetbale;
		this.content = content;
	}











	public Double getVitamin_B() {
		return vitamin_B;
	}






	public void setVitamin_B(Double vitamin_B) {
		this.vitamin_B = vitamin_B;
	}






	public Double getVitamin_C() {
		return vitamin_C;
	}






	public void setVitamin_C(Double vitamin_C) {
		this.vitamin_C = vitamin_C;
	}






	public Double getSalt() {
		return salt;
	}






	public void setSalt(Double salt) {
		this.salt = salt;
	}



	





	public Double getVagetbale() {
		return vagetbale;
	}
	public void setVagetbale(Double vagetbale) {
		this.vagetbale = vagetbale;
	}
	public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }
	public byte[] getProduct_photo() {
		return product_photo;
	}
	public void setProduct_photo(byte[] product_photo) {
		this.product_photo = product_photo;
	}
	public Integer getProduct_status() {
        return product_status;
    }

    public void setProduct_status(Integer product_status) {
        this.product_status = product_status;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", recipe_id=" + recipe_id + ", product_type=" + product_type
				+ ", product_name=" + product_name + ", product_price=" + product_price + ", product_photo="
				+ Arrays.toString(product_photo) + ", product_status=" + product_status + ", carbohydrate="
				+ carbohydrate + ", protein=" + protein + ", fat=" + fat + ", calorie=" + calorie + ", vitamin_B="
				+ vitamin_B + ", vitamin_C=" + vitamin_C + ", salt=" + salt + ", vagetbale=" + vagetbale + ", content="
				+ content + "]";
	}
	public String Howstatus() {
		String status="";
		if(product_status==0) {
			status="未上架";
		}
		else if(product_status==1) {
			status="已上架";			
		}
		else {
			status="異常狀態";
		}
		return status;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;		
			ProductVO other=(ProductVO) obj;
			if(product_name==null) {
				return false;
			}
				if(other.product_name!=null) {
					return false;
				}
				else if(!product_name.equals(other.product_name)) {
					return false;

					
				}
					return true;
			}
			

	
	
	
}
