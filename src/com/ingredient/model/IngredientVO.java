package com.ingredient.model;

public class IngredientVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ingredient_id;
	private String ingredient_type;
	private String ingredient_name;
	private Double carbohydrate;
	private Double protein;
	private Double fat;
	private Double calorie;
	private Double vitamin_B;
	private Double vitamin_C;
	private Double salt;
	private Double vgetable;

	public String getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(String ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	public String getIngredient_type() {
		return ingredient_type;
	}

	public void setIngredient_type(String ingredient_type) {
		this.ingredient_type = ingredient_type;
	}

	public String getIngredient_name() {
		return ingredient_name;
	}

	public void setIngredient_name(String ingredient_name) {
		this.ingredient_name = ingredient_name;
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

	public Double getVgetable() {
		return vgetable;
	}

	public void setVgetable(Double vgetable) {
		this.vgetable = vgetable;
	}

	@Override
	public String toString() {
		return "IngredientVO [ingredient_id=" + ingredient_id + ", ingredient_type=" + ingredient_type
				+ ", ingredient_name=" + ingredient_name + ", carbohydrate=" + carbohydrate + ", protein=" + protein
				+ ", fat=" + fat + ", calorie=" + calorie + ", vitamin_B=" + vitamin_B + ", vitamin_C=" + vitamin_C
				+ ", salt=" + salt + ", vgetable=" + vgetable + "]";
	}

}
