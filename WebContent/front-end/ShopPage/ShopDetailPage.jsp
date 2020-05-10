<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.product.model.*"%>
<%@page import="com.order_detail.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	Vector<Order_detailVO> productCarlist;
	if ((Vector<Order_detailVO>) session.getAttribute("productCarlist") == null) {
		productCarlist = new Vector<Order_detailVO>();
//諾是空值建立一個購物車清單
	} else {
		productCarlist = (Vector<Order_detailVO>) session.getAttribute("productCarlist");
	}
	
	session.setAttribute("productCarlist", productCarlist);
	ProductService svc = new ProductService();
	String product_id = (String) request.getAttribute("product_id");

	ProductVO productvo = svc.getOneProduct(product_id); 
	/*注意新增*/
	session.setAttribute("productvo", productvo);

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Map<Integer, String> map = new HashMap<>();
	map.put(0, "未上架");
	map.put(1, "已上架");
	request.setAttribute("productstatus", map);
%>
<html>
productCarlistt${productCarlist}


<head>
    <script src="<%=request.getContextPath() %>/plugin/jquery-3.4.1.min.js"></script>


<link rel="stylesheet" href="../../css/semantic.min.css">
<script src="../../js/semantic.min.js"></script>




<link rel="stylesheet" href="css/productPage.css">



<script src="../../css/header-sider.css"></script>



        <script src="https://cdn.staticfile.org/echarts/4.7.0/echarts.min.js"></script>
        
        
     <link rel="stylesheet" type="text/css"href="css/productDetailPage.css"/>
     
     
     
 <link rel="stylesheet" type="text/css"href="<%=request.getContextPath()%>/css/CarMessage.css"/> 
 
 
 
 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/frontEnd.css">
 
 
 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/homePage.css">
 
    <script src="../../js/jquery-migrate-1.4.1.min.js"> </script>

<link rel="stylesheet" type="text/css" href="../../plugin/jquery-ui/jquery-ui.min.css"/>

<script src="../../plugin/jquery-ui/jquery-ui.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/slick-1.8.1/slick/slick-theme.css"/>
    <script type="text/javascript" src="../../css/slick-1.8.1/slick/slick.js"></script>
    
    
<title>購買頁面</title>
 <!-- 廣告連播套件 -->

</head>
<body>
	<header>
		<div id="top-logo" class="logo">
			<a href="<%=request.getContextPath() %>/index.jsp" title="回首頁"><img class="logo-photo"
				src="<%=request.getContextPath() %>/image/FoodPron_Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			<div class="function-list">
				<a href="#"></a>
				<div class="member-center">
				<a href="<%=request.getContextPath() %>/front-end/member/TestMemberHomepage.jsp" id="member-center">
					<span class="member-center-spann">會員中心</span>
					</a>
					
					
				</div>
				
			</div>
			<div class="function-list">
				<div class="menu">
					<input type="checkbox" href="#" class="menu-open menu-icon"
						name="menu-open" id="menu-open" /> <label
						class="menu-open-button" for="menu-open"> <span
						class="lines line-1"></span> <span class="lines line-2"></span> <span
						class="lines line-3"></span>
					</label>
				</div>
				
				
				
				
				
				
				
		
				
			<c:if test='${empty member_id}'>
			
				<div class="login">
					<a href="#"><img class="header-icon" src="<%=request.getContextPath() %>/image/user-icon.png"
						alt="login-icon">
						<div class="herder-icon-span">
							<span class="login-span">登入</span>
						</div> </a>
				</div>
			</c:if>	
			
			<c:if test='${not empty member_id}'>
	
					<div class="login">
				
					<a> <img class="header-icon" src="<%=request.getContextPath() %>/image/logout.png"
						alt="login-icon">
					
							<span class="login-span">登出</span>
							<form method="POST" action="member.do">	
								<input type="hidden" name="action" value="login">
								<input class="login-out" type="submit" name="action" style= "display:none;">
						</form>	 </a>
				</div>

				</c:if>	
				<script>
				$('.login-span').click(function(){
					$('.login-out').click();
				})
				
				
				
				
				</script>
		
				
				
				
				
				
				<!-- 購物車 -->
				<div class="shop-car">
					<a
						href="<%=request.getContextPath()%>/front-end/ShopPage/ProductPage?action=checktpage1">

						<div class="carmessage1">
							<img class="header-icon"
								src="<%=request.getContextPath()%>/image/shopping-cart-icon.png"
								alt="shopping-cart">


						</div>
						<div class="carmessage2" style="display: none">${fn:length(productCarlist)}</div>
						<div class="carmessagecircle" style="display: none">more</div>



						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>

						</div>
					</a>
				</div>
								<!-- 購物車 -->
				
				<div class="notice">
					<a href="#"><img class="header-icon" src="<%=request.getContextPath() %>/image/ico_notice.png"
						alt="notice-icon">
						<div class="herder-icon-span">
							<span class="notice-span">通知總覽</span>
						</div> </a>
				</div>

			</div>

		</div>
		<!-- end of function-->
		<nav id="navigation">
			<ul>
				<li class="dropdown"><a href="#"><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/recipe-icon.png"><span class="menu-span">食譜專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
