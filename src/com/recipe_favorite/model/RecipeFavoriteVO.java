package com.recipe_favorite.model;

public class RecipeFavoriteVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String recipe_id;
	private String member_id;
	@Override
	public String toString() {
		return "RecipeFavoriteVO [recipe_id=" + recipe_id + ", member_id=" + member_id + "]";
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}
