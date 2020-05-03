package com.recipe_order_details.model;

import java.sql.Connection;
import java.util.List;

public interface RecipeOrderDetailsDAO_interface {
    public void insertWithOrder(RecipeOrderDetailsVO recipeOrderDetailsVO, Connection con);
//    public void update(RecipeOrderDetailsVO recipeOrderDetailsVO, RecipeOrderDetailsVO recipeOrderDetailsVO_1);
//    public void delete(String ido_no, String product_id);
    public List<RecipeOrderDetailsVO> findByPrimaryKey_IDO_no(String ido_no);
//    public List<RecipeOrderDetailsVO> findByPrimaryKey_Product_id(String product_id);
//    public List<RecipeOrderDetailsVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 
}
