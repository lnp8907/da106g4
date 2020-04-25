package com.ingredient.model;

import java.util.List;

public interface IngredientDAO_interface {
    public void insert(IngredientVO ingredientVO);
    public void update(IngredientVO ingredientVO);
    public void delete(String ingredient_id);
    public IngredientVO findByPrimaryKey(String ingredient_id);
    public IngredientVO getIntakeByName(String ingredient_name);
    public List<IngredientVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 

}
