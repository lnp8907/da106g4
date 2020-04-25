package com.course.controller;

import java.io.*;


import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.course.model.CourseService;
import com.course.model.CourseVO;


@MultipartConfig
@WebServlet({ "/front-end/course/photo","/photo" })
public class PhotoReader extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // �]�w�s�X
		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();

		try {
			String course_id = req.getParameter("course_id");
			
			/***************************2.開始查詢資料****************************************/
			CourseService courseService = new CourseService();
			CourseVO courseVO = courseService.getOneCourse(course_id);
			byte[] photo = courseVO.getCourse_photo();
			if (!(photo==null || photo.length==0)) {
				InputStream in = new ByteArrayInputStream(photo);
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

}