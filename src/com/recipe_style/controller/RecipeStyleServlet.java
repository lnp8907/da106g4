package com.recipe_style.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.recipe_style.model.RecipeStyleService;

@WebServlet({ "/back-end/recipe/RecipeStyleServlet" })
public class RecipeStyleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String rcstyle = req.getParameter("rcstyle");
			String rcstyleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (rcstyle == null || rcstyle.trim().length() == 0) {
				errorMsgs.add("食譜名稱: 請勿空白");
			} else if (!rcstyleReg.trim().matches(rcstyleReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食譜名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("rcstyle", rcstyle);
			}

			/*************************** 2.開始新增資料 *****************************************/
			RecipeStyleService recipeStyleService = new RecipeStyleService();
			recipeStyleService.insert(rcstyle);
			RequestDispatcher succeseView = req
					.getRequestDispatcher("//back-end/recipe/backEndRecipePage.jsp?pageType=recipeStyleList.jsp");
			succeseView.forward(req, res);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/backEndRecipePage.jsp?pageType=recipeStyleList.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String rcstyle_no = req.getParameter("rcstyle_no");
			String whichPage = req.getParameter("whichPage");
			String rcstyle = req.getParameter("rcstyle");
			String rcstyleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (rcstyle == null || rcstyle.trim().length() == 0) {
				errorMsgs.add("食譜名稱: 請勿空白");
			} else if (!rcstyleReg.trim().matches(rcstyleReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食譜名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("rcstyle", rcstyle);
			}
			/*************************** 2.開始新增資料 *****************************************/
			RecipeStyleService recipeStyleService = new RecipeStyleService();
			recipeStyleService.update(rcstyle_no, rcstyle);
			String oldRCS_id = rcstyle_no;
			req.setAttribute("oldRCS_id", oldRCS_id);
			RequestDispatcher succeseView = req.getRequestDispatcher("/back-end/recipe/backEndRecipePage.jsp?pageType=recipeStyleList.jsp&whichPage=" + whichPage);
			succeseView.forward(req, res);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			} catch (Exception e) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/backEndRecipePage.jsp?pageType=recipeStyleList.jsp");
//				failureView.forward(req, res);
//			}
		}
	}
}