<!-- 						<li><a href="#">特輯食譜</a></li> -->
<!-- 						<li><a href="#">推薦食譜</a></li> -->
					<c:if test="${memberVO.member_status==1}">
						<li><a href="front-end/recipe/addRecipe.jsp">建立食譜</a></li>
					</c:if>
	
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/livestream-icon.png"><span class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="<%=request.getContextPath()%>/front-end/livestream/livestreamHomePage.jsp"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
						<li><a href="livestream.jsp">直播預告</a></li>
					<c:if test="${memberVO.member_status==1}">
						<li><a href="front-end/livestream/livestream.jsp#${member_id}">開啟直播</a></li>
						
						</c:if>
					</ul></li>
				<li class="dropdown dropdown-shop"><a href="<%=request.getContextPath()%>/front-end/ShopPage/ShopHomePage.jsp"><img
						class="access-menu-icon" src="<%=request.getContextPath() %>/image/shop-icon.png"><span
						class="menu-span">食材商城</span></a>
					<ul id="dropdown-shop-ul">
						<li><a class="dropdown-first-a" href="<%=request.getContextPath()%>/front-end/ShopPage/ShopHomePage.jsp"><img
								class="dropdown-first-img"
								src="<%=request.getContextPath() %>/image/ico_gnav_recipes_salad.svg"><span
								class="dropdown-first-a-span">食材商城主頁</span></a></li>
								<%
			Map<Integer, String> producttype = new HashMap<Integer, String>();
			
				producttype.put(0, "水果類");
				producttype.put(1, "肉類");
				producttype.put(2, "蔬菜類");
				producttype.put(3, "乳品類");
				producttype.put(4, "魚貝類");
				producttype.put(5, "菇類");
				producttype.put(6, "穀物類");
				producttype.put(7, "澱粉類");
				producttype.put(8, "酒類");
				producttype.put(9, "調味料及香辛料類");
				producttype.put(10, "油脂類");
				producttype.put(11, "所有商品");				
				request.setAttribute("producttype", producttype);

			%>							<c:forEach var="producttype" items="${producttype}">
									<li><a href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?product_type=${producttype.value}&action=goProductPage">${producttype.value}</a></li>
			
										</c:forEach>
			

					</ul></li>
				<li class="dropdown"><a href="front-end/course/listAllCourse.jsp"><img class="access-menu-icon"
						src="<%=request.getContextPath() %>/image/course-icon.png"><span class="menu-span">料理課程</span></a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/front-end/course/listAllCourse.jsp" class="dropdown-first-a" href="front-end/course/listAllCourse.jsp"><img
								class="dropdown-first-img" src="<%=request.getContextPath() %>/image/ico_gnav_recipes_pot.svg"><span
								class="dropdown-first-a-span">課程主頁</span></a></li>
