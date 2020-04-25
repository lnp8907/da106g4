package com.recipe.model;

import java.sql.Date;

public class RecipeVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	String	recipe_id;
	private	String	rcstyle_no;
	private	String	member_id;
	private	String	recipe_name;
	private	String	recipe_type;
	private	Integer	recipe_status;
	private	Integer	refollow_num;
	private	Date	recipe_uldate;
	private	String	recipe_ingredient;
	private	String	recipe_step;
	private	String	recipe_photo;
	private	Integer	cook_time;
	private	Double	calo_intake;
	private	Double	salt_intake;
	private	Double	protein_intake;
	private	Double	fat_intake;
	private	Double	carbo_intake;
	private	Double	vitamin_b;
	private	Double	vitamin_c;
	private	Double	vage_intake;
	private	String	recipe_content;
	
	
	
	public RecipeVO() {
		super();
	}
	public String getRecipe_content() {
		return recipe_content;
	}
	public void setRecipe_content(String recipe_content) {
		this.recipe_content = recipe_content;
	}
	
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getRcstyle_no() {
		return rcstyle_no;
	}
	public void setRcstyle_no(String rcstyle_no) {
		this.rcstyle_no = rcstyle_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRecipe_name() {
		return recipe_name;
	}
	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	public String getRecipe_type() {
		return recipe_type;
	}
	public void setRecipe_type(String recipe_type) {
		this.recipe_type = recipe_type;
	}
	public Integer getRecipe_status() {
		return recipe_status;
	}
	public void setRecipe_status(Integer recipe_status) {
		this.recipe_status = recipe_status;
	}
	public Integer getRefollow_num() {
		return refollow_num;
	}
	public void setRefollow_num(Integer refollow_num) {
		this.refollow_num = refollow_num;
	}
	public Date getRecipe_uldate() {
		return recipe_uldate;
	}
	public void setRecipe_uldate(Date recipe_uldate) {
		this.recipe_uldate = recipe_uldate;
	}
	public String getRecipe_ingredient() {
		return recipe_ingredient;
	}
	public void setRecipe_ingredient(String recipe_ingredient) {
		this.recipe_ingredient = recipe_ingredient;
	}
	public String getRecipe_step() {
		return recipe_step;
	}
	public void setRecipe_step(String recipe_step) {
		this.recipe_step = recipe_step;
	}
	public String getRecipe_photo() {
		return recipe_photo;
	}
	public void setRecipe_photo(String recipe_photo) {
		this.recipe_photo = recipe_photo;
	}
	public Integer getCook_time() {
		return cook_time;
	}
	public void setCook_time(Integer cook_time) {
		this.cook_time = cook_time;
	}
	public Double getCalo_intake() {
		return calo_intake;
	}
	public void setCalo_intake(Double calo_intake) {
		this.calo_intake = calo_intake;
	}
	public Double getSalt_intake() {
		return salt_intake;
	}
	public void setSalt_intake(Double salt_intake) {
		this.salt_intake = salt_intake;
	}
	public Double getProtein_intake() {
		return protein_intake;
	}
	public void setProtein_intake(Double protein_intake) {
		this.protein_intake = protein_intake;
	}
	public Double getFat_intake() {
		return fat_intake;
	}
	public void setFat_intake(Double fat_intake) {
		this.fat_intake = fat_intake;
	}
	public Double getCarbo_intake() {
		return carbo_intake;
	}
	public void setCarbo_intake(Double carbo_intake) {
		this.carbo_intake = carbo_intake;
	}
	public Double getVitamin_b() {
		return vitamin_b;
	}
	public void setVitamin_b(Double vitamin_b) {
		this.vitamin_b = vitamin_b;
	}
	public Double getVitamin_c() {
		return vitamin_c;
	}
	public void setVitamin_c(Double vitamin_c) {
		this.vitamin_c = vitamin_c;
	}
	public Double getVage_intake() {
		return vage_intake;
	}
	public void setVage_intake(Double vage_intake) {
		this.vage_intake = vage_intake;
	}
	@Override
	public String toString() {
		return "RecipeVO [recipe_id=" + recipe_id + ", rcstyle_no=" + rcstyle_no + ", member_id=" + member_id
				+ ", recipe_name=" + recipe_name + ", recipe_type=" + recipe_type + ", recipe_status=" + recipe_status
				+ ", refollow_num=" + refollow_num + ", recipe_uldate=" + recipe_uldate + ", recipe_ingredient="
				+ recipe_ingredient + ", recipe_step=" + recipe_step + ", recipe_photo= " + "BASE64,"
				+ ", cook_time=" + cook_time + ", calo_intake=" + calo_intake + ", salt_intake=" + salt_intake
				+ ", protein_intake=" + protein_intake + ", fat_intake=" + fat_intake + ", carbo_intake=" + carbo_intake
				+ ", vitamin_b=" + vitamin_b + ", vitamin_c=" + vitamin_c + ", vage_intake=" + vage_intake
				+ ", recipe_content=" + recipe_content + "]";
	}
}
