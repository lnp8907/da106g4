package com.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.Part;
import com.recipe.model.RecipeVO_saved;

public class Utility {
	
	//將字串切完後存回
	public static RecipeVO_saved getSplitedIngAndStep(RecipeVO_saved recipeVO) {

		if (recipeVO == null) {
			recipeVO = new RecipeVO_saved();
		}

		String[] tokens = recipeVO.getRecipe_ingredient().split(",");
		String[] unitContainer = new String[(tokens.length) * 2];
		String[] unitContainer2 = new String[(tokens.length) * 2];
		String[] ingredientName = new String[tokens.length];
		String[] ingredientNums = new String[tokens.length];// 多個數量名稱
		String[] ingredientNum = new String[tokens.length];
		String[] ingredientUnit = new String[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			int a = 0, b = 0;
			unitContainer = tokens[i].split(":");
			ingredientName[i] = unitContainer[a];
			ingredientNums[i] = unitContainer[++a];
			unitContainer2 = ingredientNums[i].split("/");
			ingredientNum[i] = unitContainer2[b];
			ingredientUnit[i] = unitContainer2[++b];
			a++;
			b++;
		}
		// 字串切割步驟
		String[] recipe_steps = recipeVO.getRecipe_step().split("/");

		// 將切割好的字串存入延伸的VO
		recipeVO.setRecipe_ingredients(ingredientName);
		recipeVO.setIngredient_amount(ingredientNum);
		recipeVO.setUnits(ingredientUnit);
		recipeVO.setRecipe_steps(recipe_steps);

		return recipeVO;
	}

	// 將Part位元資料轉為BASE
	public static String getPictureClob(Part part) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Base64.Encoder encoder = Base64.getEncoder();
		String base64Image = "data:image/jpeg;base64,";
		String picCode = "";
		InputStream in;
		try {
			in = part.getInputStream();
			byte[] buffer = new byte[8192];
			int a;
			while ((a = in.read(buffer)) != -1) {
				baos.write(buffer, 0, a);
			}
			picCode = encoder.encodeToString(baos.toByteArray());// 將圖片陣列編碼成BASE64
			baos.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64Image + picCode;
	}
}
