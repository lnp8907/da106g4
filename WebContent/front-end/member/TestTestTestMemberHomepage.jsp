
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String member_id =(String) session.getAttribute("member_id");
MemberService memberSvc = new MemberService();
MemberVO memberVO = memberSvc.getOneMember(member_id);
MemberVO memberVO1 =(MemberVO) session.getAttribute("memberVO");
String member_name =(String) session.getAttribute(memberVO1.getMember_name());
	MemberService pSvc = new MemberService();
	List<MemberVO> list = pSvc.getAll();
	pageContext.setAttribute("list", list);
pageContext.setAttribute("member_status", memberVO1.getMember_status());
pageContext.setAttribute("memberVO1", memberVO1);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<bady>


<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/MemberServlet" name="upateform" id="upateform"
		enctype="multipart/form-data" >







			<div class="container">

				<h3>我的檔案</h3>

				<h1>管理你的檔案以保護你的帳戶</h1>




				

					<table align="center" cellpadding="10">

						<!----- First Name ---------------------------------------------------------->
					
						<tr>
							<td>會員帳號:</td>
							<td><%=memberVO.getAccount()%></td>
						</tr>
					
						<tr>
				<td>會員圖片:</td>
				<!-- 按鈕 -->
				<td><input type="file" id="imgView" name="member_photo"
					size="45" accept="image/gif, image/jpeg, image/png"> <img src=DBGifReader4.do?photo_type=mempic&member_id=<%=session.getAttribute("member_id")%> id="preview_progressbarTW_img" width=100px height=100px;/></td>

			</tr>
					
					
					
					
					
					
					
					
					
					
					
						<tr>
							<td>會員姓名:</td>

							<td>
								





								<input type="TEXT" name="member_name" size="45"
					id=member_name value="<%=memberVO.getMember_name()%>" />

								<!-- (max 30 characters a-z and A-Z) -->
							</td>
						</tr>
						
						
						
						
						
						
						
						
						
							<tr>
							<td>暱稱:</td>

							<td>
								





								<input type="TEXT" name="nickname" size="45"
					id=nickname value="<%=memberVO.getNickname()%>" />

								<!-- (max 30 characters a-z and A-Z) -->
							</td>
						</tr>
						

						<!----- Last Name ---------------------------------------------------------->
					

						<tr>
							<td>會員密碼:</td>
							<td><input type="password" name="password" size="45" id=password
					value="<%=memberVO.getPassword()%>" /></td>
					<td><label><input type="checkbox" id="show_password" size="45" />顯示密碼</label></td>
						</tr>




						<!----- Date Of Birth -------------------------------------------------------->
						<tr>
							<td>會員生日:</td>

							<td><input type="TEXT" name="birthday" size="45" id="birthday"
					 /></td>

						</tr>

						<!----- Email Id ---------------------------------------------------------->
						<tr>
							<td>信箱:</td>
							<td><input type="TEXT" name="email" size="45" id=email
					value="<%=memberVO.getEmail()%>" /></td>
						</tr>

						<!----- Mobile Number ---------------------------------------------------------->
						<tr>
							<td>電話號碼:</td>
							<td><input type="TEXT" name="cellphone" size="45" id=cellphone
					value="<%=memberVO.getCellphone()%>" /></td>
						</tr>

						<!----- Gender ----------------------------------------------------------->
						<tr>
							<td>性別:</td>
							<td><input type="radio" name="gender" value=0 checked="<%=(memberVO.getGender()==0)? "true": "false"%>"> 男<br>
					<input type="radio" name="gender" value=1 checked="<%=(memberVO.getGender()==1)? "true": "false"%>"> 女<br></td>
						</tr>

						<!----- Address ---------------------------------------------------------->
						<tr>
							<td>地址: <br />
							<br />
							<br /></td>
							<!-- <td><textarea name="Address" rows="4" cols="30"></textarea></td> -->
							<td><input type="TEXT" name="member_address" size="45"
					id="address" value="<%=memberVO.getMember_address()%>" /></td>
						</tr>



						<!----- State ---------------------------------------------------------->
						<!-- <tr>
<td>STATE</td>
<td><input type="text" name="State" maxlength="30" />
(max 30 characters a-z and A-Z)
</td>
</tr> -->

						<!----- Country ---------------------------------------------------------->
						<!-- <tr>
<td>COUNTRY</td>
<td><input type="text" name="Country" value="India" readonly="readonly" /></td>
</tr> -->

						<!----- Hobbies ---------------------------------------------------------->

						<!-- <tr>
