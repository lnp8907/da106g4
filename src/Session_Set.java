
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/Session_Set")
public class Session_Set extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html; charset=UTF-8");
		String member_id=req.getParameter("member_id");
//		PrintWriter out = res.getWriter();
		MemberService memberService = new MemberService(); 
		MemberVO memberVO = memberService.getOneMember(member_id);
		System.out.println("SessionSet_Member:" + member_id);
		HttpSession session = req.getSession();
        session.setAttribute("memberVO",memberVO);
		String URL=req.getContextPath()+"/front-end/livestream/livestream.jsp#"+member_id;
		res.sendRedirect(URL);
//        String ID = session.getId();
//        out.println("ID="+ID);
	
	}
}