<!-- 						<li><a href="#">熱門課程</a></li> -->
					<c:if test="${memberVO.member_status==1}">
						<li><a href="<%=request.getContextPath() %>/front-end/course/addCourse.jsp">建立料理課程</a></li>
						</c:if>
					</ul></li>
			</ul>
		</nav>
	</header>
	<!-- end of header-->	
<!-- 登箱開始 -->

<c:if test='${empty member_id}'>
	<div class="login-wrap">
		<div class="login-html">
			<img class="login-close" src="image/close.png" alt="close"> <input
				id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
<form method="POST" action="member.do">
				<div class="sign-in-htm">
					<div class="group">
						<label for="user" class="label">Account</label> <input name="mem_id" id="user"
							type="text" class="input">
					</div>
					<div class="group">
						<label for="pass" class="label">Password</label> <input name="psw"  id="pass"
							type="password" class="input" data-type="password">
					</div>
<input type="hidden" name="action" value="login">
					<div class="group">
						<input id="check" type="checkbox" class="check" checked> <label
							for="check"><span class="icon"></span> Keep me Signed in</label>
					</div>
					<div class="group SignIn">
						<input type="submit" class="button" value="Sign In">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<a href="#forgot" class="foot-lnk-a">Forgot Password?</a>
					</div>
 
				</div>
</form>
<form method="POST" action="member.do">
				<div class="sign-up-htm">
					<div class="group">
						<label for="newuser" class="label">Account</label> <input
							id="newuser" type="text" class="input">
					</div>
					<div class="group">
						<label for="newpass" class="label">Password</label> <input
							id="newpass" type="password" name="password" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="renewpass" class="label">Repeat Password</label> <input
							id="renewpass" type="password" name="password2" class="input" data-type="password">
					</div>
					<div class="group">
						<label for="email" class="label">Email Address</label> <input
							id="email" type="email" name="email" class="input">
					</div>
<input type="hidden" name="action" value="insert">					
					<div class="group">
						<input type="submit" class="button" value="Sign Up">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<label for="tab-1">Already Member?</label>
					</div>
				</div>
</form>				
			</div>
		</div>
	</div>
</c:if>
	<!-- end of login-->
	
	
	

	
	<div class="pagetop">
		<img src="<%=request.getContextPath() %>/image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
	</div>
	<!-- end of pagetop-->









	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>	
	<script>	
		var r = $('#recipe_td').html()
		if (r == "null") {
			$('#recipe_td').css('display', 'none');
			$('#recipe_th').css('display', 'none');
			$('#product_idth').attr("colspan", 2);
			$('#product_idtd').attr("colspan", 2);
		} else {

		}
	</script>
	<script>
		function del() {
			var num = parseInt($('#countmun').text()) - 1;
			if (num < 1) {
				$('#countmun').text(1);
				$('#inquantity').val($('#countmun').text());

			} else {
				$('#countmun').text(num);
				$('#inquantity').val($('#countmun').text());

			}
		}
		function add() {
			var num = parseInt($('#countmun').text()) + 1;
			$('#countmun').text(num);
			$('#inquantity').val($('#countmun').text());

		}
	</script>
	<!-- 改 -->
		<div id="ShopPathLocation">

<div class="ui breadcrumb">
  <a class="section" href="<%=request.getContextPath() %>/index.html">Foodporn</a>
  <i class="right angle icon divider"></i>
  <a class="section" href="ShopHomePage.jsp">商城首頁</a>
  <i class="right angle icon divider"></i>
    <a class="section" href="ProductPage?product_type=${productvo.product_type}&action=goProductPage">${productvo.product_type} </a>
  <i class="right angle icon divider"></i>
  <div class="active section">${productvo.product_name} 
  
  </div>
