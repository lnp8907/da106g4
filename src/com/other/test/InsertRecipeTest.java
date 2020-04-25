package com.other.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ingredient.model.IngredientDAO;
import com.ingredient.model.IngredientVO;
import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
import com.recipe.model.RecipeVO_saved;

/**
 * Servlet implementation class InsertRecipeTest
 */
@MultipartConfig

public class InsertRecipeTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String recipe_name = req.getParameter("recipe_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (recipe_name == null || recipe_name.trim().length() == 0) {
				errorMsgs.add("食譜名稱: 請勿空白");
			} else if (!recipe_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食譜名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String member_id = req.getParameter("member_id");
			String member_idReg = "^[0-9]{6}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("廚師編號: 請勿空白");
			} else if (!member_id.trim().matches(member_idReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("廚師編號: 只能是數字, 且長度必需為6");
			}

			Part photo = req.getPart("recipe_photo");

			String recipe_photo = "";
			if (photo.getSize() == 0) {
				errorMsgs.add("請上傳圖片");
			} else {
				recipe_photo = getPictureClob(photo);
			}

			String recipe_content = req.getParameter("recipe_content");
			if (recipe_content == null ||recipe_content.trim().length() == 0) {
				errorMsgs.add("請填寫食譜簡介");
			}

			String recipe_type = req.getParameter("recipe_type").trim();
			if (recipe_type == null || "請選擇".equals(recipe_type) || recipe_type.trim().length() == 0) {
				errorMsgs.add("請選擇食譜類型");
			}

			String rcstyle_no = req.getParameter("rcstyle_no").trim();
			if (rcstyle_no == null || rcstyle_no.trim().length() == 0) {
				errorMsgs.add("請選擇食譜風格");
			}

			Integer cook_time = Integer.valueOf(req.getParameter("cook_time").trim());
			if (cook_time == null || cook_time == 0) {
				errorMsgs.add("請選擇烹飪時間");
			}

			String[] recipe_ingredients = req.getParameterValues("recipe_ingredient");
			if (recipe_ingredients == null) {
				errorMsgs.add("請填寫食材");
			}
			for (int i = 0; i < recipe_ingredients.length; i++) {
				if (recipe_ingredients[i] == null || recipe_ingredients[i].trim().length() == 0) {
					errorMsgs.add("食材欄位不得為空或刪除多餘的食材欄位");
					break;
				}
			}

			
			//判斷食材數量選填
			String[] ingredient_amount = req.getParameterValues("ingredient_amount");
			String[] units = req.getParameterValues("unit");
			
			if (ingredient_amount == null) {
				errorMsgs.add("請填寫食材數量");
			}
			String ingredient_amountReg = "^[0-9]+(.[0-9]{0,2})?$";
			for (int i = 0; i < ingredient_amount.length; i++) {
				if ((ingredient_amount[i] == "" || ingredient_amount[i].trim().length() == 0 ) && (units[i].equals("0.01,適量") || units[i].equals("0.005,少許") )) {
					ingredient_amount[i] = "1";
					continue;
				} else if (!ingredient_amount[i].trim().matches(ingredient_amountReg)
						|| ingredient_amount[i].equals("0")) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("數量: 只能是數字, 且小數點不可超過二位數也不得為0");
					break;
				}
			}

			if (units == null) {
				errorMsgs.add("請填寫食材單位");
			}
			for (int i = 0; i < units.length; i++) {
				if (units[i] == "" || units[i].trim().length() == 0) {
					errorMsgs.add("請選擇單位");
					break;
				}
			}

			// ***************************食譜步驟*********************************//
			String[] recipe_steps = req.getParameterValues("recipe_step");
			if (recipe_steps == null) {
				errorMsgs.add("請填寫料理步驟");
			}
			StringBuilder recipe_step = new StringBuilder();

			for (int i = 0; i < recipe_steps.length; i++) {
				if (recipe_steps[i] == null || recipe_steps[i].trim().length() == 0) {
					errorMsgs.add("步驟欄不得為空,或刪除多餘的步驟欄位");
					break;
				} else {
					recipe_step.append(recipe_steps[i] + "*");
				}
			}

			// **********************食材串接*******************************///
			// 食材字串切割
			StringBuilder recipe_ingredient = new StringBuilder();
			String unitStr = "";
			String[] unitContainer = new String[(recipe_ingredients.length)*2];
			String[] unitNum = new String[recipe_ingredients.length];
			String[] unitName = new String[recipe_ingredients.length];
			if (errorMsgs.isEmpty()) {
				for (int i = 0; i < units.length; i++) {
					int a = 0;
					unitStr = units[i];
					unitContainer = unitStr.split(",");
					unitNum[i] = unitContainer[a];
					unitName[i] = unitContainer[++a];
					// 順便串起食材內容
					recipe_ingredient.append(recipe_ingredients[i] + ":" + ingredient_amount[i] + unitName[i] + ",");
					a++;
				}
			}

			// *******************連接食材庫機算營養含量***********************///
			Double carbo_intake = 0.0;
			Double protein_intake = 0.0;
			Double fat_intake = 0.0;
			Double calo_intake = 0.0;
			Double vitamin_b = 0.0;
			Double vitamin_c = 0.0;
			Double salt_intake = 0.0;
			Double vage_intake = 0.0;

			// 計算食譜營養量
			IngredientDAO dao = new IngredientDAO();
			IngredientVO ingredientVO = null;
			String ingredientName = "";
			if (errorMsgs.isEmpty()) {
				try {
				for (int i = 0; i < recipe_ingredients.length; i++) {
					ingredientName = recipe_ingredients[i];
					ingredientVO = dao.getIntakeByName(recipe_ingredients[i]);
					carbo_intake += ingredientVO.getCarbohydrate() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					protein_intake += ingredientVO.getProtein() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					fat_intake += ingredientVO.getFat() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					calo_intake += ingredientVO.getCalorie() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					vitamin_b += ingredientVO.getVitamin_B() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					vitamin_c += ingredientVO.getVitamin_C() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					salt_intake += ingredientVO.getSalt() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
					vage_intake += ingredientVO.getVgetable() * Double.valueOf(ingredient_amount[i])
							* Double.valueOf(unitNum[i]);
				}
				DecimalFormat df = new java.text.DecimalFormat("#.##");
				carbo_intake = Double.valueOf(df.format(carbo_intake));
				protein_intake = Double.valueOf(df.format(protein_intake));
				fat_intake = Double.valueOf(df.format(fat_intake));
				calo_intake = Double.valueOf(df.format(calo_intake));
				vitamin_b = Double.valueOf(df.format(vitamin_b));
				vitamin_c = Double.valueOf(df.format(vitamin_c));
				salt_intake = Double.valueOf(df.format(salt_intake)) * 0.001;
				vage_intake = Double.valueOf(df.format(vage_intake));
				}catch (Exception e) {
					errorMsgs.add( "本平台尚未提供[" + ingredientName + "],請更換食材");
				}
			}

			RecipeVO recipeVO = new RecipeVO();
			recipeVO.setRcstyle_no(rcstyle_no);
			recipeVO.setMember_id(member_id);
			recipeVO.setRecipe_name(recipe_name);
			recipeVO.setRecipe_type(recipe_type);
			recipeVO.setRecipe_photo(recipe_photo);
			recipeVO.setRecipe_content(recipe_content);
			recipeVO.setRecipe_step(recipe_step.toString());
			recipeVO.setRecipe_ingredient(recipe_ingredient.toString());
			recipeVO.setCook_time(cook_time); // 烹煮時間
			recipeVO.setCalo_intake(calo_intake); // 卡洛里
			recipeVO.setSalt_intake(salt_intake); // 食鹽
			recipeVO.setProtein_intake(protein_intake); // 蛋白質 たんぱく質
			recipeVO.setFat_intake(fat_intake); // 脂質
			recipeVO.setCarbo_intake(carbo_intake); // 碳水化物
			recipeVO.setVitamin_b(vitamin_b); // 維他命B ビタミンB
			recipeVO.setVitamin_c(vitamin_c); // 維他命C ビタミンC
			recipeVO.setVage_intake(vage_intake); // 食物繊維

