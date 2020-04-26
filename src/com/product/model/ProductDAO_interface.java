package com.product.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.recipe.model.RecipeVO;


public interface ProductDAO_interface {
	


	
	/*新增商品*/
	public void addRecipe(ProductVO productvo);
	/*獲取由編號*/
	public ProductVO byRecipe(String recipe_id);
	
    public void insert(ProductVO productvo);
    public void update(ProductVO productvo);
    public void delete(String product_id);
    public ProductVO findByPrimaryKey(String product_id);
    public List<ProductVO> getAll();
    
  //搜尋
    public List<ProductVO> getAll(Map<String, String[]> map,Integer product_status); 

    
    public void updatepicture(ProductVO productVO);
    //改變狀態
    public void changestatus(String product_id, Integer product_status);
    //判斷是否參照
	public boolean isproductid(String productid) ;
	//獲得TYPE陣列
	public List<ProductVO> gettypelist(String product_type);
	public List<ProductVO> gettypelist(String product_type,Integer product_status);

	//獲得食譜列表
    public Set<ProductVO> GetRecripeList();
    public Set<ProductVO> GetRecripeList(Integer product_status);

    //獲取除食譜外的商品
    public Set<ProductVO> getAllExceprRecipe();
    public Set<ProductVO> getAllExceprRecipe(Integer product_status);

    //獲得總長度
    public Integer getlistzize(Collection list);


}
