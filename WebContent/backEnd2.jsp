<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.staff.model.*"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ page import="java.util.*"%>

<%



    String staff_id =(String) session.getAttribute("staff_id");
	out.println(staff_id);
	StaffVO staffVO = (StaffVO)session.getAttribute("staffVO");
	Set<String> powerList = (Set<String>)session.getAttribute("powerList"); 
	
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
            font-family: "Microsoft JhengHei", "標楷體", "Lato", sans-serif;
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
            background-color: white;
            display: flex;
            flex-flow: row nowrap;
            /*margin-top: 10px;*/
            padding-left: 250px;
        }

        .left-bar {
            position: absolute;
            top: 0;
            left: 0;
            /* position: relative; */
            z-index: 5;
            width: 200px;
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

        .building {
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
        }

        /* .top-bar { ------------------------上方下拉式選單
            position: relative;
            display: flex;
            height: 60px;
            align-items: center;
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
            box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.08);
        }

        .top-bar>span {
            margin-left: 30px;
            font-weight: bold;
            letter-spacing: 2px;
            color: #FF2C55;
        } */

        /* .cta-button { --------------- 開封後是粉紅小點點
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
        }

        .cta-button:hover {
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.46);
            transform: translateY(-5px);
        }

        .cta-button>span {
            color: white;
            font-size: 30px;
        } */

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
            margin: 15px 40px 30px;
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
            font-family: "Microsoft JhengHei", "Lato", sans-serif;
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
        }
        a{
        text-decoration:none;
        color:white;}
    </style>

<body>
    <div class="container">
        <div class="box">
            <!--這裡是左邊選單-->
            <div class="left-bar"><span class="fa fa-cloud-download"><img src="image/logo_nohead.png" alt="LOGO"
                        width="200" height="200"></span>
                <div class="menu-group"> <span class="fa fa-television"><a href="back-end/staff/StaffServlet?action=getOne_ForStaff&pageType=staffSetting.jsp&staff_id=910001">帳號設定</a></span> 
                    <div class="building"><span class="fa fa-building"></span><span class="fa fa-building"></span></div>
                    <span class="fa fa-cog"></span>
                </div>
                
                
                <form method="POST" action="<%=request.getContextPath()%>/back-end/staff/StaffServlet">	
                 <input type="hidden" name="action" value="loginOUT"> 
                <span><input type="submit" class="fa fa-sign-out" >登出</span>
            </form>	
            
            </div>
            <div class="wrapper">
                <div class="top-bar"><span class="cloud"></span><!-- 上方隱含的下拉式選單界線 -->
                    <div class="cta-button"><span></span></div><!-- 粉紅小點點 -->
                </div>
                <div class="content">
                    <div class="menu">
                        <!-- 卡片內容上方留白的起始標籤 -->
                        <div class="search"><label> <span class="fa fa-search"></span></div>
                        <div class="list-info"><span></span><span class="fa fa-bars"></span><span
                                class="fa fa-th-large"></span></div>
                    </div><!-- 卡片內容上方留白的結束標籤 -->
                </div>
                <div class="grid">
                    <!-- 卡片內容起始標籤 -->
                    <div class="card">
                        <div class="number"><span>1</span></div>
                        <div class="img"></div>
                        <div class="product-name" ><span>員工管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span><c:if test="${powerList.contains('920001')}">
                            <a href="back-end/staff/staffPage.jsp">進入管理 </a>
                            </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>2</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>會員管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span><c:if test="${powerList.contains('920002')}">
                            <a href="back-end/member/memberPage.jsp">進入管理</a>
                            </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>3</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>食譜管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span>
                            <c:if test="${powerList.contains('920003')}">
                            <a href="back-end/recipe/backEndRecipePage.jsp">進入管理</a>
                             </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>4</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>直播管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span>
                            <c:if test="${powerList.contains('920004')}">
                            <a href="back-end/livestream/listAllLivestream.jsp">進入管理</a>
                            </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>5</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>食材商城管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span>
                              <c:if test="${powerList.contains('920005')}">
                            <a href="back-end/shop_product/shop_backendPage.jsp">進入管理</a>
                                  </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>6</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>即時配送管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span>
                               <c:if test="${powerList.contains('920006')}">
                            <a href="back-end/Instant_order/Instant_order_backendPage.jsp">進入管理</a>
                                </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>7</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>課程管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span>
                             <c:if test="${powerList.contains('920007')}">
                            <a href="back-end/course/backEndCoursePage.jsp">進入管理</a>
                            </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>8</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>優惠券管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span>
                             <c:if test="${powerList.contains('920008')}">
                            <a href="back-end/coupon/couponPage.jsp">進入管理</a>
                               </c:if>
                            </span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="number"><span>9</span></div>
                        <div class="img"></div>
                        <div class="product-name"><span>通知管理</span></div>
                        <div class="overlay">
                            <div class="detail"><span> <c:if test="${powerList.contains('920009')}">進入管理</c:if></span><span class="fa fa-pencil"></span></div>
                        </div>
                    </div>
                </div> <!-- end of grid -->
                <!-- 卡片內容結束標籤 -->
            </div>
        </div>
    </div>
</body>

</html>