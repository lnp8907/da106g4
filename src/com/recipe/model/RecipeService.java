package com.recipe.model;

import java.util.List;
import java.util.Map;

import com.order_detail.model.Order_detailVO;
import com.product.model.ProductVO;

public class RecipeService {

	private RecipeDAO_interface dao;

	
	
	public RecipeService() {
		dao = new RecipeDAO();
	}
	
    public RecipeVO getLatest() {
    	System.out.println("Service");
    	return dao.getLatest();
    }
    public RecipeVO getMostPopular() {
    	return dao.getMostPopular();
    }
    public Integer updateFollowNum(String recipe_id,Integer followNum) {
    	return dao.updateFollowNum(recipe_id, followNum);
    }
    public List<RecipeVO> getCheck(){
    	return dao.getCheck();
    }
    public Integer getManageNum() {
    	return dao.getManageNum();
    }
	
	public List<RecipeVO_saved> getAllWithTerm(Map<String, String[]> map, String orderBy) {

		return dao.getAllWithTerm(map, orderBy);
	}

	public Integer getChefCookedNum(String member_id) {

		return dao.getChefCookedNum(member_id);
	}

	public List<RecipeVO> getChefCooked(String member_id) {
		return dao.getChefCooked(member_id);
	}

	public RecipeVO addRecipe(String rcstyle_no, String member_id, String recipe_name, String recipe_type,
			String recipe_photo, String recipe_content, String recipe_step, String recipe_ingredient, Integer cook_time,
			Double calo_intake, Double salt_intake, Double protein_intake, Double fat_intake, Double carbo_intake,
			Double vitamin_b, Double vitamin_c, Double vage_intake) {

		RecipeVO recipeVO = new RecipeVO();
		recipeVO.setRcstyle_no(rcstyle_no);
		recipeVO.setMember_id(member_id);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_type(recipe_type);
		recipeVO.setRecipe_photo(recipe_photo);
		recipeVO.setRecipe_content(recipe_content);
		recipeVO.setRecipe_step(recipe_step);
		recipeVO.setRecipe_ingredient(recipe_ingredient);
		recipeVO.setCook_time(cook_time); // 烹煮時間
		recipeVO.setCalo_intake(calo_intake); // 卡洛里
		recipeVO.setSalt_intake(salt_intake); // 食鹽
		recipeVO.setProtein_intake(protein_intake); // 蛋白質 たんぱく質
		recipeVO.setFat_intake(fat_intake); // 脂質
		recipeVO.setCarbo_intake(carbo_intake); // 碳水化物
		recipeVO.setVitamin_b(vitamin_b); // 維他命B ビタミンB
		recipeVO.setVitamin_c(vitamin_c); // 維他命C ビタミンC
		recipeVO.setVage_intake(vage_intake); // 食物繊維
		
		dao.insert(recipeVO);

		return recipeVO;
	}
	
	
	public void addRecipe(RecipeVO recipeVO,ProductVO productVO) {
		dao.insert(recipeVO, productVO);
	}

	public RecipeVO updateRecipe(String recipe_id, String rcstyle_no, String member_id, String recipe_name,
			String recipe_type, String recipe_photo, String recipe_content, String recipe_step,
			String recipe_ingredient, Integer cook_time, Double calo_intake, Double salt_intake, Double protein_intake,
			Double fat_intake, Double carbo_intake, Double vitamin_b, Double vitamin_c, Double vage_intake) {

		RecipeVO recipeVO = new RecipeVO();
		recipeVO.setRecipe_id(recipe_id);
		recipeVO.setRcstyle_no(rcstyle_no);
		recipeVO.setMember_id(member_id);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_type(recipe_type);
		recipeVO.setRecipe_photo(recipe_photo);
		recipeVO.setRecipe_content(recipe_content);
		recipeVO.setRecipe_step(recipe_step);
		recipeVO.setRecipe_ingredient(recipe_ingredient);
		recipeVO.setCook_time(cook_time); // 烹煮時間
		recipeVO.setCalo_intake(calo_intake); // 卡洛里
		recipeVO.setSalt_intake(salt_intake); // 食鹽
		recipeVO.setProtein_intake(protein_intake); // 蛋白質 たんぱく質
		recipeVO.setFat_intake(fat_intake); // 脂質
		recipeVO.setCarbo_intake(carbo_intake); // 碳水化物
		recipeVO.setVitamin_b(vitamin_b); // 維他命B ビタミンB
		recipeVO.setVitamin_c(vitamin_c); // 維他命C ビタミンC
		recipeVO.setVage_intake(vage_intake); // 食物繊維
		dao.update(recipeVO);
		return recipeVO;
	}

//
	public void changeStatus(String recipe_id,Integer recipe_status) {
		dao.changeStatus(recipe_id, recipe_status);
	}

	
	public void deleteRecipe(String recipe_id) {
		dao.delete(recipe_id);
	}

	public RecipeVO getOneRecipe(String recipe_id) {
		return dao.findByPrimaryKey(recipe_id);
	}

	public RecipeVO_saved findByPrimaryKeyForSaved(String recipe_id) {
		return dao.findByPrimaryKeyForSaved(recipe_id);
	}

	public List<RecipeVO> getAll() {
		return dao.getAll();
	}

	public List<RecipeVO_saved> getAllForFrontEnd() {

		return dao.getAllForFrontEnd();
	}

}
