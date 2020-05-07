
	
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

%>













<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Gudea:400,700);

        body {
            perspective: 800px;
            height: 100vh;
            margin: 0;
            overflow: hidden;
            font-family: 'Gudea', sans-serif;
            background: pink;
            /* background: -webkit-linear-gradient(180deg, rgb(224, 20, 81), rgb(236, 119, 201));
            background: linear-gradient(180deg, rgb(224, 20, 81), rgb(236, 119, 201)); */
        }

        body ::-webkit-input-placeholder {
            color: #4E546D;
        }

        body p {
            color: #5B5E6F;
            font-size: 10px;
            text-align: left;
        }

        .backEnd-title {
            position: relative;
            top: -90px;
            left: 10%;
            width: 80%;
            height: 40px;
            font-family: "å¾®è»æ­£é»é«";
            text-align: center;
            font-size: 24px;
            line-height: 40px;
            color: #DC6180;
            letter-spacing: 2px;
            font-weight: 900;
            border-radius: 50px;
            border: 2px solid #DC6180;
        }

        body .login {
            opacity: 1;
            transition-property: transform, opacity, box-shadow, top, left;
            transition-duration: 0.5s;
            transform-origin: 161px 100%;
            transform: rotateX(0deg);
            width: 240px;
            height: 300px;
            position: absolute;
            left: 0;
            right: 0;
            margin: 180px auto 0;
            top: 0;
            bottom: 0;
            padding: 100px 40px 40px 40px;
            border-radius: 20px;
            background: #35394a;
            background: linear-gradient(45deg, #35394a 0%, #1f222e 100%);
        }

        body .login .disclaimer {
            position: absolute;
            top: 160px;
            left: 35px;
            width: 250px;
        }

        body .login_title {
            position: relative;
            top: -36px;
            color: #afb1be;
            height: 40px;
            text-align: left;
            font-size: 16px;
            box-sizing: border-box;
        }

        body .login_fields {
            height: 208px;
            position: absolute;
            top: 40%;
            left: 0;
        }

        body .login_fields .icon {
            position: absolute;
            z-index: 1;
            left: 36px;
            top: 8px;
            opacity: 0.5;
        }

        body .login_fields input[type='password'] {
            color: #DC6180 !important;
        }

        body .login_fields input[type='text'],
        body .login_fields input[type='password'] {
            color: #afb1be;
            width: 190px;
            margin-top: -2px;
            background: #32364a;
            left: 0;
            padding: 10px 65px;
            border-top: 2px solid #393d52;
            border-bottom: 2px solid #393d52;
            border-right: none;
            border-left: none;
            outline: none;
            font-family: 'Gudea', sans-serif;
            box-shadow: none;
        }

        body .login_fields__user,
        body .login_fields__password {
            position: relative;
            top: 0;
        }

        body .login_fields__submit {
            position: relative;
            top: 30px;
            left: 0;
            width: 80%;
            right: 0;
            margin: auto;
        }

        body .login_fields__submit .forgot {
            float: right;
            font-size: 10px;
            margin-top: 11px;
            text-decoration: underline;
        }

        body .login_fields__submit .forgot a {
            color: #606479;
        }

        body .login_fields__submit input {
            border-radius: 50px;
            background: transparent;
            padding: 10px 50px;
            border: 2px solid #DC6180;
            color: #DC6180;
            text-transform: uppercase;
            font-size: 11px;
            transition-duration: 0.2s;
        }

        body .login_fields__submit input:focus {
            box-shadow: none;
            outline: none;
        }

        body .login_fields__submit input:hover {
            color: white;
            background: #DC6180;
            cursor: pointer;
            transition-duration: 0.2s;
        }

        .logo {
            width: 180px;
            margin: 40px auto 0;
            text-align: center;
        }

        .logo>img {
            width: 100%;
        }
    </style>
</head>

<body>
    <div class="logo"><img src="image/logo_nohead.png" alt="logo"></div>
    <div class="login">
        <h1 class="backEnd-title">後台員工登入</h1>
        <div class="login_title">
            <span>Login to your account</span>
        </div>
  <form method="POST" action="<%=request.getContextPath() %>/back-end/staff/StaffServlet">
        <div class="login_fields">
            <div class="login_fields__user">
                <div class="icon">
                    <img src='image/user_icon_copy.png'></div>
                
 <input type="hidden" name="action" value="login">               
                
                
                
                <input type='text' name="staff_id" placeholder='StaffID'>
                <div class="login_fields__password">
                    <div class="icon">
                        <img src='image/lock_icon_copy.png'></div>
                    
                    
                    
                    
                    
                    <input type='password' name="psw" placeholder='Password'>
                    <div class="login_fields__submit">
                        <input type='submit' value='LogIn' onclick="javascript:location.href='backEnd.html'">
                        <div class="forgot">
                            <a href='#'> Forgotten password?</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="disclaimer">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce semper laoreet placerat. Nullam
                    semper
                    auctor justo, rutrum posuere odio vulputate nec.</p>
            </div>
        </div>
        </form>
</body>

</html>