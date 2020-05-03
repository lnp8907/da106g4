package com.recipe.model;

import java.util.List;
import java.util.Map;

import com.order_detail.model.Order_detailVO;
import com.product.model.ProductVO;
import com.shop_order.model.Shop_orderVO;

public interface RecipeDAO_interface {
    public void insert(RecipeVO recipeVO);
    public void update(RecipeVO recipeVO);
    public void delete(String recipe_id);
    public void changeStatus(String recipe_id,Integer recipe_status);
    public RecipeVO findByPrimaryKey(String recipe_id);
    public Integer getChefCookedNum(String member_id); //廚師製作的食譜數量
    public RecipeVO_saved findByPrimaryKeyForSaved(String recipe_id);
    public List<RecipeVO> getAll();
    public List<RecipeVO> getCheck();
    public Integer getManageNum();
    public List<RecipeVO_saved> getAllForFrontEnd();
    public List<RecipeVO> getChefCooked(String member_id); //廚師製作過的食譜
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<RecipeVO_saved> getAllWithTerm(Map<String, String[]> map , String orderBy); 
    public RecipeVO getLatest();
    public RecipeVO getMostPopular();
    public Integer updateFollowNum(String recipe_id,Integer followNum);
    //複合主鍵新增
    public void insert(RecipeVO recipeVO,ProductVO productVO);
}