<td>HOBBIES <br /><br /><br /></td>
 
<td>
Drawing
<input type="checkbox" name="Hobby_Drawing" value="Drawing" />
Singing
<input type="checkbox" name="Hobby_Singing" value="Singing" />
Dancing
<input type="checkbox" name="Hobby_Dancing" value="Dancing" />
Sketching
<input type="checkbox" name="Hobby_Cooking" value="Cooking" />
<br />
Others
<input type="checkbox" name="Hobby_Other" value="Other">
<input type="text" name="Other_Hobby" maxlength="30" />
</td>
</tr> -->

						<!----- Qualification---------------------------------------------------------->
						<!-- <tr>
<td>QUALIFICATION <br /><br /><br /><br /><br /><br /><br /></td>
 
<td>
<table>
 
<tr>
<td align="center"><b>Sl.No.</b></td>
<td align="center"><b>Examination</b></td>
<td align="center"><b>Board</b></td>
<td align="center"><b>Percentage</b></td>
<td align="center"><b>Year of Passing</b></td>
</tr> -->

						<!-- <tr>
<td>1</td>
<td>Class X</td>
<td><input type="text" name="ClassX_Board" maxlength="30" /></td>
<td><input type="text" name="ClassX_Percentage" maxlength="30" /></td>
<td><input type="text" name="ClassX_YrOfPassing" maxlength="30" /></td>
</tr>
 
<tr>
<td>2</td>
<td>Class XII</td>
<td><input type="text" name="ClassXII_Board" maxlength="30" /></td>
<td><input type="text" name="ClassXII_Percentage" maxlength="30" /></td>
<td><input type="text" name="ClassXII_YrOfPassing" maxlength="30" /></td>
</tr>
 
<tr>
<td>3</td>
<td>Graduation</td>
<td><input type="text" name="Graduation_Board" maxlength="30" /></td>
<td><input type="text" name="Graduation_Percentage" maxlength="30" /></td>
<td><input type="text" name="Graduation_YrOfPassing" maxlength="30" /></td>
</tr>
 
<tr>
<td>4</td>
<td>Masters</td>
<td><input type="text" name="Masters_Board" maxlength="30" /></td>
<td><input type="text" name="Masters_Percentage" maxlength="30" /></td>
<td><input type="text" name="Masters_YrOfPassing" maxlength="30" /></td>
</tr>
 
<tr>
<td></td>
<td></td>
<td align="center">(10 char max)</td>
<td align="center">(upto 2 decimal)</td>
</tr>
</table>
 
</td>
</tr>
  -->
						<!----- Course ---------------------------------------------------------->
						<!-- <tr>
<td>COURSES<br />APPLIED FOR</td>
<td>
BCA
<input type="radio" name="Course_BCA" value="BCA">
B.Com
<input type="radio" name="Course_BCom" value="B.Com">
B.Sc
<input type="radio" name="Course_BSc" value="B.Sc">
B.A
<input type="radio" name="Course_BA" value="B.A">
</td>
</tr> -->

						<!----- Submit and Reset ------------------------------------------------->

			<tr>
							<td colspan="2" align="center">
								
								
								 <!-- <div class="submit">
  <input type="submit"  value="儲存" id="button-blue"/>
  <div class="ease"></div> -->
  <div class="submit_btn">
  
<!--    <span class="submitAndSave" id="article-section-seemore-recipe" id="submit">儲存</span> -->
 
 
 
<%--    <span class="submitAndSave" id="article-section-seemore-recipe" id="submit"><a href="<%=request.getContextPath() %>/front-end/member/FrontMemberServlet?action=updateBySelf">儲存</a></span> --%>
 
 
 
 
 
<!--                <span class="fa fa-sign-out" id="fa fa-sign-out"><a href="back-end/staff/StaffServlet?action=loginOUT">登出</a></span>  -->
 
 
 	 <input type="hidden" name="action" value="updateBySelf">	
 
  <input type="submit" value="儲存" id="send" name="send">
 
 
								
		</div>						
								
								
								
								
								<!-- <input type="reset" value="Reset"> -->
							</td>
						</tr>
					</table>
					
		
					
					 <input type="hidden"
							name="member_id" value="<%=session.getAttribute("member_id")%>">
							
							
						
					
					<input type="hidden"
							name="account" value="${memberVO.account}">
					
					
					
					
				 
					
					
					
					
					
					
								</div>
				
				</form>
				</bady>
				</head>
				</html>