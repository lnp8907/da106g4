package com.recipe_style.model;

import java.util.List;


public class RecipeStyleService {

	private RecipeStyleDAO_interface dao;

	public RecipeStyleService() {
		dao = new RecipeStyleDAO();
	}

	public List<RecipeStyleVO> getAll() {
		return dao.getAll();
	}

	public RecipeStyleVO getOneReStyle(String rcstyle_no) {
		return dao.findByPrimaryKey(rcstyle_no);
	}

	public void deleteReStyle(String rcstyle_no) {
		dao.delete(rcstyle_no);
	}

	public RecipeStyleVO insert(String rcstyle) {

		RecipeStyleVO recipeStyleVO = new RecipeStyleVO();
		recipeStyleVO.setRcstyle(rcstyle);

		dao.insert(recipeStyleVO);

		return recipeStyleVO;
	}

	public RecipeStyleVO update(String rcstyle_no,String rcstyle) {
		RecipeStyleVO recipeStyleVO = new RecipeStyleVO();
		recipeStyleVO.setRcstyle_no(rcstyle_no);
		recipeStyleVO.setRcstyle(rcstyle);
		dao.update(recipeStyleVO);

		return recipeStyleVO;

	}

}
