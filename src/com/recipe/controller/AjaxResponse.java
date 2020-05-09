package com.recipe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.course.model.CourseService;
import com.course.model.CourseVO;
import com.livestream.model.LivestreamVO;
import com.livestream.model.LsService;
import com.member.model.MemberService;
import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
import com.recipe_favorite.model.RecipeFavoriteServiec;

@WebServlet({ "/AjaxResponse", "/front-end/recipe/ajaxResponse.do", "/front-end/recipe/AjaxResponse", "/front-end/member/ajaxResponse.do" })
public class AjaxResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxResponse() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		if ("recipe_follow".equals(action)) {
			String recipe_id = "";
			String member_id = "";
			Integer followNum = null;
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
				RecipeService recipeService = new RecipeService();
				recipeFavoriteServiec.insert(recipe_id, member_id);
				followNum = recipeFavoriteServiec.getFollowedNum(recipe_id);
				recipeService.updateFollowNum(recipe_id, followNum);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
			out.write(followNum.toString());
		}

		if ("getLatest".equals(action)) {
			JSONObject obj = new JSONObject();
			RecipeService recipeService = new RecipeService();
			RecipeVO recipeVO = recipeService.getLatest();
			try {
				obj.put("recipe_id", recipeVO.getRecipe_id());
				obj.put("recipe_name", recipeVO.getRecipe_name());
				obj.put("member_id", recipeVO.getMember_id());
				obj.put("recipe_uldate", recipeVO.getRecipe_uldate());
				obj.put("recipe_photo", recipeVO.getRecipe_photo());
				obj.put("refollow_num", recipeVO.getRefollow_num().toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println("OOOK");
			out.write(obj.toString());
		}

		if ("getMostPopular".equals(action)) {
			JSONObject obj = new JSONObject();
			RecipeService recipeService = new RecipeService();
			RecipeVO recipeVO = recipeService.getMostPopular();
			try {
				obj.put("recipe_id", recipeVO.getRecipe_id());
				obj.put("recipe_name", recipeVO.getRecipe_name());
				obj.put("member_id", recipeVO.getMember_id());
				obj.put("recipe_uldate", recipeVO.getRecipe_uldate());
				obj.put("recipe_photo", recipeVO.getRecipe_photo());
				obj.put("refollow_num", recipeVO.getRefollow_num().toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.write(obj.toString());
		}

		// 以下為課程相關
		if ("getCourse".equals(action)) {
			CourseService courseService = new CourseService();
			JSONArray array = new JSONArray();
			List<CourseVO> list = courseService.getForHomePage();
		    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			for (CourseVO courseVO : list) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("course_id", courseVO.getCourse_id());
					obj.put("course_name", courseVO.getCourse_name());
					obj.put("course_start", sdFormat.format(courseVO.getCourse_start()));
					array.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			out.write(array.toString());
		}
		
		if ("getLivestream".equals(action)) {
			LsService lsService = new LsService();
			JSONArray array = new JSONArray();
			List<LivestreamVO> list = lsService.getFourForHomePage();
			for (LivestreamVO livestreamVO : list) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("livestream_id", livestreamVO.getLivestream_id());
					obj.put("title", livestreamVO.getTitle());
					obj.put("introduction", livestreamVO.getIntroduction());
					obj.put("livestream_date", livestreamVO.getLivestream_date());
					array.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			out.write(array.toString());
		}
		
		if ("getMostPopLS".equals(action)) {
			LsService lsService = new LsService();

			LivestreamVO livestreamVO = lsService.getMostPopLS();
			JSONObject obj = new JSONObject();
				try {
					obj.put("livestream_id", livestreamVO.getLivestream_id());
					obj.put("title", livestreamVO.getTitle());
					obj.put("introduction", livestreamVO.getIntroduction());
					obj.put("livestream_date", livestreamVO.getLivestream_date());
					obj.put("watched_num", livestreamVO.getWatched_num());
				} catch (JSONException e) {
					e.printStackTrace();
				}

				out.write(obj.toString());
			}
		
		
		
		System.out.println(action);
		//改狀態為直播中
		if ("beOnline".equals(action)) {

			String	member_id = req.getParameter("member_id");
			Integer num = 2;//2為開播狀態碼
			String livestream_id = req.getParameter("livestream_id");

				/*************************** 2.開始新增資料 *****************************************/
				MemberService memberService = new MemberService();
				memberService.changeOnline(member_id, num);//更改廚師直播狀態為開播
				
				
				LsService lsService = new LsService();
				lsService.updateForOnline(livestream_id, num);//更改直播預告為開播中
				System.out.println("OnlineOK");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		}
		
		
		
		//改狀態為下線中
		if ("beOffline".equals(action)) {
			
			String	member_id = req.getParameter("member_id");
			System.out.println(member_id);
			Integer num = 3;

				/*************************** 2.開始新增資料 *****************************************/
				MemberService memberService = new MemberService();
				memberService.changeOnline(member_id, num);
				System.out.println("OfflineOK");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		}
		
		
		
		
		
		
		
		//最後關閉資源
		out.flush();
		out.close();
	}
		


	



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
