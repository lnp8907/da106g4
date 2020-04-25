package com.other.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingredient.model.IngredientDAO;
import com.ingredient.model.IngredientVO;

public class SplitTest extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 模擬getParameter
		String[] recipe_ingredients = { "高麗菜", "苦瓜", "茄子", "李子" };
		String[] ingredient_amount = { "200", "1", "1", "2" };
		String[] units = { "0.01,公克", "0.0125,大匙", "0.005,少許", "0.045,茶匙" };

		StringBuilder recipe_ingredient = new StringBuilder();

		// Split 將數量和名稱切分開
		String unitStr = "";
		String[] unitContainer;
		String[] unitNum = new String[units.length];
		String[] unitName = new String[units.length];
		for (int i = 0; i < units.length; i++) {
			int a = 0;
			unitStr = units[i];
			unitContainer = unitStr.split(",");
			unitNum[i] = unitContainer[a];
			unitName[i] = unitContainer[a + 1];
			a++;
		// 順便串起食材內容
			recipe_ingredient.append(recipe_ingredients[i] + ":" + ingredient_amount[i] + unitName[i] + ",");
		}

		Double carbo_intake = 0.0;
		Double protein_intake = 0.0;
		Double fat_intake = 0.0;
		Double calo_intake = 0.0;
		Double vitamin_b = 0.0;
		Double vitamin_c = 0.0;
		Double salt_intake = 0.0;
		Double vage_intake = 0.0;

		//計算食譜營養量
		IngredientDAO dao = new IngredientDAO();
		IngredientVO ingredientVO = null;
		for (int i = 0; i < recipe_ingredients.length; i++) {
			ingredientVO = dao.getIntakeByName(recipe_ingredients[i]);
			carbo_intake += ingredientVO.getCarbohydrate()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			protein_intake += ingredientVO.getProtein()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			fat_intake += ingredientVO.getFat()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			calo_intake += ingredientVO.getCalorie()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			vitamin_b += ingredientVO.getVitamin_B()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			vitamin_c += ingredientVO.getVitamin_C()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			salt_intake += ingredientVO.getSalt()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
			vage_intake += ingredientVO.getVgetable()*Double.valueOf(ingredient_amount[i])*Double.valueOf(unitNum[i]);
		}
		
		System.out.println(ingredientVO);
	
	}
}
