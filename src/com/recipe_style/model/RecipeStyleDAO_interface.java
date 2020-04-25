package com.recipe_style.model;

import java.util.*;

public interface RecipeStyleDAO_interface {
    public void insert(RecipeStyleVO recipeStyleVO);
    public void update(RecipeStyleVO recipeStyleVO);
    public void delete(String rcstyle_no);
    public RecipeStyleVO findByPrimaryKey(String rcstyle_no);
    public List<RecipeStyleVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 

}