</div>
</div>

<div id="ShopLsearch">
    <div class="ui icon input">
        <input type="text"  placeholder="你想找甚麼...">
        <i   class="inverted  circular search link icon"></i>
    </div>
</div>





<div id="shopProduct">
<!-- 品名 -->
<div id="producttitle">
    <font size=55px >${productvo.product_name} </font>
</div>
    <div id="Productcontext">
        <div id="productpicture">


            <img src="../../back-end/shop_product/Product_photoReader?product_id=${productvo.product_id}
			" alt="">

        </div>
        <div id="productdescript">
            <div id="descript1"><p>${productvo.content}</p></div>
            <div id="descript2"><front>價格:</front>${productvo.product_price}</div>
            <!-- 購物車 -->
            		
			
            <div id="descript3">
     
                     <div id="carbtnp">
    <div class="ui focus  input">
        <button id="decmun"class="ui  button red"><i class="minus icon "></i>  </button>

    <input  type="text" class="ui input"
            id="countmun" value="1">
        <button id="plusmun"class="ui  button red"><i class="plus icon"></i>  </button>

       </div>
</div>
<!--                   <FORM METHOD="post" ACTION="ShopCart" -->
<!--  	style="margin-bottom: 0px;"> -->
						<input type="hidden" id="inquantity" name="quantity" size="3"					
						value=1>
 <input 
						type="hidden" name="product_id"
						value="<%=productvo.getProduct_id()%>">
						 
						 <input
						type="hidden" name="product_price"
						value="<%=productvo.getProduct_price()%>"> 
						
						<input
						type="hidden" name="action" value="ADD">
<!-- 						<input type="submit" value="加入購物車"> -->
<!-- 				</FORM> -->
<!-- 				 <button  class="button ui addcar">加入購物車</button> -->
<div style="width: 80px" class="ui addcar vertical animated button" tabindex="0">
  <div class="hidden content">加入購物車</div>
  <div class="visible content">
    <i class="shop icon"></i>
  </div>
		</div>
				
				

<!--             數量：<input type="button" value="-" id="del" onclick="del()" /> -->
<!-- 					 <span name="quantity" -->
<!-- 						id="quantity">1</span>  -->
<!-- 						<input type="button" value="+" id="add" -->
<!-- 						onclick="add()" /> -->
            
</div>
        </div>

        <div class="prodictmessage">
            <div class="title">營養成分</div>
            <div class="hrdiv"> <hr></div>

            <div class="context">
                <table>
                

		<tr>
			<th>熱量</th>
			<th>碳水化合物</th>

			<th>蛋白質</th>
			<th>脂質</th>
			<th>維生素B</th>
			<th>維生素C</th>
			<th>鈉含量</th>
			<th>植物纖維</th>
		</tr>
		<tr>
			<td><%=productvo.getCalorie()%></td>
			<td><%=productvo.getProtein()%></td>
			<td><%=productvo.getProtein()%></td>
			<td><%=productvo.getFat()%></td>
			<td><%=productvo.getVitamin_B()%></td>
			<td><%=productvo.getVitamin_C()%></td>
			<td><%=productvo.getSalt()%></td>
			<td><%=productvo.getVagetbale()%></td>
		</tr>
	</table>
	<div id="chart" style="width: 500px;height:500px;"></div>
	

            </div>
        </div>
        
              </div></div>
        
  <div>
	        <c:if test="${member_id!=null}">
	                <%@ include file="ProductCarousel.jsp" %>


	        </c:if>
	        
	        
	        </div>
        



	
	<div id="buymessage" style="border-color:  #e4002b"  title="購買確認">
    <div>購買成功</div>
