package android.com.recipe.model;

import java.sql.Connection;
import java.util.List;

public interface RecipeDAO_interface {
	RecipeVO findByPrimaryKey(String recipe_id);
	List<RecipeVO> findByStyle(String rcstyle_no);
	List<RecipeVO> findByType(String recipe_type);
	List<RecipeVO> getAll();
	List<RecipeVO_A> getAllWithPrice();
	String getContentByPrimaryKey(String recipe_id, Connection con);
	String getImage(String recipe_id);
}
