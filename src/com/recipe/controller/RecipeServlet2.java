package com.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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

public class RecipeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String recipe_id = "";
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				recipe_id = req.getParameter("recipe_id");
				String recipeId_idReg = "^[0-9]{6}$";
				if (recipe_id == null || recipe_id.trim().length() == 0) {
					errorMsgs.add("食譜編號: 請勿空白");
				} else if (!recipe_id.trim().matches(recipeId_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("食譜編號: 只能是數字, 且長度為6");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RecipeService RecipeSvc = new RecipeService();
				RecipeVO_saved recipeVO = RecipeSvc.findByPrimaryKeyForSaved(recipe_id);
				if (recipeVO == null) {
					errorMsgs.add("查無資料");
				}else {					
					recipeVO = getSplitedIngAndStep(recipeVO, recipeVO.getRecipe_ingredient(), recipeVO.getRecipe_step());
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/************************** 開始字串切割 ********************************************/

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req

				String url = "/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ****************************************************************//
		// ******************************修改資料****************************//
		// ***************************************************************//

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String recipe_id = new String(req.getParameter("recipe_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RecipeService RecipeSvc = new RecipeService();
				RecipeVO_saved recipeVO = RecipeSvc.findByPrimaryKeyForSaved(recipe_id);
				recipeVO = getSplitedIngAndStep(recipeVO, recipeVO.getRecipe_ingredient(), recipeVO.getRecipe_step());

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
				String url = "/recipe/update_recipe_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}

		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String recipe_id = req.getParameter("recipe_id");
				Date recipe_uldate = java.sql.Date.valueOf(req.getParameter("recipe_uldate").trim());
				Integer refollow_num = Integer.valueOf(req.getParameter("refollow_num"));

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
				String recipe_photo = req.getParameter("temp_photo");
				if (!(photo == null || photo.getSize() == 0)) {
					recipe_photo = getPictureClob(photo);
				}

				String recipe_content = req.getParameter("recipe_content");
				if (recipe_content == null || recipe_content.trim().length() == 0) {
					errorMsgs.add("請填寫食譜簡介");
				}

				String recipe_type = req.getParameter("recipe_type").trim();
				if (recipe_type == null || "請選擇".equals(recipe_type)) {
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
				} else {
					for (int i = 0; i < recipe_ingredients.length; i++) {
						if (recipe_ingredients[i] == null || recipe_ingredients[i].trim().length() == 0) {
							errorMsgs.add("食材欄位不得為空或刪除多餘的食材欄位");
							break;
						}
					}
				}
				// 判斷食材數量選填
				String[] ingredient_amount = req.getParameterValues("ingredient_amount");
				String[] units = req.getParameterValues("unit");
				String ingredient_amountReg = "^[0-9]+(.[0-9]{0,2})?$";

				if (ingredient_amount == null) {
					errorMsgs.add("請填寫食材數量");
				} else {
					for (int i = 0; i < ingredient_amount.length; i++) {
						if (("".equals(ingredient_amount[i]) || ingredient_amount[i].trim().length() == 0)
								&& (units[i].equals("0.025,適量") || units[i].equals("0.0125,少許"))) {
							ingredient_amount[i] = "1";
							continue;
						} else if (!ingredient_amount[i].trim().matches(ingredient_amountReg)
								|| ingredient_amount[i].equals("0")) { // 以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("數量: 只能是數字, 且小數點不可超過二位數也不得為0");
							break;
						}
					}
				}

				if (units == null) {
					errorMsgs.add("請填寫食材單位");
				} else {
					for (int i = 0; i < units.length; i++) {
						if ("選擇單位".equals(units[i]) || units[i].trim().length() == 0) {
							errorMsgs.add("請選擇單位");
							break;
						}
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
						recipe_step.append(recipe_steps[i] + "/");
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

				IngredientDAO dao = new IngredientDAO();
				IngredientVO ingredientVO = null;

				String ingredientNames = "";
				StringBuilder recipe_ingredient = new StringBuilder();

				// ************************開始各種處理***************************//

				if (errorMsgs.isEmpty()) {

					String unitStr = "";
					String[] unitContainer = new String[(recipe_ingredients.length) * 2];
					String[] unitNum = new String[recipe_ingredients.length];
					String[] unitName = new String[recipe_ingredients.length];
					for (int i = 0; i < units.length; i++) {
						int a = 0;
						unitStr = units[i];
						unitContainer = unitStr.split(",");
						unitNum[i] = unitContainer[a];
						unitName[i] = unitContainer[++a];
						// 順便串起食材內容
						recipe_ingredient
								.append(recipe_ingredients[i] + ":" + ingredient_amount[i] + "/" + unitName[i] + ",");
						a++;
					}

					// 計算食譜營養量
					try {
						for (int i = 0; i < recipe_ingredients.length; i++) {
							ingredientNames = recipe_ingredients[i];
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
						salt_intake = salt_intake * 0.001; //將mg變成g
						salt_intake = Double.valueOf(df.format(salt_intake));
						vage_intake = Double.valueOf(df.format(vage_intake));

					} catch (Exception e) {
						errorMsgs.add("本平台尚未提供[" + ingredientNames + "],請更換食材");
					}
				}

				RecipeVO recipeVO = new RecipeVO_saved();
				recipeVO.setRcstyle_no(recipe_id);
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

				RecipeVO_saved recipeSavedTemp = new RecipeVO_saved();
				if (errorMsgs.isEmpty()) {

					recipeSavedTemp = getSplitedIngAndStep(recipeSavedTemp, recipe_ingredient.toString(),
							recipe_step.toString());

				} else {
					recipeSavedTemp.setRecipe_ingredients(recipe_ingredients);
					recipeSavedTemp.setIngredient_amount(ingredient_amount);
					recipeSavedTemp.setUnits(units);
					recipeSavedTemp.setRecipe_steps(recipe_steps);
				}
				// 重組延伸的VO為了讓UPDATE PAGE可以取得RecipeVO_saved物件
				recipeSavedTemp.setRcstyle_no(recipe_id);
				recipeSavedTemp.setRcstyle_no(rcstyle_no);
				recipeSavedTemp.setMember_id(member_id);
				recipeSavedTemp.setRecipe_name(recipe_name);
				recipeSavedTemp.setRecipe_type(recipe_type);
				recipeSavedTemp.setRecipe_photo(recipe_photo);
				recipeSavedTemp.setRecipe_content(recipe_content);
				recipeSavedTemp.setRecipe_step(recipe_step.toString());
				recipeSavedTemp.setRecipe_ingredient(recipe_ingredient.toString());
				recipeSavedTemp.setCook_time(cook_time); // 烹煮時間
				recipeSavedTemp.setCalo_intake(calo_intake); // 卡洛里
				recipeSavedTemp.setSalt_intake(salt_intake); // 食鹽
				recipeSavedTemp.setProtein_intake(protein_intake); // 蛋白質 たんぱく質
				recipeSavedTemp.setFat_intake(fat_intake); // 脂質
				recipeSavedTemp.setCarbo_intake(carbo_intake); // 碳水化物
				recipeSavedTemp.setVitamin_b(vitamin_b); // 維他命B ビタミンB
				recipeSavedTemp.setVitamin_c(vitamin_c); // 維他命C ビタミンC
				recipeSavedTemp.setVage_intake(vage_intake); // 食物繊維
				recipeSavedTemp.setRecipe_uldate(recipe_uldate);
				recipeSavedTemp.setRefollow_num(refollow_num);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeSavedTemp); // 含有輸入格式錯誤的物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe/update_recipe_input.jsp");
					failureView.forward(req, res);
					return;// 中斷程式

				}

				/*************************** 2.開始修改資料 ***************************************/
				RecipeService RecSvc = new RecipeService();
				recipeVO = RecSvc.updateRecipe(recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_photo,
						recipe_content, recipe_step.toString(), recipe_ingredient.toString(), cook_time, calo_intake,
						salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				/*** 修改完成後以延伸的VO重新進資料庫重刷頁面 ******/
				req.setAttribute("recipeVO", recipeSavedTemp);
				String url = "/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				// 新增成功後轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/update_recipe_input.jsp");
				failureView.forward(req, res);
			}
		} /*******************************
			 * END OF UPDATE
			 ****************************************/

		// ****************************************************************//
		// ******************************新增資料****************************//
		// ***************************************************************//
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
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
				String tmep_recipe_photo = req.getParameter("temp_recipe_photo");
				String recipe_photo = "";
				if (photo.getSize() == 0 && "".equals(tmep_recipe_photo)) {
					errorMsgs.add("請上傳圖片");
				} else if (photo.getSize() != 0 && "".equals(tmep_recipe_photo)) {
					recipe_photo = getPictureClob(photo);
				} else if (photo.getSize() != 0 && !("".equals(tmep_recipe_photo))) {
					recipe_photo = getPictureClob(photo);
				} else {
					recipe_photo = tmep_recipe_photo;
				}

				String recipe_content = req.getParameter("recipe_content");
				if (recipe_content == null || recipe_content.trim().length() == 0) {
					errorMsgs.add("請填寫食譜簡介");
				}

				String recipe_type = req.getParameter("recipe_type").trim();
				if (recipe_type == null || "請選擇".equals(recipe_type)) {
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
				} else {
					for (int i = 0; i < recipe_ingredients.length; i++) {
						if (recipe_ingredients[i] == null || recipe_ingredients[i].trim().length() == 0) {
							errorMsgs.add("食材欄位不得為空或刪除多餘的食材欄位");
							break;
						}
					}
				}

				// 判斷食材數量選填
				String[] ingredient_amount = req.getParameterValues("ingredient_amount");
				String[] units = req.getParameterValues("unit");
				String ingredient_amountReg = "^[0-9]+(.[0-9]{0,2})?$";

				if (ingredient_amount == null) {
					errorMsgs.add("請填寫食材數量");
				} else {
					for (int i = 0; i < ingredient_amount.length; i++) {
						if (("".equals(ingredient_amount[i]) || ingredient_amount[i].trim().length() == 0)
								&& (units[i].equals("0.025,適量") || units[i].equals("0.0125,少許"))) {
							ingredient_amount[i] = "1";
							continue;
						} else if (!ingredient_amount[i].trim().matches(ingredient_amountReg)
								|| ingredient_amount[i].equals("0")) { // 以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("數量: 只能是數字, 且小數點不可超過二位數也不得為0");
							break;
						}
					}
				}

				if (units == null) {
					errorMsgs.add("請填寫食材單位");
				} else {
					for (int i = 0; i < units.length; i++) {
						if ("選擇單位".equals(units[i]) || units[i].trim().length() == 0) {
							errorMsgs.add("請選擇單位");
							break;
						}
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
						recipe_step.append(recipe_steps[i] + "/");
					}
				}

				// ************************開始串切食材內容處理***************************//
				// *******************連接食材庫機算營養含量***********************///
				Double carbo_intake = 0.0;
				Double protein_intake = 0.0;
				Double fat_intake = 0.0;
				Double calo_intake = 0.0;
				Double vitamin_b = 0.0;
				Double vitamin_c = 0.0;
				Double salt_intake = 0.0;
				Double vage_intake = 0.0;

				IngredientDAO dao = new IngredientDAO();
				IngredientVO ingredientVO = null;

				String ingredientNames = "";
				StringBuilder recipe_ingredient = new StringBuilder();

				// ************************開始各種處理***************************//

				if (errorMsgs.isEmpty()) {

					String unitStr = "";
					String[] unitContainer = new String[(recipe_ingredients.length) * 2];
					String[] unitNum = new String[recipe_ingredients.length];
					String[] unitName = new String[recipe_ingredients.length];
					for (int i = 0; i < units.length; i++) {
						int a = 0;
						unitStr = units[i];
						unitContainer = unitStr.split(",");
						unitNum[i] = unitContainer[a];
						unitName[i] = unitContainer[++a];
						// 順便串起食材內容
						recipe_ingredient
								.append(recipe_ingredients[i] + ":" + ingredient_amount[i] + "/" + unitName[i] + ",");
						a++;
					}

					// 計算食譜營養量
					try {
						for (int i = 0; i < recipe_ingredients.length; i++) {
							ingredientNames = recipe_ingredients[i];
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
						salt_intake = salt_intake * 0.001; //將mg變成g
						salt_intake = Double.valueOf(df.format(salt_intake));
						vage_intake = Double.valueOf(df.format(vage_intake));

					} catch (Exception e) {
						errorMsgs.add("本平台尚未提供[" + ingredientNames + "],請更換食材");
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
				RecipeVO_saved recipeSaved = new RecipeVO_saved(recipe_ingredients, ingredient_amount, units,
						recipe_steps, recipe_photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的物件,也存入req
					req.setAttribute("recipeSaved", recipeSaved);
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe/addRecipe.jsp");
					failureView.forward(req, res);
					return;

				}

				/*************************** 2.開始新增資料 ***************************************/

				RecipeService RecSvc = new RecipeService();
				recipeVO = RecSvc.addRecipe(rcstyle_no, member_id, recipe_name, recipe_type, recipe_photo,
						recipe_content, recipe_step.toString(), recipe_ingredient.toString(), cook_time, calo_intake,
						salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				// 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/addRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		/*******************************
		 * END OF INSERT
		 ****************************************/

		/***********************************
		 * 開始刪除
		 ************************************/

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接請求參數 ***************************************/
				String recipe_id = req.getParameter("recipe_id");

				/*************************** 2.開始刪除資料 ***************************************/
				RecipeService recipeSvc = new RecipeService();
				recipeSvc.deleteRecipe(recipe_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
		}
	}

	/*******************************
	 * END OF DELETE
	 ****************************************/

	// 將Part位元資料轉為BASE
	public String getPictureClob(Part part) {
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

	public RecipeVO_saved getSplitedIngAndStep(RecipeVO_saved recipeVO, String Ingredient, String Step) {
		RecipeVO_saved recipeVO_saved = null;
		if (recipeVO == null) {
			recipeVO_saved = new RecipeVO_saved();
		} else {
			recipeVO_saved = recipeVO;
		}

		String[] tokens = Ingredient.split(",");
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
		String[] recipe_steps = Step.split("/");
		// 將切割好的字串存入延伸的VO
		recipeVO_saved.setRecipe_ingredients(ingredientName);
		recipeVO_saved.setIngredient_amount(ingredientNum);
		recipeVO_saved.setUnits(ingredientUnit);
		recipeVO_saved.setRecipe_steps(recipe_steps);

		return recipeVO_saved;
	}
}
