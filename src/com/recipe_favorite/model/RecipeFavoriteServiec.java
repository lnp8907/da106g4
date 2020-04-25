package com.recipe_favorite.model;

import java.util.List;

public class RecipeFavoriteServiec {
	private RecipeFavoriteDAO_interface dao;

	public RecipeFavoriteServiec() {
		dao = new RecipeFavoriteDAO();
	}

	public RecipeFavoriteVO insert(String recipe_id, String member_id) {
		RecipeFavoriteVO recipeFavoriteVO = new RecipeFavoriteVO();
		recipeFavoriteVO.setRecipe_id(recipe_id);
		recipeFavoriteVO.setMember_id(member_id);
		dao.insert(recipeFavoriteVO);

		return recipeFavoriteVO;
	}

	public void delete(String recipe_id, String member_id) {
		dao.delete(recipe_id, member_id);
	}

	public Integer getFollowedNum(String recipe_id) {

		return dao.getFollowedNum(recipe_id);

	}

	public List<RecipeFavoriteVO> findFollowedByPrimaryKey(String recipe_id) {
		return dao.findFollowedByPrimaryKey(recipe_id);
	}

	public List<RecipeFavoriteVO> findFollowingByPrimaryKey(String member_id) {
		return dao.findFollowingByPrimaryKey(member_id);

	}

	public List<RecipeFavoriteVO> getAll() {
		return dao.getAll();

	}

}
