package com.recipe.controller;


import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ingredient.model.IngredientDAO;
import com.ingredient.model.IngredientVO;
import com.product.model.ProductVO;
import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
import com.recipe.model.RecipeVO_saved;
import com.recipe_favorite.model.RecipeFavoriteServiec;

@WebServlet({ "/RecipeServlet"})
@MultipartConfig

public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("recipe_follow".equals(action)) {

			String recipe_id = "";
			String member_id = "";
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				recipe_id = req.getParameter("recipe_id");
				if (recipe_id == null || recipe_id.trim().length() == 0) {
					throw new Exception();
				}
				member_id = req.getParameter("member_id");
				if (member_id == null || member_id.trim().length() == 0) {
					throw new RuntimeException();
				}

				/*************************** 2.開始新增資料 *****************************************/
				RecipeFavoriteServiec recipeFavoriteServiec = new RecipeFavoriteServiec();
				recipeFavoriteServiec.insert(recipe_id, member_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getChef_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String member_id = "";
			String pageType = req.getParameter("pageType");
			req.setAttribute("pageType", pageType);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				member_id = req.getParameter("member_id");

				if (member_id == null || member_id.trim().length() == 0) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("member_id", member_id); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/chefPage.jsp"); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String pageType = req.getParameter("pageType");
			req.setAttribute("pageType", pageType); //怎麼來怎麼回去
			try {
				/*************************** 接收請求參數 - 輸入格式的錯誤處理 **********************/
				Map<String, String[]> map = new HashMap<>();
				String selectedType = req.getParameter("selectedType");
				String selectedValue = req.getParameter("selectedValue");

				if (selectedType == null || selectedType.trim().length() == 0) {
					map.put("selectedType", new String[] { " CALO_INTAKE > 0 " }); // 給預設值
				} else if ("recipe_type".equals(selectedType)) {
					map.put("selectedType", new String[] { "recipe_type like ('%" + selectedValue + "%')" });
				} else if ("rcstyle_no".equals(selectedType)) {
					map.put("selectedType", new String[] { "rcstyle_no like ('%" + selectedValue + "%')" });
				} else if ("calo_intake".equals(selectedType)) {
					String[] token = selectedValue.split("~");
					if (token.length == 2) {
						map.put("selectedType",
								new String[] { "calo_intake > " + token[0] + " and calo_intake <" + token[1] });
					} else {
						map.put("selectedType", new String[] { "calo_intake > " + token[0] });
					}
				} else {
					String[] token = selectedValue.split("~");
					if (token.length == 2) {
						map.put("selectedType",
								new String[] { "cook_time > " + token[0] + " and cook_time <" + token[1] });
					} else {
						map.put("selectedType", new String[] { "cook_time > " + token[0] });
					}
				}

				String keyword = req.getParameter("keyword");
				if (keyword == null || keyword.trim().length() == 0) {
					map.put("keyword", new String[] { "" }); // 給預設值
				} else {
					map.put("keyword", keyword.split(" "));
				}

				// 排序
				String orderBy = "";
				orderBy = req.getParameter("sort_type");
				if (orderBy == null || orderBy.trim().length() == 0) {
					orderBy = "";
				} else if ("refollow_num".equals(orderBy)) {
					orderBy += " DESC";
				}

				/*************************** 開始查詢資料 *****************************************/
				RecipeService RecipeSvc = new RecipeService();
				List<RecipeVO_saved> list = RecipeSvc.getAllWithTerm(map, orderBy);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/searchRecipe.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				session.setAttribute("list", list); // 資料庫取出的LIST物件,存入req
				// 將查詢的關鍵字在包裝回去,以便排序時使用
				req.setAttribute("keyword", keyword);
				req.setAttribute("selectedType", selectedType);
				req.setAttribute("selectedValue", selectedValue);
				String url = "/front-end/recipe/recipeHomepage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 searchRecipe.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getOne_For_Display".equals(action))

		{ // 來自select_page.jsp的請求

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RecipeService RecipeSvc = new RecipeService();
				RecipeVO_saved recipeVO = RecipeSvc.findByPrimaryKeyForSaved(recipe_id);
				if (recipeVO == null) { // 檢查是否為空直
					errorMsgs.add("查無資料");
				} else {
					recipeVO = Utility.getSplitedIngAndStep(recipeVO);// 如不為空直呼叫切字串的方法,再將完整物件forward
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/recipe/recipeHomepage.jsp?pageType=listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/select_page.jsp");
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
				recipeVO = Utility.getSplitedIngAndStep(recipeVO);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/recipe/update_recipe_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
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
					recipe_photo = Utility.getPictureClob(photo);
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
						DecimalFormat df2 = new java.text.DecimalFormat("#");
						carbo_intake = Double.valueOf(df.format(carbo_intake));
						protein_intake = Double.valueOf(df.format(protein_intake));
						fat_intake = Double.valueOf(df.format(fat_intake));
						calo_intake = Double.valueOf(df2.format(calo_intake));
						vitamin_b = Double.valueOf(df.format(vitamin_b));
						vitamin_c = Double.valueOf(df.format(vitamin_c));
						salt_intake = salt_intake * 0.001; // 將mg變成g
						salt_intake = Double.valueOf(df.format(salt_intake));
						vage_intake = Double.valueOf(df.format(vage_intake));

					} catch (Exception e) {
						errorMsgs.add("本平台尚未提供[" + ingredientNames + "],請更換食材");
					}
				}

				RecipeVO recipeVO = new RecipeVO_saved();
				recipeVO.setRecipe_id(recipe_id);
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
				recipeVO.setRecipe_uldate(recipe_uldate);
				recipeVO.setRefollow_num(refollow_num);
				if (!errorMsgs.isEmpty()) {
					((RecipeVO_saved) recipeVO).setRecipe_ingredients(recipe_ingredients);
					((RecipeVO_saved) recipeVO).setIngredient_amount(ingredient_amount);
					((RecipeVO_saved) recipeVO).setUnits(units);
					((RecipeVO_saved) recipeVO).setRecipe_steps(recipe_steps);
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/update_recipe_input.jsp");
					failureView.forward(req, res);
					return;// 中斷程式

				}

				/*************************** 2.開始修改資料 ***************************************/
				RecipeService RecSvc = new RecipeService();
				// 必須建立一個準備要刷新成功修改的VO,否則待會原VO記憶體位置將被RecSvc.updateRecipe()改變,
				// 導致無法呼叫getSplitedIngAndStep().
				RecipeVO_saved refreshVO = (RecipeVO_saved) recipeVO;

				recipeVO = RecSvc.updateRecipe(recipe_id, rcstyle_no, member_id, recipe_name, recipe_type, recipe_photo,
						recipe_content, recipe_step.toString(), recipe_ingredient.toString(), cook_time, calo_intake,
						salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				/*** 修改完成後以延伸的VO重新進資料庫重刷頁面 ******/
				refreshVO = Utility.getSplitedIngAndStep(refreshVO);
				req.setAttribute("recipeVO", refreshVO);
				String url = "/front-end/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				// 新增成功後轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/update_recipe_input.jsp");
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
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (recipe_name == null || recipe_name.trim().length() == 0) {
					errorMsgs.add("食譜名稱: 請勿空白");
				} else if (!recipe_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("食譜名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
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
					recipe_photo = Utility.getPictureClob(photo);
				} else if (photo.getSize() != 0 && !("".equals(tmep_recipe_photo))) {
					recipe_photo = Utility.getPictureClob(photo);
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
							if (errorMsgs.contains("數量: 只能是數字, 且小數點不可超過二位數也不得為0")) {
								continue;
							}
							errorMsgs.add("數量: 只能是數字, 且小數點不可超過二位數也不得為0");
						}
					}
				}

				if (units == null) {
					errorMsgs.add("請填寫食材單位");
				} else {
					for (int i = 0; i < units.length; i++) {
						if ("選擇單位".equals(units[i]) || units[i].trim().length() == 0) {
							if (errorMsgs.contains("請選擇單位")) {
								continue;
							}
							errorMsgs.add("請選擇單位");
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
						DecimalFormat df2 = new java.text.DecimalFormat("#");
						carbo_intake = Double.valueOf(df.format(carbo_intake));
						protein_intake = Double.valueOf(df.format(protein_intake));
						fat_intake = Double.valueOf(df.format(fat_intake));
						calo_intake = Double.valueOf(df2.format(calo_intake));
						vitamin_b = Double.valueOf(df.format(vitamin_b));
						vitamin_c = Double.valueOf(df.format(vitamin_c));

						salt_intake = salt_intake * 0.001; // 將mg變成g
						salt_intake = Double.valueOf(df.format(salt_intake));
						vage_intake = Double.valueOf(df.format(vage_intake));

					} catch (Exception e) {
						errorMsgs.add("本平台尚未提供[" + ingredientNames + "],請更換食材");
					}
				}

				RecipeVO recipeVO = new RecipeVO_saved();
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

				((RecipeVO_saved) recipeVO).setRecipe_ingredients(recipe_ingredients);
				((RecipeVO_saved) recipeVO).setIngredient_amount(ingredient_amount);
				((RecipeVO_saved) recipeVO).setUnits(units);
				((RecipeVO_saved) recipeVO).setTempPhto(recipe_photo);
				((RecipeVO_saved) recipeVO).setRecipe_steps(recipe_steps);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/recipeHomepage.jsp");
					failureView.forward(req, res);
					return;

				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductVO productVO=new ProductVO();
				RecipeService RecSvc = new RecipeService();
				RecSvc.addRecipe(recipeVO, productVO);
//				recipeVO = RecSvc.addRecipe(rcstyle_no, member_id, recipe_name, recipe_type, recipe_photo,
//						recipe_content, recipe_step.toString(), recipe_ingredient.toString(), cook_time, calo_intake,
//						salt_intake, protein_intake, fat_intake, carbo_intake, vitamin_b, vitamin_c, vage_intake);
//				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String pageType= "searchRecipe.jsp";
				String url = "/front-end/recipe/recipeHomepage.jsp?pageType=" + pageType;
				req.setAttribute("pageType", pageType); //導回searchReicpe
				HttpSession session = req.getSession();
				session.removeAttribute("list");//該list為search的結果,因要導回search畫面,所以不管使用者有沒有搜尋過一律先清空
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				// 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/recipeHomepage.jsp");
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
				String url = "/front-end/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		/*******************************
		 * END OF DELETE
		 ****************************************/

		if ("changeStatus".equals(action)) { // 來自listAllEmp.jsp

			String recipe_id = req.getParameter("recipe_id");
			String pageType = (String) req.getAttribute("pageType");
			if (pageType == null) {
				pageType = "recipeAllList.jsp";
			}
			String whichPage = req.getParameter("whichPage");
			Integer recipe_status = new Integer(req.getParameter("recipe_status"));
			/*************************** 2.開始刪除資料 ***************************************/
			RecipeService recipeSvc = new RecipeService();
			recipeSvc.changeStatus(recipe_id, recipe_status);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/recipe/backEndRecipePage.jsp?pageType=" + pageType + "&whichPage=" + whichPage;
			req.setAttribute("oldRecipe_id", recipe_id);
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		}
	}

	

	
}
