package com.product.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;




public class ProductService {
	private ProductDAO_interface dao;

	public ProductService() {
	
		dao=new ProductJDBCDAO();
	}
	public ProductVO getbyreceipe(String recipe_id) {
		return dao.byRecipe(recipe_id);
	}
		
		
		
		
		
		
	
	public void updatepicture(ProductVO productVO) {
		dao.updatepicture(productVO);
	}
	public void addReceipe(String recipe_id) {
		ProductVO productVO=new ProductVO();

		productVO.setRecipe_id(recipe_id);
		
		dao.addRecipe(productVO);
		
		
		
		
		
	}
	public ProductVO insert(String product_type, String product_name,
			Integer product_price, byte[] product_photo, Integer product_status, Double carbohydrate, Double protein,
			Double fat, Double calorie, Double vitamin_B, Double vitamin_C, Double salt, Double vagetbale,
			String content) {
		ProductVO productVO=new ProductVO();
		productVO.setProduct_type(product_type);
		productVO.setProduct_name(product_name);
		productVO.setProduct_price(product_price);
		productVO.setProduct_photo(product_photo);
		productVO.setProduct_status(product_status);
		productVO.setCarbohydrate(carbohydrate);
		productVO.setProtein(protein);
		productVO.setFat(fat);
		productVO.setCalorie(calorie);
		productVO.setVitamin_B(vitamin_B);
		productVO.setVitamin_C(vitamin_C);
		productVO.setContent(content);
		productVO.setSalt(salt);
		productVO.setVagetbale(vagetbale);
	dao.insert(productVO);
		return productVO;
		
		
		
		
	}
	public ProductVO update(String product_id,String product_type, String product_name,
			Integer product_price, byte[] product_photo, Integer product_status, Double carbohydrate, Double protein,
			Double fat, Double calorie, Double vitamin_B, Double vitamin_C, Double salt, Double vagetbale,
			String content) {
		ProductVO productVO=new ProductVO();
		productVO.setProduct_id(product_id);
		productVO.setProduct_type(product_type);
		productVO.setProduct_name(product_name);
		productVO.setProduct_price(product_price);
		productVO.setProduct_photo(product_photo);
		productVO.setProduct_status(product_status);
		productVO.setCarbohydrate(carbohydrate);
		productVO.setProtein(protein);
		productVO.setFat(fat);
		productVO.setCalorie(calorie);
		productVO.setVitamin_B(vitamin_B);
		productVO.setVitamin_C(vitamin_C);
		productVO.setContent(content);
		productVO.setSalt(salt);
		productVO.setVagetbale(vagetbale);
		
		dao.update(productVO);
		
		return productVO;
	}
	public void delete(String product_id) {
		dao.delete(product_id);
	}
	//獲取所有商品
	
	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	//前端{狀態預設為0已上架}
    public List<ProductVO> getAll_ZERO(Map<String, String[]> map){
    	return dao.getAll(map,0);
    }


	public ProductVO getOneProduct(String product_id) {
		return dao.findByPrimaryKey(product_id);
	}
	public boolean isProduct_idFK(String product_id) {
		return dao.isproductid(product_id);
		
	}
	/*獲取食譜清單*/
	public List<ProductVO> gettypelist(String product_type)  {
		return dao.gettypelist(product_type);
	}
	public List<ProductVO> gettypelist(String product_type,Integer product_status)  {
		return dao.gettypelist(product_type,0);
	}
	/*獲取所有商品商品除了食譜*/
	public Set<ProductVO> getAllProduct() {
		return dao.getAllExceprRecipe();
	}
	public Set<ProductVO> getAllProduct(Integer product_status) {
		return dao.getAllExceprRecipe(product_status);
	}
	/*獲取LIST長度*/
	public Integer getsize(Collection<?> list) {
		return dao.getlistzize(list);
		
		
	}
	/*更改狀態*/
	public void changestatus(String product_id,Integer product_status) {
		dao.changestatus(product_id, product_status);
	}

}
