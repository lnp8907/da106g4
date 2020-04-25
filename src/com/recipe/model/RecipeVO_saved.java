package com.recipe.model;

import java.util.Arrays;

import javax.servlet.http.Part;

public class RecipeVO_saved extends RecipeVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	String[] recipe_ingredients;
	String[] ingredient_amount;
	String[] units;
	String[] recipe_steps;
	String ingredients_str;
	String tempPhto;
	
	public RecipeVO_saved() {
		super();
	}

	public RecipeVO_saved(String[] recipe_ingredients, String[] ingredient_amount, String[] units,
			String[] recipe_steps, String tempPhto) {
		super();
		this.recipe_ingredients = recipe_ingredients;
		this.ingredient_amount = ingredient_amount;
		this.units = units;
		this.recipe_steps = recipe_steps;
		this.tempPhto = tempPhto;
	}
	
	public String getTempPhto() {
		return tempPhto;
	}

	public void setTempPhto(String tempPhto) {
		this.tempPhto = tempPhto;
	}
	
	
	public String getIngredients_str() {
		return ingredients_str;
	}

	public void setIngredients_str(String ingredients_str) {
		this.ingredients_str = ingredients_str;
	}

	public String[] getRecipe_ingredients() {
		return recipe_ingredients;
	}

	public void setRecipe_ingredients(String[] recipe_ingredients) {
		this.recipe_ingredients = recipe_ingredients;
	}

	public String[] getIngredient_amount() {
		return ingredient_amount;
	}

	public void setIngredient_amount(String[] ingredient_amount) {
		this.ingredient_amount = ingredient_amount;
	}

	public String[] getUnits() {
		return units;
	}

	public void setUnits(String[] units) {
		this.units = units;
	}

	public String[] getRecipe_steps() {
		return recipe_steps;
	}

	public void setRecipe_steps(String[] recipe_steps) {
		this.recipe_steps = recipe_steps;
	}

	@Override
	public String toString() {
		return "RecipeVO_saved [recipe_ingredients=" + Arrays.toString(recipe_ingredients) + ", ingredient_amount="
				+ Arrays.toString(ingredient_amount) + ", units=" + Arrays.toString(units) + ", recipe_steps="
				+ Arrays.toString(recipe_steps) + ", ingredients_str=" + ingredients_str
				+ ", getRecipe_content()=" + getRecipe_content() + ", getRecipe_id()=" + getRecipe_id()
				+ ", getRcstyle_no()=" + getRcstyle_no() + ", getMember_id()=" + getMember_id() + ", getRecipe_name()="
				+ getRecipe_name() + ", getRecipe_type()=" + getRecipe_type() + ", getRecipe_status()="
				+ getRecipe_status() + ", getRefollow_num()=" + getRefollow_num() + ", getRecipe_uldate()="
				+ getRecipe_uldate() + ", getRecipe_ingredient()=" + getRecipe_ingredient() + ", getRecipe_step()="
				+ getRecipe_step() + ", getCook_time()=" + getCook_time()
				+ ", getCalo_intake()=" + getCalo_intake() + ", getSalt_intake()=" + getSalt_intake()
				+ ", getProtein_intake()=" + getProtein_intake() + ", getFat_intake()=" + getFat_intake()
				+ ", getCarbo_intake()=" + getCarbo_intake() + ", getVitamin_b()=" + getVitamin_b()
				+ ", getVitamin_c()=" + getVitamin_c() + ", getVage_intake()=" + getVage_intake() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}


	

}
