package com.shop.controller;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductJDBCDAO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@MultipartConfig
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;

	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneProductDisplay".equals(action)) {
			System.out.println("進入單選");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("product_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("不得為空值");
				}

				String product_id = null;
				try {
					product_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式異常不正確");
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productvo = productSvc.getOneProduct(product_id);
				if (productvo == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_product/listAllProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_id", product_id);
				req.setAttribute("productvo", productvo);
				String url = "/back-end/shop_product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/listAllProduct.jsp");
				failureView.forward(req, res);
			}

		}
		// 前往頁面
		if ("ProductUpdatePage".equals(action)) { // 來自listAllEmp.jsp
			System.out.println("有進來喔更新頁面處理");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			ProductService productSvc = new ProductService();
			ProductVO productvo = productSvc.getOneProduct(req.getParameter("product_id"));

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String product_id = req.getParameter("product_id");
				System.out.println(product_id);
				if (productvo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productvo", productvo);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_product/listOneProduct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_id", product_id);
				req.setAttribute("productvo", productvo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/shop_product/updata_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.setAttribute("productvo", productvo);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/listOneProduct.jsp");
				failureView.forward(req, res);
			}
		}
		// 前往詳細頁面ProductUpdate
		if ("listOneProduct".equals(action)) {
			System.out.println("有進來喔 前往詳細頁面");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("product_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("不得為空值");
				}

				String product_id = null;
				try {
					product_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式異常不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_product/listAllProduct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println(product_id);

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productvo = productSvc.getOneProduct(product_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_id", product_id);
				req.setAttribute("productvo", productvo); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/shop_product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/listAllProduct.jsp");
				failureView.forward(req, res);
			}

		}
		// 更新
		if ("update".equals(action)) {
			System.out.println("處理更新");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String product_id = new String(req.getParameter("product_id").trim());
				System.out.println("獲得ID:product_id" + product_id);
				ProductService prosvc = new ProductService();
				ProductVO productvo = prosvc.getOneProduct(product_id);

				// 類型用下拉試選單
				String product_type = new String(req.getParameter("product_type").trim());
				productvo.setProduct_type(product_type);
				// 商品名
				String product_name = req.getParameter("product_name");
				System.out.println("商品名:" + product_name);

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名: 請勿空白");
				} else if (!product_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名: 只能是中、英文字母 , 且長度必需在1到10之間");
				}
				productvo.setProduct_name(product_name);
				// 商品價格
				Integer product_price = null;
				try {
					product_price = new Integer(req.getParameter("product_price"));
				} catch (NumberFormatException e) {
					product_price = 0;
					errorMsgs.add("單價請填整數.");
				}
				productvo.setProduct_price(product_price);

				req.setCharacterEncoding("UTF-8"); // 處理中文檔名
				res.setContentType("text/html; charset=UTF-8");

				Integer product_status = new Integer(req.getParameter("product_status"));
				System.out.println("狀態為:" + product_status);
				productvo.setProduct_status(product_status);
				Double carbohydrate = null;

				try {
					carbohydrate = new Double(req.getParameter("carbohydrate"));
				} catch (Exception e1) {
					carbohydrate = 0.0;
					errorMsgs.add("碳水化合物請勿填入非數字的值");
				}
				if (carbohydrate == 0) {
					carbohydrate = 0.0;
				} else if (carbohydrate > 9999 || carbohydrate < 0.0091) {
					errorMsgs.add("碳水化合物請勿填入異常的數值");

				}
				productvo.setCarbohydrate(carbohydrate);

				Double protein = null;
				try {
					protein = new Double(req.getParameter("protein").trim());
				} catch (Exception e) {
					protein = 0.0;
					errorMsgs.add("蛋白質請勿填入非數字的值");
				}
				if (protein == 0) {
					protein = 0.0;
				}

				else if (protein > 9999 || protein < 0.0091) {
					errorMsgs.add("蛋白質請勿填入異常的數值");
				}
				productvo.setProtein(protein);

				
				
				Double fat = 0.0;

				try {
					fat = new Double(req.getParameter("fat").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fat = 0.0;
					errorMsgs.add("脂質請勿填入非數字的值");
				}
				if (fat == 0) {
					fat = 0.0;
				} else if (fat > 9999 || protein < 0.009) {
					errorMsgs.add("脂質請勿填入異常的數值");
				}
				productvo.setFat(fat);

				
				
				Double calorie = null;

				try {
					calorie = new Double(req.getParameter("calorie").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("熱量請勿填入非數字的值");
				}
				if (calorie == 0) {
					calorie = 0.0;
				} else if (calorie > 9999 || calorie < 0.009) {
					errorMsgs.add("熱量請勿填入異常的數值");
				}
				productvo.setCalorie(calorie);

				Double vitamin_B = null;

				try {
					vitamin_B = new Double(req.getParameter("vitamin_B").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("維他命B請勿填入非數字的值");
				}
				if (vitamin_B == 0) {
					vitamin_B = 0.0;
				} else if (vitamin_B > 9999 || vitamin_B < 0.009) {
					errorMsgs.add("維他命B請勿填入異常的數值");
				}
				productvo.setVitamin_B(vitamin_B);

				Double vitamin_C = null;

				try {
					vitamin_C = new Double(req.getParameter("vitamin_C").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("維他命C請勿填入非數字的值");
				}
				if (vitamin_C == 0) {
					vitamin_C = 0.0;
				} else if (vitamin_C > 9999 || vitamin_C < 0.009) {
					errorMsgs.add("維他命C請勿填入異常的數值");
				}
				productvo.setVitamin_C(vitamin_C);
				System.out.println("維他命C:"+vitamin_C);

				Double salt = null;
				try {
					salt = new Double(req.getParameter("salt").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("鈉含量請勿填入非數字的值");
				}
				if (salt == 0) {
					salt = 0.0;
				} else if (salt > 9999 || salt < 0.009) {
					errorMsgs.add("鈉含量請勿填入異常的數值");
				}
				productvo.setSalt(salt);

				Double vagetbale = null;

				try {
					vagetbale = new Double(req.getParameter("vagetbale").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("膳食纖維請勿填入非數字的值");
				}
				if (vagetbale == 0) {
					vagetbale = 0.0;
				} else if (vagetbale > 9999 || vagetbale < 0.009) {
					errorMsgs.add("膳食纖維請勿填入異常的數值");
				}
				productvo.setVagetbale(vagetbale);

				String content = req.getParameter("content");
				System.out.println("內容:" + content);
				productvo.setContent(content);

				
				System.out.println("圖片測試");
				// -----------------------圖片測
				Part filePart = req.getPart("product_photo");
				byte[] product_photo = null;
				try {
					if (filePart.getSize() == 0) {
						if (productvo.getProduct_photo() == null) {
							String path = getServletContext().getRealPath("/");
							System.out.println(path);
							path += "\\back-end\\images";
							path += "\\NullProductImage.png";
							byte[] pic = ProductJDBCDAO.getPictureByteArray(path);
							product_photo = pic;
						} else {

							product_photo = productvo.getProduct_photo();

							System.out.println("這是舊圖片:" + product_photo);
						}
					}

					else {
						InputStream fileContent = filePart.getInputStream();
						product_photo = inputStreamToByte(fileContent);
						System.out.println("這是新圖片:" + product_photo);
					}
					productvo.setProduct_photo(product_photo);
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("productvo", productvo); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/shop_product/updata_product.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("更新後\n品名:" + product_type + product_name + product_price + product_photo
						+ product_status + carbohydrate + protein + fat + calorie + vitamin_B + vitamin_C + salt
						+ vagetbale + content);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				prosvc.update(product_id, product_type, product_name, product_price, product_photo, product_status,
						carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetbale, content);
				req.setAttribute("product_id", product_id);
				String url = "/back-end/shop_product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/updata_product.jsp");
				failureView.forward(req, res);
			}
		}
//處理新增
		if ("insert".equals(action)) {
			System.out.println("處理新增");

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				byte[] product_photo = null;
				try {
					Part filePart = req.getPart("product_photo");
					String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					System.out.println(fileName);

					InputStream fileContent = filePart.getInputStream();
					product_photo = inputStreamToByte(fileContent);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("圖片處理完畢");
				System.out.println(product_photo);

//-----------------------------------------------------------------//
				String product_type = new String(req.getParameter("product_type").trim());
				System.out.println(product_type);

				// 商品名
				String product_name = req.getParameter("product_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名: 請勿空白");
				} else if (!product_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名: 只能是中、英文字母 , 且長度必需在1到10之間");
				}
				// 商品價格
				Integer product_price = null;

				try {
					product_price = new Integer(req.getParameter("product_price").trim());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					product_price = 0;
					errorMsgs.add("請勿填入非數字的值");
				}
				if (product_price > 99999 || product_price < 0) {
					errorMsgs.add("請勿填入異常的數值");

				}

				Integer product_status = new Integer(req.getParameter("product_status"));
//處理細項
				Double carbohydrate = null;

				try {
					carbohydrate = new Double(req.getParameter("carbohydrate"));
				} catch (Exception e1) {
					carbohydrate = 0.0;
					errorMsgs.add("碳水化合物請勿填入非數字的值");
				}
				if (carbohydrate == 0) {
					carbohydrate = 0.0;
				} else if (carbohydrate > 9999 || carbohydrate < 0.0091) {
					errorMsgs.add("碳水化合物請勿填入異常的數值");

				}

				Double protein = null;
				try {
					protein = new Double(req.getParameter("protein").trim());
				} catch (Exception e) {
					protein = 0.0;
					errorMsgs.add("蛋白質請勿填入非數字的值");
				}
				if (protein == 0) {
					protein = 0.0;
				}

				else if (protein > 9999 || protein < 0.0091) {
					errorMsgs.add("蛋白質請勿填入異常的數值");

				}

				Double fat = 0.0;

				try {
					fat = new Double(req.getParameter("fat").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fat = 0.0;
					errorMsgs.add("脂質請勿填入非數字的值");
				}
				if (fat == 0) {
					fat = 0.0;
				} else if (fat > 9999 || protein < 0.009) {
					errorMsgs.add("脂質請勿填入異常的數值");
				}

				Double calorie = null;

				try {
					calorie = new Double(req.getParameter("calorie").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("熱量請勿填入非數字的值");
				}
				if (calorie == 0) {
					calorie = 0.0;
				} else if (calorie > 9999 || calorie < 0.009) {
					errorMsgs.add("熱量請勿填入異常的數值");
				}

				Double vitamin_B = null;

				try {
					vitamin_B = new Double(req.getParameter("vitamin_B").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("維他命B請勿填入非數字的值");
				}
				if (vitamin_B == 0) {
					vitamin_B = 0.0;
				} else if (vitamin_B > 9999 || vitamin_B < 0.009) {
					errorMsgs.add("維他命B請勿填入異常的數值");
				}

				Double vitamin_C = null;

				try {
					vitamin_C = new Double(req.getParameter("vitamin_C").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("維他命C請勿填入非數字的值");
				}
				if (vitamin_C == 0) {
					vitamin_C = 0.0;
				} else if (vitamin_B > 9999 || vitamin_B < 0.009) {
					errorMsgs.add("維他命C請勿填入異常的數值");
				}

				Double salt = null;
				try {
					salt = new Double(req.getParameter("salt").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("鈉含量請勿填入非數字的值");
				}
				if (salt == 0) {
					salt = 0.0;
				} else if (salt > 9999 || salt < 0.009) {
					errorMsgs.add("鈉含量請勿填入異常的數值");
				}

				Double vagetbale = null;

				try {
					vagetbale = new Double(req.getParameter("vagetbale").trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errorMsgs.add("膳食纖維請勿填入非數字的值");
				}
				if (vagetbale == 0) {
					vagetbale = 0.0;
				} else if (vagetbale > 9999 || vagetbale < 0.009) {
					errorMsgs.add("膳食纖維請勿填入異常的數值");
				}
				String content = req.getParameter("content");

				ProductVO productvo = new ProductVO();
				System.out.println(product_type);
				productvo.setProduct_type(product_type);
				productvo.setProduct_name(product_name);
				productvo.setProduct_price(product_price);
				productvo.setProduct_photo(product_photo);
				productvo.setProduct_status(product_status);
				productvo.setCarbohydrate(carbohydrate);
				productvo.setProtein(protein);
				productvo.setFat(fat);
				productvo.setCalorie(calorie);
				productvo.setVitamin_B(vitamin_B);
				productvo.setVitamin_C(vitamin_C);
				productvo.setSalt(salt);
				productvo.setVagetbale(vagetbale);
				productvo.setContent(content);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productvo", productvo);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				ProductService productSvc = new ProductService();
				productSvc.insert(product_type, product_name, product_price, product_photo, product_status,
						carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetbale, content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String whichPage = req.getParameter("whichPage");
System.out.println("第幾頁"+whichPage);
				String url="/back-end/shop_backendPage.jsp?whichPage="+whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("異常:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

		// 刪除
		if ("delete".equals(action)) { 
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
			System.out.println("開始刪除作業");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			ProductService productSvc = new ProductService();
			String product_id = new String(req.getParameter("product_id"));
		System.out.println(product_id);
		String whichPage = req.getParameter("whichPage");

			
			try {
				
				
				
				/*************************** 1.接收請求參數 ***************************************/
				if(productSvc.isProduct_idFK(product_id)==false) {
					errorMsgs.add("此商品已被購買請勿刪除");
					System.out.println(productSvc.isProduct_idFK(product_id));
				}	
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_id", product_id);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_backendPage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始刪除資料 ***************************************/
				productSvc.delete(product_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url="/back-end/shop_backendPage.jsp?whichPage="+whichPage;
				String url=req.getParameter("requestURL");
				System.out.println("路徑為"+url);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		// 前往新增頁面
		if ("addProduct".equals(action)) {
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			String url = "/back-end/shop_product/addProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}

	public static byte[] inputStreamToByte(InputStream is) throws Exception {
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] a = new byte[1000];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = bis.read(a)) != -1) {
			bos.write(a, 0, len);
		}
		bis.close();
		bos.close();
		return bos.toByteArray();
	}

}
