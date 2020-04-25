<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%
	MemberVO membervo = (MemberVO) request.getAttribute("memberVO");
%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

  <script>
    $("#leftside-navigation .sub-menu > a").click(function (e) {
      $("#leftside-navigation ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
        e.stopPropagation()
    })



    $('#member-center-header').prepend('<div id="menu-icon"><span class="first"></span><span class="second"></span><span class="third"></span></div>');

    $("#menu-icon").on("click", function () {
      $("nav").slideToggle();
      $(this).toggleClass("active");
    });

  </script>


  <link rel="stylesheet" href="css/frontEnd.css">

  <style>
    /* cyrillic-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OX-hpOqc.woff2) format('woff2');
      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
    }

    /* cyrillic */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OVuhpOqc.woff2) format('woff2');
      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
    }

    /* greek-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OXuhpOqc.woff2) format('woff2');
      unicode-range: U+1F00-1FFF;
    }

    /* greek */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OUehpOqc.woff2) format('woff2');
      unicode-range: U+0370-03FF;
    }

    /* vietnamese */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OXehpOqc.woff2) format('woff2');
      unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
    }

    /* latin-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OXOhpOqc.woff2) format('woff2');
      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
    }

    /* latin */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 300;
      src: local('Open Sans Light'), local('OpenSans-Light'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OUuhp.woff2) format('woff2');
      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
    }

    /* cyrillic-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFWJ0bbck.woff2) format('woff2');
      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
    }

    /* cyrillic */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFUZ0bbck.woff2) format('woff2');
      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
    }

    /* greek-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFWZ0bbck.woff2) format('woff2');
      unicode-range: U+1F00-1FFF;
    }

    /* greek */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFVp0bbck.woff2) format('woff2');
      unicode-range: U+0370-03FF;
    }

    /* vietnamese */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFWp0bbck.woff2) format('woff2');
      unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
    }

    /* latin-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFW50bbck.woff2) format('woff2');
      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
    }

    /* latin */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 400;
      src: local('Open Sans Regular'), local('OpenSans-Regular'), url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFVZ0b.woff2) format('woff2');
      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
    }

    /* cyrillic-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOX-hpOqc.woff2) format('woff2');
      unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF, U+A640-A69F, U+FE2E-FE2F;
    }

    /* cyrillic */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOVuhpOqc.woff2) format('woff2');
      unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
    }

    /* greek-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOXuhpOqc.woff2) format('woff2');
      unicode-range: U+1F00-1FFF;
    }

    /* greek */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOUehpOqc.woff2) format('woff2');
      unicode-range: U+0370-03FF;
    }

    /* vietnamese */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOXehpOqc.woff2) format('woff2');
      unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169, U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
    }

    /* latin-ext */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOXOhpOqc.woff2) format('woff2');
      unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
    }

    /* latin */
    @font-face {
      font-family: 'Open Sans';
      font-style: normal;
      font-weight: 700;
      src: local('Open Sans Bold'), local('OpenSans-Bold'), url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOUuhp.woff2) format('woff2');
      unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
    }


    .access-menu-icon1 {
      width: 35px;
      margin-right: 10px;
    }


    main {

      height: 200px;
    }

    body {
      color: #5D5F63;
      background: white;
      font-family: 'Open Sans', sans-serif;
      padding: 0;
      margin: 0;
      text-rendering: optimizeLegibility;
      -webkit-font-smoothing: antialiased;
    }


    .sidebar {
      width: 22%;
      height: 100%;
      background: white;
      -webkit-transition: all .3s ease-in-out;
      -moz-transition: all .3s ease-in-out;
      -o-transition: all .3s ease-in-out;
      -ms-transition: all .3s ease-in-out;
      transition: all .3s ease-in-out;
      z-index: 100;
      float: left;
      padding-top: 20px;
    }

    .sidebar #leftside-navigation ul,
    .sidebar #leftside-navigation ul ul {
      margin: -2px 0 0;
      padding: 0;
    }

    .sidebar #leftside-navigation ul li {
      list-style-type: none;
      border-bottom: 1px solid rgba(255, 255, 255, 0.05);
    }

    .sidebar #leftside-navigation ul li.active>a {
      color: #1abc9c;
    }

    .sidebar #leftside-navigation ul li.active ul {
      display: block;
    }

    .sidebar #leftside-navigation ul li a {
      color: #d12139;
      text-decoration: none;
      display: block;
      padding: 10px;
      font-size: 16px;
      font-family: 微軟正黑體;
      font-weight: bold;
      outline: 0;
      -webkit-transition: all 200ms ease-in;
      -moz-transition: all 200ms ease-in;
      -o-transition: all 200ms ease-in;
      -ms-transition: all 200ms ease-in;
      transition: all 200ms ease-in;

    }

    .sidebar #leftside-navigation ul li a:hover {
      color: #1abc9c;
    }

    .sidebar #leftside-navigation ul li a img {
      display: inline-block;
      vertical-align: middle;
    }

    .sidebar #leftside-navigation ul li a span {
      display: inline-block;
      vertical-align: middle;

    }

    .sidebar #leftside-navigation ul li a i {
      width: 20px;
    }

    .sidebar #leftside-navigation ul li a i .fa-angle-left,
    .sidebar #leftside-navigation ul li a i .fa-angle-right {
      padding-top: 3px;
    }

    .sidebar #leftside-navigation ul ul {
      display: none;
    }

    .sidebar #leftside-navigation ul ul li {
      background: white;
      margin-bottom: 0;
      margin-left: 0;
      margin-right: 0;
      border-bottom: none;
    }

    .sidebar #leftside-navigation ul ul li a {
      font-size: 16px;
      font-weight: bold;
      padding-top: 8px;
      padding-bottom: 8px;
      color: #FF5757;
    }

    .footer-copyright24 {

      margin-top: 50%;

    }



    #member-center-header,
    #member-center-header:before,
    #member-center-header:after {
      -moz-box-sizing: border-box;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
      margin: 0;
      padding: 0
    }

    #member-center-header {
      height: 65px;
      text-transform: uppercase;
      font-size: 16px;
      float: right;
      width: 78%;
    }

    #member-center-header a {
      text-decoration: none;
      clear: both;
    }


    #member-center-header:after {
      content: "";
      display: block
    }

    #member-center-header input {
      border: none;
      padding: 10px;
      border-radius: 20px
    }


    #member-center-header nav {
      float: right;
      width: 100%;
    }

    #member-center-header nav li {
      height: 65px;
      text-align: center;
      line-height: 65px;
      list-style: none;
      float: left;
      width: 20%;
      color: #ff5757;
      background-color: rgb(246, 242, 242);
    }

    #member-center-header nav li:hover {
      border-bottom: #1abc9c 2px solid;
      color: #1abc9c;
    }

    #menu-icon.active .mega-col {
      width: 20%;
      float: left
    }

    #menu-icon {
      position: absolute;
      right: 0;
      top: 50%;
      margin-top: -12px;
      margin-right: 30px;
      display: none
    }

    #menu-icon span {
      border: 2px solid #fff;
      width: 30px;
      margin-bottom: 5px;
      display: block;
      -webkit-transition: all .2s;
      transition: all .1s
    }

    @media only screen and (min-width: 960px) {
      #member-center-header nav {
        display: block !important
      }
    }

    @media only screen and (max-width: 959px) {
      #member-center-header nav {
        display: none;
        width: 100%;
        clear: both;
        float: none;
        max-height: 400px;
        overflow-y: scroll
      }

      #menu-icon {
        display: inline;
        top: 45px;
        cursor: pointer
      }

      #menu-icon.active .first {
        transform: rotate(45deg);
        -webkit-transform: rotate(45deg);
        margin-top: 10px
      }

      #menu-icon.active .second {
        transform: rotate(135deg);
        -webkit-transform: rotate(135deg);
        position: relative;
        top: -9px;
      }

      #menu-icon.active .third {
        display: none
      }

      #member-center-header nav {
        padding: 10px
      }

      #member-center-header nav ul {
        float: none
      }

      #member-center-header nav li {
        float: none
      }

      #member-center-header nav ul a {
        float: none;
        padding: 8px;
        display: block
      }

      #member-center-header nav a {
        color: #fff;
        padding: 8px
      }

      #member-center-header nav a:hover {
        background: #fff;
        color: #333;
        border-radius: 3px
      }

      #member-center-header nav ul li li a:before {
        content: "- "
      }i

      #member-center-header.mega-col {
        width: 100%
      }
    }

    .article-contain {
      margin: 30px auto;
      max-width: 1024px;
    }

    #show-main-contain {
      width: 77%;
      height: 800px;
      background-color: rgb(246, 242, 242);
      float: right;
      margin-top: 30px;
    }











    @import url(https://fonts.googleapis.com/css?family=Open+Sans:300);
@import url(http://weloveiconfonts.com/api/?family=entypo);





.avatar {
  background-image: url("image/genius.jpg.png");
  background-size: cover;
  border-radius: 100%;
  margin: auto auto 1rem auto;
  width: 7rem;
  height: 7rem;
  box-shadow: 0 0 0 4px rgba(53, 42, 148, 0.555);
  transition: all 0.5s ease-in-out;
}

.avatar:hover {
  box-shadow: 0 0 0 6px rgba(53, 42, 148, 0.555);
}

h1 {
  text-align:center;
  font-size:24px;
  font-weight:300;
  color:#e69f4e;
  text-shadow: 1px 1px 2px #a8258c;
  margin: 0;
}


.link-top {

            width: 100%;
            height: 1px;
            border-top: solid rgb(226, 16, 44) 2px;

            margin: 5px 0 0 5px;
        }













  </style>

</head>

<body>
  <header>
    <!--這個是上方選單,你用不到-->
    <div id="top-logo" class="logo"><a href="frontEnd.html" title="回首頁"><img class="logo-photo"
          src="image/Handdrawn Circle Logo.png" alt="logo"></a></div>
    <div class="function">
      <div class="function-list">
        <div class="member-center">
          <a href="#"><span class="member-center-spann">會員中心</span></a>
        </div>
      </div>
      <div class="function-list">
        <div class="shop-car">
          <a href="#"><img class="header-icon" src="image/shopping-cart-icon.png" alt="shopping-cart">
            <div class="herder-icon-span"><span class="shop-car-span">購物車</span></div>
          </a>
        </div>
      </div>
      <div class="login">
        <div class="function-list">
          <a href="#"><img class="header-icon" src="image/user-icon.png" alt="login-icon">
            <div class="herder-icon-span"><span class="login-span">登入</span></div>
          </a>
        </div>
      </div>
    </div><!-- end of function-->
    <nav role="navigation">
      <ul class="access-menu">
        <li>
          <a href="#" class="access-menu-a"><img class="access-menu-icon" src="image/recipe-icon.png"><span
              class="access-menu-span">食譜專區</span></a>
          <!-- <div class="access-menu-subinner"> -->
          <span class="access-submenu-1">
            <ul class="access-submenu">
              <li><a href="#">sub link 1</a></li>
              <li><a href="#">sub link 1</a></li>
              <li><a href="#">sub link 1</a></li>
              <li><a href="#">sub link 1</a></li>
            </ul>
          </span>
          </div>
        </li>
        </div>
        <li>
          <a href="#" class="access-menu-a"><img class="access-menu-icon" src="image/livestream-icon.png"><span
              class="access-menu-span">直播專區</span></a>
          <!-- <div class="access-menu-subinner"> -->
          <ul class="access-submenu">
            <li><a href="#">sub link 2</a></li>
            <li><a href="#">sub link 2</a></li>
            <li><a href="#">sub link 2</a></li>
            <li><a href="#">sub link 2</a></li>
          </ul>
          </div>
        </li>
        </div>
        <li>
          <a href="#" class="access-menu-a"><img class="access-menu-icon" src="image/shop-icon.png"><span
              class="access-menu-span">食材商城</span></a>
          <!-- <div class="access-menu-subinner"> -->
          <ul class="access-submenu">
            <li><a href="#">sub link 3</a></li>
            <li><a href="#">sub link 3</a></li>
            <li><a href="#">sub link 3</a></li>
            <li><a href="#">sub link 3</a></li>
          </ul>
          </div>
        </li>
        </div>
        <li>
          <a href="#" class="access-menu-a"><img class="access-menu-icon" src="image/course-icon.png"><span
              class="access-menu-span">料理課程</span></a>
          <!-- <div class="access-menu-subinner"> -->
          <ul class="access-submenu">
            <li><a href="#">sub link 4</a></li>
            <li><a href="#">sub link 4</a></li>
            <li><a href="#">sub link 4</a></li>
            <li><a href="#">sub link 4</a></li>
          </ul>
          </div>
        </li>
        </div>
        <!-- Link5 暫時保留 -->
        <!-- <li>
                    <a href="#" style="display:none">link 5</a>
                    <ul class="access-submenu">
                        <li><a href="#">sub link 5</a></li>
                        <li><a href="#">sub link 5</a></li>
                        <li><a href="#">sub link 5</a></li>
                        <li><a href="#">sub link 5</a></li>
                    </ul>
                </li> -->
      </ul>
    </nav>
  </header><!-- end of header-->
  <!--上方選單尾巴-->
  <!-- 這個是登箱製作,你不要動! -->
  <div class="login-wrap">
    <div class="login-html">
      <img class="login-close" src="image/close.png" alt="close">
      <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign
        In</label>
      <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
      <div class="login-form">
        <div class="sign-in-htm">
          <div class="group">
            <label for="user" class="label">Username</label>
            <input id="user" type="text" class="input">
          </div>
          <div class="group">
            <label for="pass" class="label">Password</label>
            <input id="pass" type="password" class="input" data-type="password">
          </div>
          <div class="group">
            <input id="check" type="checkbox" class="check" checked>
            <label for="check"><span class="icon"></span> Keep me Signed in</label>
          </div>
          <div class="group SignIn">
            <input type="submit" class="button" value="Sign In">
          </div>
          <div class="hr"></div>
          <div class="foot-lnk">
            <a href="#forgot" class="foot-lnk-a">Forgot Password?</a>
          </div>
        </div>
        <div class="sign-up-htm">
          <div class="group">
            <label for="user" class="label">Username</label>
            <input id="user" type="text" class="input">
          </div>
          <div class="group">
            <label for="pass" class="label">Password</label>
            <input id="pass" type="password" class="input" data-type="password">
          </div>
          <div class="group">
            <label for="pass" class="label">Repeat Password</label>
            <input id="pass" type="password" class="input" data-type="password">
          </div>
          <div class="group">
            <label for="pass" class="label">Email Address</label>
            <input id="pass" type="text" class="input">
          </div>
          <div class="group">
            <input type="submit" class="button" value="Sign Up">
          </div>
          <div class="hr"></div>
          <div class="foot-lnk">
            <label for="tab-1">Already Member?
          </div>
        </div>
      </div>
    </div>
  </div><!-- end of login-->
  <!--登箱結束-->
  <!--一鍵置頂-->
  <div class="pagetop">
    <a href="#top-logo"><img src="image/go-top-page.png" alt="go-top-page"></a>
  </div><!-- end of pagetop-->
  <!--*************************************以下開放編輯▼**************************************-->
  <!-- 主要內容從這裡開始!!!請自行編輯調整,請勿更動載入的CSS檔案自行生成 -->
  <!-- 主要內容從這裡開始!!!請自行編輯調整,請勿更動載入的CSS檔案自行生成 -->
  <!-- 主要內容從這裡開始!!!請自行編輯調整,請勿更動載入的CSS檔案自行生成 -->
  <!-- 不要動外部的frontEndcss檔案!-->
  <!-- 不要動外部的frontEndcss檔案!-->
  <!-- 不要動外部的frontEndcss檔案!-->
  <main>
    <article>
      <div class="article-contain">
        <!-- 以下開始你的各種標籤 -->


        

















        <aside class="sidebar">

          <div class="avatar"></div>
          <h1>宏哥</h1>

          <div class="link-top"></div>






          <div id="leftside-navigation" class="nano">
            <ul class="nano-content">
              <li>
                <a href="index.html"><i class="fa fa-dashboard"></i><img class="access-menu-icon1"
                    src="image/S__12066824.jpg"><span>我的帳戶</span></a>
              </li>
              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa-cogs"></i><img class="access-menu-icon1"
                    src="image/S__12066823.jpg"><span>購買清單&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>

                  <li><a href="ui-alerts-notifications.html">已成立</a>
                  </li>
                  <li><a href="ui-panels.html">運送中</a>
                  </li>
                  <li><a href="ui-buttons.html">已完成</a>
                  </li>
                  <li><a href="ui-slider-progress.html">取消訂單</a>
                  </li>
                  <li><a href="ui-modals-popups.html">1</a>
                  </li>
                  <li><a href="ui-icons.html">2</a>
                  </li>
                  <li><a href="ui-grid.html">3</a>
                  </li>
                </ul>
              </li>
              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa-table"></i><img class="access-menu-icon1"
                    src="image/S__12066821.jpg"><span>我的錢包/點數&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> </span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>
                  <li><a href="tables-basic.html">交易紀錄</a>
                  </li>

                  <li><a href="tables-data.html">提款</a>
                  </li>
                </ul>
              </li>
              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa fa-tasks"></i><img class="access-menu-icon1"
                    src="image/S__12066822.jpg"><span>瀏覽紀錄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>
                  <li><a href="forms-components.html">課程瀏覽</a>
                  </li>
                  <li><a href="forms-validation.html">直播瀏覽</a>
                  </li>
                  <li><a href="forms-mask.html">食譜瀏覽</a>
                  </li>
                  <li><a href="forms-wizard.html">1</a>
                  </li>
                  <li><a href="forms-multiple-file.html">2</a>
                  </li>
                  <li><a href="forms-wysiwyg.html">3</a>
                  </li>
                </ul>
              </li>
              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa-envelope"></i><img class="access-menu-icon1"
                    src="image/S__12066820.jpg"><span>精選收藏&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>
                  <li><a href="mail-inbox.html">課程收藏</a>
                  </li>
                  <li><a href="mail-compose.html">直播收藏</a>
                  </li>
                  <li><a href="mail-compose.html">食譜收藏</a>
                  </li>
                </ul>
              </li>
              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa-bar-chart-o"></i><img class="access-menu-icon1"
                    src="image/S__12066818.jpg"><span>我的課程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>
                  <li><a href="charts-chartjs.html">課程紀錄</a>
                  </li>
                  <li><a href="charts-morris.html">1</a>
                  </li>
                  <li><a href="charts-c3.html">2</a></li>
                </ul>
              </li>
              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa-map-marker"></i><img class="access-menu-icon1"
                    src="image/S__12066827.jpg"><span>直播/課程管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>
                  <li><a href="map-google.html">1</a>
                  </li>
                  <li><a href="map-vector.html">2</a>
                  </li>
                </ul>
              </li>

              <li class="sub-menu">
                <a href="javascript:void(0);"><i class="fa fa-file"></i><img class="access-menu-icon1"
                    src="image/S__12066826.jpg"><span>食譜管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
                    class="arrow fa fa-angle-right pull-right"></i></a>
                <ul>
                  <li><a href="pages-blank.html">1</a>
                  </li>
                  <li><a href="pages-login.html">2</a>
                  </li>
                  <li><a href="pages-sign-up.html">3</a>
                  </li>
                  <li><a href="pages-calendar.html">4</a>
                  </li>
                  <li><a href="pages-timeline.html">5</a>
                  </li>
                  <li><a href="pages-404.html">6</a>
                  </li>
                  <li><a href="pages-500.html">7</a>
                  </li>
                </ul>
              </li>
              <li class="sub-menu">
                <a href="typography.html"><i class="fa fa-text-height"></i><img class="access-menu-icon1"
                    src="image/S__12066825.jpg"><span>帳號設定</span></a>
              </li>
            </ul>
          </div>

        </aside>
        <div id="member-center-header">
          <!-- <div class="logo">
    <a href="#">Responsive Nav</a>
  </div>  -->
          <nav>
            <!-- <form class="search" action="search.php"> 
      <input name="q" placeholder="Search..." type="search">
    </form> -->
            <ul>
              <a href="">
                <li>
                  已成立
                </li>
              </a>
              <a href="">
                <li>
                  運送中
                </li>
              </a>
            </ul>
            </li>
            <a href="">
              <li>
                已完成
              </li>
            </a>
            <a href="">
              <li>
                取消訂單
              </li>
            </a>
            <a href="">
              <li>
                5
              </li>
            </a>
            </ul>
          </nav>
        </div>
        <div id="show-main-contain"></div>
      </div><!-- end of article contain -->
    </article>
  </main>
  <!--主要內容到此結束-->
  <!--*************************************以上開放編輯▲**************************************-->
  <div class="footer-copyright24">
    <footer>
      <!--這個是底部,你用不到-->
      <div class="footer-copyright">
        Copyright &copy; DA106-G4 Foodporn All rights reserved.
      </div>

    </footer>
  </div>
  <script>
    $("#leftside-navigation .sub-menu > a").click(function (e) {
      $("#leftside-navigation ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
        e.stopPropagation()
    })
  </script>
</body>

</html>














