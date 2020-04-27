<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
    String member_id =(String) session.getAttribute("member_id");
out.println(member_id);
%>
    
    
    
    
    
    
    
    
    
<!DOCTYPE html>

  <head>
<!--   <link rel="stylesheet" href="../css/member.css"> -->
<link rel="stylesheet" href="../../front-end/css/member.css">
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


  <link rel="stylesheet" href="../../css/frontEnd.css">
<!-- <link rel="stylesheet" href="css/frontEnd.css"> -->

  </head>


  <body>
  <header>
  <!--這個是上方選單,你用不到-->
  <div id="top-logo" class="logo"><a href="frontEnd.html" title="回首頁"><img class="logo-photo"
  src="../../test/image/Handdrawn%20Circle%20Logo.png" alt="logo"></a></div>
  <div class="function">
  <div class="function-list">
  <div class="member-center">
  <a href="#"><span class="member-center-spann">會員中心</span></a>
  </div>
  </div>
  <div class="function-list">
  <div class="shop-car">
  <a href="#"><img class="header-icon" src="../../test/image/shopping-cart-icon.png" alt="shopping-cart">
  <div class="herder-icon-span"><span class="shop-car-span">購物車</span></div>
  </a>
  </div>
  </div>
  <div class="login">
  <div class="function-list">
  <a href="#"><img class="header-icon" src="../../test/image/user-icon.png" alt="login-icon">
  <div class="herder-icon-span"><span class="login-span">登入</span></div>
  </a>
  </div>
  </div>
  </div><!-- end of function-->
  <nav role="navigation">
  <ul class="access-menu">
  <li>
  <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../test/image/recipe-icon.png"><span
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
  <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../test/image/livestream-icon.png"><span
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
  <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../test/image/shop-icon.png"><span
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
  <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../test/image/course-icon.png"><span
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
  <img class="login-close" src="../../test/image/close.png" alt="close">
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
  <a href="#top-logo"><img src="../../test/image/go-top-page.png" alt="go-top-page"></a>
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






<!--   <div id="leftside-navigation" class="nano"> -->
<!--   <ul class="nano-content"> -->
<!--   <li> -->
<!--   <i class="fa fa-dashboard"></i><img class="access-menu-icon1" -->
<!--   src="../../test/image/S__12066824.jpg">     <FORM METHOD="post" ACTION="member.do" id="myAccountFrom"> -->
<!--   		<span id="myAccount">我的帳戶</span>  -->
<!--        <input type="hidden" name="member_id" value="810001"> -->
<!--        <input type="hidden" name="action" value="memberTestToUpdate"> -->

<!--     </FORM> -->
<!--   </li> -->
  
  
  

     <div id="leftside-navigation" class="nano">
            <ul class="nano-content">
              <li>
              <a>
                <i class="fa fa-dashboard"></i><img class="access-menu-icon1"
                    src="../../test/image/S__12066824.jpg"><FORM METHOD="post" ACTION="member.do" id="myAccountFrom" style= "display: inline-block;"><span id="myAccount">我的帳戶</span>
              
               <input type="hidden" name="member_id" value="810001"> 
     <input type="hidden" name="action" value="memberTestToUpdate">
              </FORM>
              </a>
              </li>
  
  
  
<!--        <div id="leftside-navigation" class="nano"> -->
<!--             <ul class="nano-content"> -->
<!--               <li> -->
<!--                 <a href="index.html"><i class="fa fa-dashboard"></i><img class="access-menu-icon1" -->
<!--                     src="../../test/image/S__12066824.jpg"><span>我的帳戶&nbsp;&nbsp;&nbsp;&nbsp;></span></a> -->
<!--               </li> -->
  
  
  
 
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  <li class="sub-menu">
  <a href="javascript:void(0);"><i class="fa fa-cogs"></i><img class="access-menu-icon1"
  src="../../test/image/S__12066823.jpg"><span>購買清單&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
  class="arrow fa fa-angle-right pull-right"></i></a>
  <ul>

  <li><a href="/back-end/member/MemberPage.jsp">已成立</a>
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
  src="../../test/image/S__12066821.jpg"><span>我的錢包/點數&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> </span><i
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
  src="../../test/image/S__12066822.jpg"><span>瀏覽紀錄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
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
  src="../../test/image/S__12066820.jpg"><span>精選收藏&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
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
  src="../../test/image/S__12066818.jpg"><span>我的課程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
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
  src="../../test/image/S__12066827.jpg"><span>直播/課程管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
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
  src="../../test/image/S__12066826.jpg"><span>食譜管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
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
  src="../../test/image/S__12066825.jpg"><span>帳號設定</span></a>
  </li>
  </ul>
  </div>

  </aside>

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
  
  $("#myAccount").click(function(){
	$("#myAccountFrom").submit();  
  })
  
  </script>
  </body>

  </html>