//			
//			RecipeVO_saved recipeSaved = new RecipeVO_saved(recipe_photo,recipe_ingredients, ingredient_amount, units,
//					recipe_steps);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的物件,也存入req
//				req.setAttribute("recipeSaved", recipeSaved);
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/addRecipe.jsp");
				failureView.forward(req, res);
				System.out.println(recipeVO);
				return;
				
				
			}

			/*************************** 2.開始新增資料 ***************************************/
			
			
			
			
			
			RecipeService RecSvc = new RecipeService();
			recipeVO = RecSvc.addRecipe(rcstyle_no, member_id, recipe_name, recipe_type, recipe_photo, recipe_content,
					recipe_step.toString(), recipe_ingredient.toString(), cook_time, calo_intake, salt_intake,
					protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake);
			System.out.println(recipe_ingredient);
			System.out.println(ingredientVO);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// String url = "/emp/listAllEmp.jsp";
			// RequestDispatcher successView = req.getRequestDispatcher(url); //
			// 新增成功後轉交listAllEmp.jsp
			// successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/addRecipe.jsp");
//				failureView.forward(req, res);
//			}
		} /*******************************
			 * END OF INSERT
			 ****************************************/
	}

	/*******************************
	 * END OF DOPOST
	 ****************************************/

	// 將Part位元資料轉為BASE
	public static String getPictureClob(Part part) throws IOException {
		@SuppressWarnings("unused")
		final String recipe_photo;
		InputStream in = part.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Base64.Encoder encoder = Base64.getEncoder();
		String base64Image = "data:image/jpeg;base64,";

		byte[] buffer = new byte[8192];
		int a;
		while ((a = in.read(buffer)) != -1) {
			baos.write(buffer, 0, a);
		}
		baos.close();
		in.close();

		final String picCode = encoder.encodeToString(baos.toByteArray());// 將圖片陣列編碼成BASE64
		return recipe_photo = base64Image + picCode;
	}
}
