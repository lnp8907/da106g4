<%@page import="java.util.stream.Collector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.livestream.model.*"%>
<%
	LsService lsSvc = new LsService();
    List<LivestreamVO> list = lsSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>

<html>


<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Livestream</title>
    <style>
        @import 'https://fonts.googleapis.com/css?family=Lato';

        /*   Variables   */
        /*   End of Variables   */
        * {
            padding: 0;
            margin: 0;
        }

        html {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        * {
            text-rendering: optimizeLegibility;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        html,
        body {
            width: 100%;
            height: 100%;
            font-family: "微軟正黑體", "標楷體", "Lato", sans-serif;
            background-color: pink;
        }

        *,
        *:before,
        *:after {
            -webkit-box-sizing: inherit;
            -moz-box-sizing: inherit;
            box-sizing: inherit;
        }

        /*  End Resets  */
        .container {
            /* background-color: pink; */
            width: 100%;
            height: 100%;
            height: auto;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .box {
            position: relative;
            width: 1220px;
            height: 850px;
            background-color: white;
            display: flex;
            flex-flow: row nowrap;
            margin-top: 10px;
            padding-left: 250px;
        }

        .left-bar {
            position: absolute;
            top: 0;
            left: 0;
            /* position: relative; */
            z-index: 5;
            width: 250px;
            height: 100%;
            background: #FF4E50;
            background: -webkit-linear-gradient(180deg, #FF2C55, #FF4E2D);
            background: linear-gradient(180deg, #FF2C55, #FF4E2D);
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .left-bar span {
            padding: 5px;
        }

        .left-bar>span {
            cursor: pointer;
            transform: scale(1, 1);
            transition: all ease-in-out 150ms;
        }

        .left-bar>span:hover {
            transform: scale(1.2, 1.2);
        }

        .left-bar>span:first-child {
            margin-top: 20px;
            color: white;
            font-size: 26px;
        }

        .left-bar>span:last-child {
            color: rgba(241, 212, 195, 0.76);
            margin-top: 200px;
            font-size: 26px;
            font-weight: 900;
        }

        .menu-group {
            height: 200px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-between;
            margin-top: 70px;
        }

        .menu-group>span {
            color: rgba(0, 0, 0, 0.5);
            cursor: pointer;
            transform: scale(1);
            transition: all ease-in-out 150ms;
            font-size: 26px;
            font-weight: 600;
        }

        .menu-group>span:hover {
            transform: scale(1.2, 1.2);
        }

        .menu-group .active {
            color: white;
        }

        .menu-group .active:after {
            content: '';
            position: absolute;
            margin-left: 3px;
            margin-top: -4px;
            padding: 4px;
            background-color: yellow;
            border-radius: 50%;
            box-shadow: 0 0 5px 2px rgba(255, 255, 0, 0.5);
        }

        .wrapper {
            flex: 1;
        }
        
        .tocensor{
        /* border:1px solid deeppink;*/
        margin-left:150%;
        white-space:nowrap;
        Font-family: "少女體";
        }


        /*----------------------------------- 嘉宏這邊不要動-----------------------------------*/
        /* .building {
            font-size: 14px;
            display: flex;
            color: rgba(0, 0, 0, 0.5);
            transform: scale(1);
            transition: all ease-in-out 150ms;
            cursor: pointer;
            padding: 5px;
        }

        .building:hover {
            transform: scale(1.2, 1.2);
        }

        .building>span {
            padding: 0;
        }

        .building>span:nth-child(1) {
            font-size: 10px;
            align-self: flex-end;
        }

        .building.active:after {
            content: '';
            position: absolute;
            margin-left: 22px;
            margin-top: -4px;
            padding: 4px;
            background-color: yellow;
            border-radius: 50%;
            box-shadow: 0 0 5px 2px rgba(255, 255, 0, 0.5);
        } */

        /* .top-bar {
            position: relative;
            display: flex;
            height: 60px;
            align-items: center;
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
            box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.08);
        } */

        /* .top-bar>span {
            margin-left: 30px;
            font-weight: bold;
            letter-spacing: 2px;
            color: #FF2C55;
        } */

        /* .cta-button {
            cursor: pointer;
            position: absolute;
            bottom: -45%;
            right: 3.5%;
            width: 50px;
            height: 50px;
            background-color: #FF2C55;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            box-shadow: 0 0 4px rgba(0, 0, 0, 0.14), 0 4px 8px rgba(0, 0, 0, 0.28);
            transition: all ease-in-out 150ms;
        } */

        /* .cta-button:hover {
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.46);
            transform: translateY(-5px);
        }

        .cta-button>span {
            color: white;
            font-size: 30px;
        }

        .menu {
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 45px;
        }

        .menu span {
            color: #FF2C55;
        }

        .menu span:nth-child(n+2) {
            cursor: pointer;
        }

        .search {
            margin-left: 30px;
        }

        .search input {
            margin-left: 15px;
            border: none;
        }

        .list-info {
            margin-right: 30px;
        }

        .list-info span:nth-child(1) {
            margin-right: 40px;
        }

        .list-info span:nth-child(2) {
            margin-right: 20px;
        }

        .grid {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            padding-left: 25px;
            padding-right: 25px;
        }

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border-radius: 3px;
            width: 195px;
            height: 195px;
            margin: 15px 52px 30px;
            box-shadow: 0 2px 15px 0px rgba(0, 0, 0, 0.2);
        }

        .card:hover .overlay {
            opacity: 1;
        }

        .number {
            z-index: 6;
            position: absolute;
            top: 10px;
            left: 10px;
            display: flex;
            background-color: #FF2C55;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            align-items: center;
            justify-content: center;
            box-shadow: 0 3px 7px 1px rgba(255, 44, 85, 0.5);
        }

        .number>span {
            color: white;
            font-weight: bold;
        }

        .overlay {
            opacity: 0;
            background: #FF4E50;
            background: -webkit-linear-gradient(90deg, rgba(255, 44, 85, 0.7), rgba(255, 78, 45, 0.7));
            background: linear-gradient(90deg, rgba(255, 44, 85, 0.7), rgba(255, 78, 45, 0.7));
            width: 100%;
            height: 100%;
            border-radius: 3px;
            position: absolute;
            top: 0;
            left: 0;
            transition: all ease-in-out 200ms;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            flex-wrap: wrap;
        }

        .img {
            background-color: pink;
            height: 50%;
            width: 80%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .img img {
            width: 160px;
            height: 130px;
        }

        .product-name {
            font-family: "Lato", sans-serif;
            font-weight: bold;
            color: #FF2C55;
            margin-top: 10px;
        }

        .detail>span {
            cursor: pointer;
        }

        .detail>span:nth-child(1) {
            width: 80%;
            height: 20px;
            background-color: transparent;
            padding: 10px 40px;
            border-radius: 50px;
            border: 3px solid white;
            color: white;
        }

        .detail>span:nth-child(2) {
            color: white;
            position: absolute;
            top: 15px;
            right: 15px;
            font-size: 19px;
        } */
    </style>

<body>
    <div class="container">
        <div class="box">
            <!--這裡是左邊選單-->
            <div class="left-bar"><span class="fa fa-cloud-download"><img src="/image/logo_nohead.png" alt="LOGO"
                        width="200" height="200"></span>
                        <a class="menu-group-a"
						href="<%=request.getContextPath() + "/backEnd2.jsp"%>"><span style="font-size:30px;">回首頁</span></a>
                <div class="menu-group"> <span class="fa fa-television">帳號設定</span>
                    <div class="building"><span class="fa fa-building"></span><span class="fa fa-building"></span></div>
                    <span class="fa fa-cog"></span>
                </div><span class="fa fa-sign-out">登出</span>
            </div>
            <div class="wrapper">
                <div class="content">
                    <div class="menu">
                        <!-- 卡片內容上方留白的起始標籤 -->

                    </div><!-- 卡片內容上方留白的結束標籤 -->
                </div>
                <div class="grid">
                       <!-- 卡片內容起始標籤 -->
                    <table id="table-1">
                        <tr><td>
                             <h3>所有直播資料 - listAllLivestream.jsp</h3> 
       		  <a href="<%=request.getContextPath()%>/back-end/livestream/listOneLivestream.jsp" class="tocensor">直播預告審查</a>
                             <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" >回首頁</a></h4>
                        </td></tr>
                    </table>
                    
                    <!-- <%-- 錯誤表列 --%> -->
                    <c:if test="${not empty errorMsgs}">
                        <font style="color:red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color:red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    
                    <table>
                        <tr>
                           <th>直播編號</th>
							<th>員工編號</th>
							<th>直播日期</th>
							<th>影片</th>
							<th>狀態</th>
							<th>預告照片</th>
							<th>直播簡介</th>
							<th>直播標題</th>
							<th>觀看人數</th>
							<!-- <th>修改</th>
							<th>刪除</th>     -->                        
                        </tr>
                        <%@ include file="page1.file" %> 
                        <c:forEach var="livestreamVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                            
                            <tr>
                                <td>${livestreamVO.livestream_id}</td>
                                <td>${livestreamVO.member_id}</td>
                                <td>${livestreamVO.livestream_date}</td>
                                
                               <%--  <td>${livestreamVO.video}</td> --%>
                                <td>
                                <c:if test="${livestreamVO.video!=null}"> 
                                <video src=https://da106g4.tk/DA106_G4_Foodporn_Git/front-end/livestream/LivestreamVideoReader?livestream_id=${livestreamVO.livestream_id} type="video/mp4" width="320" height="240" controls></video>
                               </c:if>
                                </td>
                              
                                <td>${livestreamVO.status}</td>
                               
                                <td>
                                <c:if test="${livestreamVO.video!=null}">
                                <img width="100" height="100" src=https://da106g4.tk/DA106_G4_Foodporn_Git/front-end/livestream/LivestreamPhotoReader?livestream_id=${livestreamVO.livestream_id} >
                               </c:if>
                                </td>
                               
                                <td>${livestreamVO.introduction}</td>
                                <td>${livestreamVO.title}</td>
                                <td>${livestreamVO.watched_num}</td>
                                
                                <td>
                                  <FORM METHOD="post" ACTION="livestream.do" style="margin-bottom: 0px;">
                                    <!--  <input type="submit" value="修改"> -->
                                     <input type="hidden" name=livestream_id  value="${livestreamVO.livestream_id}">
                                     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
                                </td>
                                <td>
                                  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/livestream/livestream.do" style="margin-bottom: 0px;">
                                     <!-- <input type="submit" value="刪除"> -->
                                     <input type="hidden" name="livestream_id"  value="${livestreamVO.livestream_id}">
                                     <input type="hidden" name="action" value="delete"></FORM>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                   <%@ include file="page2.file" %>
                    
                  
                </div><!-- end of grid -->
                <!-- 卡片內容結束標籤 -->
            </div>
        </div>
    </div>
</body>

</html>