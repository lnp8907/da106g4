package com.recipe_favorite.model;

import java.util.List;

public interface RecipeFavoriteDAO_interface {
    public void insert(RecipeFavoriteVO recipeFavoriteVO);
    public void delete(String recipe_id,String member_id);
    public Integer getFollowedNum(String recipe_id);
    public List<RecipeFavoriteVO> findFollowedByPrimaryKey(String recipe_id);
    public List<RecipeFavoriteVO> findFollowingByPrimaryKey(String member_id);
    public List<RecipeFavoriteVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RecipeVO> getAll(Map<String, String[]> map); 

}