<div>是否前往購物車查看:</div>
    <div><button class="ui cancelshopcart button"><i class="shopping red arrow alternate circle left
 icon"></i> 繼續購物 </button>
 
 
 <button class="ui  button"><a href="<%=request.getContextPath() %>/front-end/ShopPage/ProductPage?action=checktpage1">前往購物車 </a> <i class="shopping cart red
 icon"></i></button></div>

 </div>
<footer>
		<div  class="footer-bg">
			<div class="footer-murmur">
				<img src="<%=request.getContextPath() %>/image/FoodPron_Logo_white.png" alt="logo"
					data-aos="zoom-in">
				<ul>
					<li class="footer-li-fist">逛其他</li>
					<li>直播專區</li>
					<li>食材商城</li>
					<li>料理課程</li>
				</ul>
				<ul>
					<li class="footer-li-fist">逛食譜</li>
					<li>熱門食譜</li>
					<li>新到食譜</li>
					<li>全部分類</li>
				</ul>
				<ul>
					<li class="footer-li-fist">會員服務</li>
					<li>我的收藏</li>
					<li>帳號設定</li>
					<li>忘記密碼</li>
					<li>我的訂單</li>
				</ul>
				<ul>
					<li class="footer-li-fist">關於我們</li>
					<li>公司資訊</li>
					<li>品牌資產</li>
					<li>服務條款</li>
					<li>隱私權政策</li>
				</ul>
			</div>
		</div>
		<div class="footer-copyright">Copyright &copy; DA106-G4 Foodporn
			All rights reserved.</div>




	</footer>


	<script>

						if($(".carmessage2").html()>0 && $(".carmessage2").html()<10){
							$(".carmessagecircle").hide();
							$(".carmessage2").show();
						}
						else if($(".carmessage2").html()>9){
							$(".carmessage2").hide();
							$(".carmessagecircle").show();
						}
						else{
							$(".carmessagecircle").hide();
							$(".carmessage2").hide();

						}
						
						</script>
	
	<!-- JavasScript-->
	<!-- JavasScript-->
	<!-- JavasScript for Sider -->
	<script src="javascript/header_sider.js" type="text/javascript"
		charset="utf-8"></script>
	<!-- JavasScript for LogForm -->
	<script src="javascript/loginForm.js" type="text/javascript"
		charset="utf-8"></script>

	<!-- JavasScript for BackTop -->
	<script>
		$('#BackTop').click(function() {
			$('html,body').animate({
				scrollTop : 0
			}, 333);
		});
		$(window).scroll(function() {
			if ($(this).scrollTop() > 450) {
				$('#BackTop').fadeIn(222);
			} else {
				$('#BackTop').stop().fadeOut(222);
			}
		});

		//畫面捲動時隱藏
		window.onresize = function() {
			if ($(window).width() > 767) {
				$("nav").show();
			} else
				$("nav").hide();
		}
	</script>

	<!-- JavasScript for shop-slide -->
	<script>
		$(".shop-slide-article-and-photo").hover(function() {
			$(".shop-slide-article-and-photo-img").removeClass('imgSmall');
			$(".shop-slide-article-and-photo-img").addClass('imgScale');
		});
		$(".shop-slide-article-and-photo").mouseleave(function() {
			$(".shop-slide-article-and-photo-img").addClass('imgSmall');
		});

		/* 直播專區 right scale 效果 */
		$(".article-section-description-livestream-right").hover(function() {
			$(".livestream-right-img", this).addClass('imgScale');
		});
		$(".imglist-li a").hover(function() {
			$(".imglist-li-span", this).removeClass('textRecoverColor');
			$(".imglist-li-img", this).removeClass('imgSmall');
			$(".imglist-li-img", this).addClass('imgScale');
			$(".imglist-li-span", this).addClass('textChangeColor');
		});
		$(".imglist-li a").mouseleave(function() {
			$(".imglist-li-span", this).addClass('textRecoverColor');
			$(".imglist-li-img", this).addClass('imgSmall');
		});
	</script>
	<script>
		$(".menu-open").on("click", function() {
			$("nav").slideToggle();
			$(this).toggleClass("active");
			$('body,html').toggleClass('add')
		});
	</script>

