package com.recipe_browsing_history.model;

import java.util.List;

import com.recipe_favorite.model.RecipeFavoriteVO;

public class Recipe_browing_historyService {
	private Recipe_browing_historyDAO_interface dao;

	public Recipe_browing_historyService() {
		
		dao=new Recipe_browing_historyDAO();
	}

	public Recipe_browing_historyVO insert(String member_id, String recipe_id) {
		System.out.println("啟動服務準備新增");

		Recipe_browing_historyVO VO = new Recipe_browing_historyVO();
		VO.setMember_id(member_id);
		VO.setRecipe_id(recipe_id);
		
		dao.insert(VO);
		return VO;
	}
	public void removelist(String member_id,String recipe_id) {
		System.out.println("用於移除");
		Recipe_browing_historyVO VO = new Recipe_browing_historyVO();
		VO.setMember_id(member_id);
		VO.setRecipe_id(recipe_id);
		dao.delete(VO);
	}
	
	public void deletelist(String member_id) {
		System.out.println("用於刪除紀錄");
	
		dao.deleteAll(member_id);
	}
		
	
	
	
	
	
	public List<Recipe_browing_historyVO> findFollowingByPrimaryKey(String member_id) {
		return dao.findFollowingByPrimaryKey(member_id);

	}
	
	
	
	
	
	
	
	
	
	
	
	

}
