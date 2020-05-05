package android.com.recipe.model;

import java.sql.Date;

public class RecipeVO_A extends RecipeVO implements java.io.Serializable {
	private Integer recipe_price;
	

	public RecipeVO_A() {
		super();
	}

	public RecipeVO_A(Integer recipe_price) {
		super();
		this.recipe_price = recipe_price;
	}

	public RecipeVO_A(String recipe_id, String rcstyle_no, String member_id, String recipe_name, String recipe_type,
			Integer recipe_status, Integer refollow_num, Date recipe_uldate, String recipe_ingredient,
			String recipe_step, String recipe_photo, Integer cook_time, Double calo_intake, Double salt_intake,
			Double protein_intake, Double fat_intake, Double carbo_intake, Double vitamin_b, Double vitamin_c,
			Double vage_intake, String recipe_content, Integer recipe_price) {
		super(recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_status, refollow_num, recipe_uldate,
				recipe_ingredient, recipe_step, recipe_photo, cook_time, calo_intake, salt_intake, protein_intake,
				fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake, recipe_content);
		this.recipe_price = recipe_price;
	}

	public Integer getRecipe_price() {
		return recipe_price;
	}

	public void setRecipe_price(Integer recipe_price) {
		this.recipe_price = recipe_price;
	}

}