<script>


    $('#decmun').click(function () {
        let count =$('#countmun').val();
        if(count<2) {
            $('#countmun').val(1);
            $('#inquantity').val(1);
        }
else{
        $('#countmun').val(parseInt(count)-1);
        $('#inquantity').val(parseInt(count)-1);
}
    });
$('#plusmun').click(function () {
    let count =$('#countmun').val();
    $('#inquantity').val(parseInt(count)+1);

        $('#countmun').val(parseInt(count)+1);

});

</script>

<script>
        $( "#buymessage" ).dialog({
        	
            autoOpen: false,
            minWidth: 205,
            width: 340,
            show: 'fade',
            hide: "blind"
            


        });
	


    $(".addcar").click(function () {

        $("#buymessage").dialog("open");

    });

 
    
    $('.cancelshopcart').click(function () {
        $("#buymessage").dialog("close");

    });

  
</script>

	


</body>



</html>
       
		<script>

		function changecarmun(data) {
			if(data==null){
					$(".carmessage2").hide();
					$(".carmessagecircle").hide();
					
				}
				else if(data>9){

					$(".carmessage2").hide();
					$(".carmessagecircle").show();
		         				
				}
				else if(data<9&&data>0){

					$(".carmessage2").html(data);
					$(".carmessage2").show();
					$(".carmessagecircle").hide();
					
					
				}
				else{
					alert("else"+data);

					$(".carmessage2").hide();
					$(".carmessagecircle").hide();
				}
        }
		
		
		
				$(".addcar").click(function(){
					$.ajax({
			         	url:'ShopCart',
			         	type:"POST",
			         	data:{
			         		product_price:"<%=productvo.getProduct_price()%>",
			         		action:"ADD",
			         		product_id:'<%=productvo.getProduct_id()%>',
			         		quantity:$("#inquantity").val()
			 
			         	},
			         	success:function(data){
			         		changecarmun(data);
			        
			         	}

			});

				});
					 
			
				</script>
				<!-- 六圍 -->
<script>
	var radar_chart = echarts.init(document.getElementById("chart"));
	var option = {
		radar : {
			name : {
				textStyle : {
					color : '#fff',
					backgroundColor : '#e4002b',
					fontsize : 50,
					borderRadius : 7,
					padding : [ 3, 5 ]
				}
			},
			indicator : [ {
				name : '熱量',
				max : 500
			}, {
				name : '蛋白質',
				max : 100
			}, {
				name : '碳水化合物',
				max : 500
			}, {
				name : '脂質',
				max : 40
			}, {
				name : '鈉含量(mg)',
				max : 100
			}, {
				name : '膳食纖維',
				max : 10
			} ]
		},
		series : [ {
			type : 'radar',
			itemStyle : {
				normal : {
					color : '#e4002b'
				}
			},
			data : [
					{
						value : [
<%=productvo.getCalorie()%>
	,
<%=productvo.getProtein()%>
	,
<%=productvo.getCarbohydrate()%>
	,
<%=productvo.getFat()%>
	,
<%=productvo.getSalt()%>
	,
<%=productvo.getVagetbale()%>
	],
						name : '食材六圍圖',
						areaStyle : {
							normal : {
								color : 'rgba(51,0,255,0.5)'
							}
						}
					}, ]
		} ]
	};
	radar_chart.setOption(option);
</script>
<style>
    #buymessage{
        margin-top: 20px;
        height: 150px;
    }
    #buymessage div:nth-child(3){
        padding-top: 20px;
        width: 100%;
        margin-top: 10px;
        height: 70px;
    }
    #buymessage div:nth-child(3) button{
width: 140px;

    }
    #buymessage div:nth-child(3) button:nth-child(2){
        margin: 0px;
        float: right;
    }


</style>