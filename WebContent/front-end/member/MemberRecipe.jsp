
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.recipe.model.*"%>
<%
String member_id =(String) session.getAttribute("member_id");

MemberService memberSvc = new MemberService();
MemberVO membervo = memberSvc.getOneMember(member_id);










String member_name =(String) session.getAttribute("member_name");
	

	MemberService pSvc = new MemberService();

	
	out.println(member_name);
	out.println(member_id);
	
	
	
	

	RecipeService recipeService = new RecipeService();
	List<RecipeVO> list = recipeService.getChefCooked(member_id);
	pageContext.setAttribute("list", list);
	
	
	MemberVO memberVO = memberSvc.getOneMember(member_id);
	MemberVO memberVO1 =(MemberVO) session.getAttribute("memberVO");
	

		

	pageContext.setAttribute("member_status", memberVO1.getMember_status());
	pageContext.setAttribute("memberVO1", memberVO1);
	
	
	
	
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0">
<title>Foodporn</title>
<link rel="stylesheet" href="../../css/frontEnd.css">
<link rel="stylesheet" href="../../css/header-sider.css">
<link rel="stylesheet" href="../../slick/slick.css">
<link rel="stylesheet" href="../../slick/slick-theme.css">
<link rel="stylesheet" href="../../css/homePage.css">
<link rel="stylesheet" href="../../css/searchRecipeCSS.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<link rel="icon" href="../../image/head-FoodPron_Logo.ico"
	type="../../image/x-icon">
<link rel="shortcut icon" href="../../image/head-FoodPron_Logo.ico"
	type="../../image/x-icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="../../slick/slick.js" type="text/javascript"
	charset="utf-8"></script>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../recipe/recipeCSS/recipeList.css">



<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>








<script type="text/javascript">
    
    
      $().ready(function(){
      
      $( "#date" ).datepicker({
      changeMonth: true,     //可以限定是否需要月份的下拉是選單，預設是沒有
      changeYear: true, //可以限定是否需要年份的下拉是選單，預設是沒有
      dateFormat: 'yy/mm/dd',//所顯示的default
      showOn: "button",            
      buttonImage: "./resources/images/icon/calendar.gif",
      buttonImageOnly: true
      });
      });
       </script>


<script type="text/javascript">
      /*
      * jQuery taiwan address plugin
      * 台灣地址 下拉式連動選單
      * Version 1.1.0 (07/11/2012)
      * @requires jQuery v1.4.2 or later
      *
      * Copyright (c) 2012 Qi-Liang Wen 啟良
      */
      (function ($) {
          var city_list = [
              { pinyin: 'taipeicity', name: '台北市' }, { pinyin: 'xinbeicity', name: '新北市' }, { pinyin: 'keelungcity', name: '基隆市' },
              { pinyin: 'taoyuancounty', name: '桃園市' }, { pinyin: 'hsinchucounty', name: '新竹縣' }, { pinyin: 'hsinchucity', name: '新竹市' },
              { pinyin: 'miaolicounty', name: '苗栗縣' }, { pinyin: 'yilancounty', name: '宜蘭縣' }, { pinyin: 'taichungcity', name: '台中市' },
              { pinyin: 'changhuacounty', name: '彰化縣' }, { pinyin: 'nantoucounty', name: '南投縣' }, { pinyin: 'yunlincounty', name: '雲林縣' },
              { pinyin: 'chiayicity', name: '嘉義市' }, { pinyin: 'chiayicounty', name: '嘉義縣' }, { pinyin: 'tainancity', name: '台南市' },
              { pinyin: 'kaohsiungcity', name: '高雄市' }, { pinyin: 'pingtungcounty', name: '屏東縣' }, { pinyin: 'hualiencounty', name: '花蓮縣' },
              { pinyin: 'taitungcounty', name: '台東縣' }, { pinyin: 'penghucounty', name: '澎湖縣' }, { pinyin: 'kinmencounty', name: '金門縣' },
              { pinyin: 'lienchiangcounty', name: '連江縣' }
              ]
          var dist_list = [
              { city: 'keelungcity', pinyin: 'qidu', name: '七堵區' },
              { city: 'penghucounty', pinyin: 'qimei', name: '七美鄉' },
              { city: 'tainancity', pinyin: 'qigu', name: '七股區' },
              { city: 'pingtungcounty', pinyin: 'sandimen', name: '三地門鄉' },
              { city: 'xinbeicity', pinyin: 'sanxia', name: '三峽區' },
              { city: 'yilancounty', pinyin: 'sanxing', name: '三星鄉' },
              { city: 'kaohsiungcity', pinyin: 'sanmin', name: '三民區' },
              { city: 'miaolicounty', pinyin: 'sanwan', name: '三灣鄉' },
              { city: 'miaolicounty', pinyin: 'sanyi', name: '三義鄉' },
              { city: 'xinbeicity', pinyin: 'sanzhi', name: '三芝區' },
              { city: 'xinbeicity', pinyin: 'sanchong', name: '三重區' },
              { city: 'tainancity', pinyin: 'xiaying', name: '下營區' },
              { city: 'taichungcity', pinyin: 'central', name: '中區' },
              { city: 'xinbeicity', pinyin: 'zhonghe', name: '中和區' },
              { city: 'chiayicounty', pinyin: 'zhongpu', name: '中埔鄉' },
              { city: 'taoyuancounty', pinyin: 'zhongli', name: '中壢區' },
              { city: 'nantoucounty', pinyin: 'zhongliao', name: '中寮鄉' },
              { city: 'keelungcity', pinyin: 'zhongshan', name: '中山區' },
              { city: 'taipeicity', pinyin: 'zhongshan', name: '中山區' },
              { city: 'keelungcity', pinyin: 'zhongzheng', name: '中正區' },
              { city: 'taipeicity', pinyin: 'zhongzheng', name: '中正區' },
              { city: 'tainancity', pinyin: 'westcentral', name: '中西區' },
              { city: 'pingtungcounty', pinyin: 'jiuru', name: '九如鄉' },
              { city: 'yunlincounty', pinyin: 'erlun', name: '二崙鄉' },
              { city: 'changhuacounty', pinyin: 'erlin', name: '二林鎮' },
              { city: 'changhuacounty', pinyin: 'ershui', name: '二水鄉' },
              { city: 'hsinchucounty', pinyin: 'wufeng', name: '五峰鄉' },
              { city: 'yilancounty', pinyin: 'wujie', name: '五結鄉' },
              { city: 'xinbeicity', pinyin: 'wugu', name: '五股區' },
              { city: 'tainancity', pinyin: 'rende', name: '仁德區' },
              { city: 'keelungcity', pinyin: 'renai', name: '仁愛區' },
              { city: 'nantoucounty', pinyin: 'renai', name: '仁愛鄉' },
              { city: 'kaohsiungcity', pinyin: 'renwu', name: '仁武區' },
              { city: 'changhuacounty', pinyin: 'shengang', name: '伸港鄉' },
              { city: 'pingtungcounty', pinyin: 'jiadong', name: '佳冬鄉' },
              { city: 'tainancity', pinyin: 'jiali', name: '佳里區' },
              { city: 'pingtungcounty', pinyin: 'laiyi', name: '來義鄉' },
              { city: 'keelungcity', pinyin: 'xinyi', name: '信義區' },
              { city: 'taipeicity', pinyin: 'xinyi', name: '信義區' },
              { city: 'nantoucounty', pinyin: 'xinyi', name: '信義鄉' },
              { city: 'yunlincounty', pinyin: 'yuanchang', name: '元長鄉' },
              { city: 'hualiencounty', pinyin: 'guangfu', name: '光復鄉' },
              { city: 'pingtungcounty', pinyin: 'neipu', name: '內埔鄉' },
              { city: 'taipeicity', pinyin: 'neihu', name: '內湖區' },
              { city: 'kaohsiungcity', pinyin: 'neimen', name: '內門區' },
              { city: 'taoyuancounty', pinyin: 'bade', name: '八德區' },
              { city: 'xinbeicity', pinyin: 'bali', name: '八里區' },
              { city: 'miaolicounty', pinyin: 'gongguan', name: '公館鄉' },
              { city: 'tainancity', pinyin: 'liujia', name: '六甲區' },
              { city: 'chiayicounty', pinyin: 'liujiao', name: '六腳鄉' },
              { city: 'kaohsiungcity', pinyin: 'liugui', name: '六龜區' },
              { city: 'yilancounty', pinyin: 'dongshan', name: '冬山鄉' },
              { city: 'kaohsiungcity', pinyin: 'qianjin', name: '前金區' },
              { city: 'kaohsiungcity', pinyin: 'qianzhen', name: '前鎮區' },
              { city: 'hsinchucity', pinyin: 'north', name: '北區' },
              { city: 'taichungcity', pinyin: 'north', name: '北區' },
              { city: 'tainancity', pinyin: 'north', name: '北區' },
              { city: 'hsinchucounty', pinyin: 'beipu', name: '北埔鄉' },
              { city: 'taichungcity', pinyin: 'beitun', name: '北屯區' },
              { city: 'taipeicity', pinyin: 'beitou', name: '北投區' },
              { city: 'changhuacounty', pinyin: 'beidou', name: '北斗鎮' },
              { city: 'yunlincounty', pinyin: 'beigang', name: '北港鎮' },
              { city: 'lienchiangcounty', pinyin: 'beigan', name: '北竿鄉' },
              { city: 'tainancity', pinyin: 'beimen', name: '北門區' },
              { city: 'taitungcounty', pinyin: 'beinan', name: '卑南鄉' },
              { city: 'hualiencounty', pinyin: 'zhuoxi', name: '卓溪鄉' },
              { city: 'miaolicounty', pinyin: 'zhuolan', name: '卓蘭鎮' },
              { city: 'tainancity', pinyin: 'nanhua', name: '南化區' },
              { city: 'taichungcity', pinyin: 'south', name: '南區' },
              { city: 'tainancity', pinyin: 'south', name: '南區' },
              { city: 'taichungcity', pinyin: 'nantun', name: '南屯區' },
              { city: 'pingtungcounty', pinyin: 'nanzhou', name: '南州鄉' },
              { city: 'miaolicounty', pinyin: 'nanzhuang', name: '南庄鄉' },
              { city: 'nantoucounty', pinyin: 'nantou', name: '南投市' },
              { city: 'nanhaiislands', pinyin: 'nansha', name: '南沙群島' },
              { city: 'taipeicity', pinyin: 'nangang', name: '南港區' },
              { city: 'yilancounty', pinyin: 'nanao', name: '南澳鄉' },
              { city: 'lienchiangcounty', pinyin: 'nangan', name: '南竿鄉' },
              { city: 'yunlincounty', pinyin: 'kouhu', name: '口湖鄉' },
              { city: 'yunlincounty', pinyin: 'gukeng', name: '古坑鄉' },
              { city: 'taitungcounty', pinyin: 'taitung', name: '台東市' },
              { city: 'yunlincounty', pinyin: 'taixi', name: '台西鄉' },
              { city: 'hualiencounty', pinyin: 'jian', name: '吉安鄉' },
              { city: 'nantoucounty', pinyin: 'mingjian', name: '名間鄉' },
              { city: 'taichungcity', pinyin: 'houli', name: '后里區' },
              { city: 'taichungcity', pinyin: 'heping', name: '和平區' },
              { city: 'changhuacounty', pinyin: 'hemei', name: '和美鎮' },
              { city: 'yilancounty', pinyin: 'yuanshan', name: '員山鄉' },
              { city: 'changhuacounty', pinyin: 'yuanlin', name: '員林鎮' },
              { city: 'tainancity', pinyin: 'shanhua', name: '善化區' },
              { city: 'yunlincounty', pinyin: 'sihu', name: '四湖鄉' },
              { city: 'nantoucounty', pinyin: 'guoxing', name: '國姓鄉' },
              { city: 'xinbeicity', pinyin: 'tucheng', name: '土城區' },
              { city: 'yunlincounty', pinyin: 'tuku', name: '土庫鎮' },
              { city: 'xinbeicity', pinyin: 'pinglin', name: '坪林區' },
              { city: 'changhuacounty', pinyin: 'puxin', name: '埔心鄉' },
              { city: 'nantoucounty', pinyin: 'puli', name: '埔里鎮' },
              { city: 'changhuacounty', pinyin: 'puyan', name: '埔鹽鄉' },
              { city: 'changhuacounty', pinyin: 'pitou', name: '埤頭鄉' },
              { city: 'taipeicity', pinyin: 'shilin', name: '士林區' },
              { city: 'yilancounty', pinyin: 'zhuangwei', name: '壯圍鄉' },
              { city: 'hualiencounty', pinyin: 'shoufeng', name: '壽豐鄉' },
              { city: 'taichungcity', pinyin: 'waipu', name: '外埔區' },
              { city: 'tainancity', pinyin: 'danei', name: '大內區' },
              { city: 'taipeicity', pinyin: 'datong', name: '大同區' },
              { city: 'yilancounty', pinyin: 'datong', name: '大同鄉' },
              { city: 'taoyuancounty', pinyin: 'dayuan', name: '大園區' },
              { city: 'changhuacounty', pinyin: 'dacheng', name: '大城鄉' },
              { city: 'chiayicounty', pinyin: 'dapu', name: '大埔鄉' },
              { city: 'yunlincounty', pinyin: 'dapi', name: '大埤鄉' },
              { city: 'taichungcity', pinyin: 'daan', name: '大安區' },
              { city: 'taipeicity', pinyin: 'daan', name: '大安區' },
              { city: 'kaohsiungcity', pinyin: 'daliao', name: '大寮區' },
              { city: 'changhuacounty', pinyin: 'dacun', name: '大村鄉' },
              { city: 'chiayicounty', pinyin: 'dalin', name: '大林鎮' },
              { city: 'kaohsiungcity', pinyin: 'dashu', name: '大樹區' },
              { city: 'taitungcounty', pinyin: 'dawu', name: '大武鄉' },
              { city: 'miaolicounty', pinyin: 'dahu', name: '大湖鄉' },
              { city: 'taoyuancounty', pinyin: 'daxi', name: '大溪區' },
              { city: 'taichungcity', pinyin: 'dajia', name: '大甲區' },
              { city: 'kaohsiungcity', pinyin: 'dashe', name: '大社區' },
              { city: 'taichungcity', pinyin: 'dadu', name: '大肚區' },
              { city: 'taichungcity', pinyin: 'dali', name: '大里區' },
              { city: 'taichungcity', pinyin: 'daya', name: '大雅區' },
              { city: 'chiayicounty', pinyin: 'taibao', name: '太保市' },
              { city: 'taichungcity', pinyin: 'taiping', name: '太平區' },
              { city: 'taitungcounty', pinyin: 'taimali', name: '太麻里鄉' },
              { city: 'tainancity', pinyin: 'xuejia', name: '學甲區' },
              { city: 'tainancity', pinyin: 'annan', name: '安南區' },
              { city: 'tainancity', pinyin: 'anding', name: '安定區' },
              { city: 'tainancity', pinyin: 'anping', name: '安平區' },
              { city: 'keelungcity', pinyin: 'anle', name: '安樂區' },
              { city: 'tainancity', pinyin: 'guantian', name: '官田區' },
              { city: 'yilancounty', pinyin: 'yilan', name: '宜蘭市' },
              { city: 'hualiencounty', pinyin: 'fuli', name: '富里鄉' },
              { city: 'hsinchucounty', pinyin: 'baoshan', name: '寶山鄉' },
              { city: 'tainancity', pinyin: 'jiangjun', name: '將軍區' },
              { city: 'kaohsiungcity', pinyin: 'xiaogang', name: '小港區' },
              { city: 'hsinchucounty', pinyin: 'jianshi', name: '尖石鄉' },
              { city: 'pingtungcounty', pinyin: 'pingtung', name: '屏東市' },
              { city: 'tainancity', pinyin: 'shanshang', name: '山上區' },
              { city: 'kaohsiungcity', pinyin: 'gangshan', name: '岡山區' },
              { city: 'hsinchucounty', pinyin: 'emei', name: '峨眉鄉' },
              { city: 'pingtungcounty', pinyin: 'kanding', name: '崁頂鄉' },
              { city: 'yunlincounty', pinyin: 'lunbei', name: '崙背鄉' },
              { city: 'kaohsiungcity', pinyin: 'zuoying', name: '左營區' },
              { city: 'tainancity', pinyin: 'zuozhen', name: '左鎮區' },
              { city: 'chiayicounty', pinyin: 'budai', name: '布袋鎮' },
              { city: 'xinbeicity', pinyin: 'pingxi', name: '平溪區' },
              { city: 'taoyuancounty', pinyin: 'pingzhen', name: '平鎮區' },
              { city: 'taitungcounty', pinyin: 'yanping', name: '延平鄉' },
              { city: 'kaohsiungcity', pinyin: 'mituo', name: '彌陀區' },
              { city: 'changhuacounty', pinyin: 'changhua', name: '彰化市' },
              { city: 'tainancity', pinyin: 'houbi', name: '後壁區' },
              { city: 'miaolicounty', pinyin: 'houlong', name: '後龍鎮' },
              { city: 'taoyuancounty', pinyin: 'fuxing', name: '復興區' },
              { city: 'pingtungcounty', pinyin: 'hengchun', name: '恆春鎮' },
              { city: 'taitungcounty', pinyin: 'chenggong', name: '成功鎮' },
              { city: 'taipeicity', pinyin: 'wenshan', name: '文山區' },
              { city: 'yunlincounty', pinyin: 'douliu', name: '斗六市' },
              { city: 'yunlincounty', pinyin: 'dounan', name: '斗南鎮' },
              { city: 'tainancity', pinyin: 'xinhua', name: '新化區' },
              { city: 'pingtungcounty', pinyin: 'xinyuan', name: '新園鄉' },
              { city: 'hualiencounty', pinyin: 'xincheng', name: '新城鄉' },
              { city: 'hsinchucounty', pinyin: 'xinpu', name: '新埔鎮' },
              { city: 'pingtungcounty', pinyin: 'xinpi', name: '新埤鄉' },
              { city: 'taoyuancounty', pinyin: 'xinwu', name: '新屋區' },
              { city: 'tainancity', pinyin: 'xinshi', name: '新市區' },
              { city: 'xinbeicity', pinyin: 'xindian', name: '新店區' },
              { city: 'chiayicounty', pinyin: 'xingang', name: '新港鄉' },
              { city: 'tainancity', pinyin: 'xinying', name: '新營區' },
              { city: 'taichungcity', pinyin: 'xinshe', name: '新社區' },
              { city: 'kaohsiungcity', pinyin: 'xinxing', name: '新興區' },
              { city: 'xinbeicity', pinyin: 'xinzhuang', name: '新莊區' },
              { city: 'hsinchucounty', pinyin: 'xinfeng', name: '新豐鄉' },
              { city: 'kaohsiungcity', pinyin: 'qishan', name: '旗山區' },
              { city: 'kaohsiungcity', pinyin: 'qijin', name: '旗津區' },
              { city: 'pingtungcounty', pinyin: 'chunri', name: '春日鄉' },
              { city: 'keelungcity', pinyin: 'nuannuan', name: '暖暖區' },
              { city: 'penghucounty', pinyin: 'wangan', name: '望安鄉' },
              { city: 'chiayicounty', pinyin: 'puzi', name: '朴子市' },
              { city: 'kaohsiungcity', pinyin: 'shanlin', name: '杉林區' },
              { city: 'taichungcity', pinyin: 'dongshi', name: '東勢區' },
              { city: 'yunlincounty', pinyin: 'dongshi', name: '東勢鄉' },
              { city: 'chiayicity', pinyin: 'east', name: '東區' },
              { city: 'hsinchucity', pinyin: 'east', name: '東區' },
              { city: 'taichungcity', pinyin: 'east', name: '東區' },
              { city: 'tainancity', pinyin: 'east', name: '東區' },
              { city: 'tainancity', pinyin: 'dongshan', name: '東山區' },
              { city: 'lienchiangcounty', pinyin: 'dongyin', name: '東引鄉' },
              { city: 'nanhaiislands', pinyin: 'dongsha', name: '東沙群島' },
              { city: 'taitungcounty', pinyin: 'donghe', name: '東河鄉' },
              { city: 'pingtungcounty', pinyin: 'donggang', name: '東港鎮' },
              { city: 'chiayicounty', pinyin: 'dongshi', name: '東石鄉' },
              { city: 'taipeicity', pinyin: 'songshan', name: '松山區' },
              { city: 'xinbeicity', pinyin: 'banqiao', name: '板橋區' },
              { city: 'pingtungcounty', pinyin: 'fangliao', name: '枋寮鄉' },
              { city: 'pingtungcounty', pinyin: 'fangshan', name: '枋山鄉' },
              { city: 'yunlincounty', pinyin: 'linnei', name: '林內鄉' },
              { city: 'xinbeicity', pinyin: 'linkou', name: '林口區' },
              { city: 'kaohsiungcity', pinyin: 'linyuan', name: '林園區' },
              { city: 'pingtungcounty', pinyin: 'linbian', name: '林邊鄉' },
              { city: 'tainancity', pinyin: 'liuying', name: '柳營區' },
              { city: 'taoyuancounty', pinyin: 'taoyuan', name: '桃園區' },
              { city: 'kaohsiungcity', pinyin: 'taoyuan', name: '桃源區' },
              { city: 'chiayicounty', pinyin: 'meishan', name: '梅山鄉' },
              { city: 'kaohsiungcity', pinyin: 'ziguan', name: '梓官區' },
              { city: 'taichungcity', pinyin: 'wuqi', name: '梧棲區' },
              { city: 'taoyuancounty', pinyin: 'yangmei', name: '楊梅區' },
              { city: 'kaohsiungcity', pinyin: 'nanzi', name: '楠梓區' },
              { city: 'tainancity', pinyin: 'nanxi', name: '楠西區' },
              { city: 'xinbeicity', pinyin: 'shulin', name: '樹林區' },
              { city: 'kaohsiungcity', pinyin: 'qiaotou', name: '橋頭區' },
              { city: 'hsinchucounty', pinyin: 'hengshan', name: '橫山鄉' },
              { city: 'tainancity', pinyin: 'guiren', name: '歸仁區' },
              { city: 'chiayicounty', pinyin: 'minxiong', name: '民雄鄉' },
              { city: 'chiayicounty', pinyin: 'shuishang', name: '水上鄉' },
              { city: 'yunlincounty', pinyin: 'shuilin', name: '水林鄉' },
              { city: 'nantoucounty', pinyin: 'shuili', name: '水里鄉' },
              { city: 'xinbeicity', pinyin: 'yonghe', name: '永和區' },
              { city: 'kaohsiungcity', pinyin: 'yongan', name: '永安區' },
              { city: 'tainancity', pinyin: 'yongkang', name: '永康區' },
              { city: 'changhuacounty', pinyin: 'yongjing', name: '永靖鄉' },
              { city: 'xinbeicity', pinyin: 'xizhi', name: '汐止區' },
              { city: 'taitungcounty', pinyin: 'chishang', name: '池上鄉' },
              { city: 'taichungcity', pinyin: 'shalu', name: '沙鹿區' },
              { city: 'miaolicounty', pinyin: 'taian', name: '泰安鄉' },
              { city: 'xinbeicity', pinyin: 'taishan', name: '泰山區' },
              { city: 'pingtungcounty', pinyin: 'taiwu', name: '泰武鄉' },
              { city: 'taitungcounty', pinyin: 'haiduan', name: '海端鄉' },
              { city: 'xinbeicity', pinyin: 'danshui', name: '淡水區' },
              { city: 'xinbeicity', pinyin: 'shenkeng', name: '深坑區' },
              { city: 'taichungcity', pinyin: 'qingshui', name: '清水區' },
              { city: 'kaohsiungcity', pinyin: 'hunei', name: '湖內區' },
              { city: 'hsinchucounty', pinyin: 'hukou', name: '湖口鄉' },
              { city: 'penghucounty', pinyin: 'huxi', name: '湖西鄉' },
              { city: 'chiayicounty', pinyin: 'xikou', name: '溪口鄉' },
              { city: 'changhuacounty', pinyin: 'xizhou', name: '溪州鄉' },
              { city: 'changhuacounty', pinyin: 'xihu', name: '溪湖鎮' },
              { city: 'pingtungcounty', pinyin: 'manzhou', name: '滿州鄉' },
              { city: 'taichungcity', pinyin: 'tanzi', name: '潭子區' },
              { city: 'pingtungcounty', pinyin: 'chaozhou', name: '潮州鎮' },
              { city: 'kinmencounty', pinyin: 'lieyu', name: '烈嶼鄉' },
              { city: 'xinbeicity', pinyin: 'wulai', name: '烏來區' },
              { city: 'kinmencounty', pinyin: 'wuqiu', name: '烏坵鄉' },
              { city: 'taichungcity', pinyin: 'wuri', name: '烏日區' },
              { city: 'kaohsiungcity', pinyin: 'yanchao', name: '燕巢區' },
              { city: 'pingtungcounty', pinyin: 'mudan', name: '牡丹鄉' },
              { city: 'pingtungcounty', pinyin: 'shizi', name: '獅子鄉' },
              { city: 'miaolicounty', pinyin: 'shitan', name: '獅潭鄉' },
              { city: 'tainancity', pinyin: 'yujing', name: '玉井區' },
              { city: 'hualiencounty', pinyin: 'yuli', name: '玉里鎮' },
              { city: 'pingtungcounty', pinyin: 'liuqiu', name: '琉球鄉' },
              { city: 'hualiencounty', pinyin: 'ruisui', name: '瑞穗鄉' },
              { city: 'xinbeicity', pinyin: 'ruifang', name: '瑞芳區' },
              { city: 'pingtungcounty', pinyin: 'majia', name: '瑪家鄉' },
              { city: 'changhuacounty', pinyin: 'tianzhong', name: '田中鎮' },
              { city: 'kaohsiungcity', pinyin: 'tianliao', name: '田寮區' },
              { city: 'changhuacounty', pinyin: 'tianwei', name: '田尾鄉' },
              { city: 'kaohsiungcity', pinyin: 'jiaxian', name: '甲仙區' },
              { city: 'chiayicounty', pinyin: 'fanlu', name: '番路鄉' },
              { city: 'penghucounty', pinyin: 'baisha', name: '白沙鄉' },
              { city: 'tainancity', pinyin: 'baihe', name: '白河區' },
              { city: 'taichungcity', pinyin: 'shigang', name: '石岡區' },
              { city: 'xinbeicity', pinyin: 'shiding', name: '石碇區' },
              { city: 'xinbeicity', pinyin: 'shimen', name: '石門區' },
              { city: 'yilancounty', pinyin: 'jiaoxi', name: '礁溪鄉' },
              { city: 'changhuacounty', pinyin: 'shetou', name: '社頭鄉' },
              { city: 'taichungcity', pinyin: 'shengang', name: '神岡區' },
              { city: 'changhuacounty', pinyin: 'fuxing', name: '福興鄉' },
              { city: 'hualiencounty', pinyin: 'xiulin', name: '秀林鄉' },
              { city: 'changhuacounty', pinyin: 'xiushui', name: '秀水鄉' },
              { city: 'hsinchucounty', pinyin: 'zhubei', name: '竹北市' },
              { city: 'miaolicounty', pinyin: 'zhunan', name: '竹南鎮' },
              { city: 'changhuacounty', pinyin: 'zhutang', name: '竹塘鄉' },
              { city: 'nantoucounty', pinyin: 'zhushan', name: '竹山鎮' },
              { city: 'chiayicounty', pinyin: 'zhuqi', name: '竹崎鄉' },
              { city: 'hsinchucounty', pinyin: 'zhudong', name: '竹東鎮' },
              { city: 'pingtungcounty', pinyin: 'zhutian', name: '竹田鄉' },
              { city: 'taitungcounty', pinyin: 'ludao', name: '綠島鄉' },
              { city: 'changhuacounty', pinyin: 'xianxi', name: '線西鄉' },
              { city: 'yilancounty', pinyin: 'luodong', name: '羅東鎮' },
              { city: 'kaohsiungcity', pinyin: 'meinong', name: '美濃區' },
              { city: 'chiayicounty', pinyin: 'yizhu', name: '義竹鄉' },
              { city: 'hsinchucounty', pinyin: 'qionglin', name: '芎林鄉' },
              { city: 'changhuacounty', pinyin: 'fenyuan', name: '芬園鄉' },
              { city: 'changhuacounty', pinyin: 'huatan', name: '花壇鄉' },
              { city: 'hualiencounty', pinyin: 'hualien', name: '花蓮市' },
              { city: 'changhuacounty', pinyin: 'fangyuan', name: '芳苑鄉' },
              { city: 'miaolicounty', pinyin: 'yuanli', name: '苑裡鎮' },
              { city: 'kaohsiungcity', pinyin: 'lingya', name: '苓雅區' },
              { city: 'miaolicounty', pinyin: 'miaoli', name: '苗栗市' },
              { city: 'kaohsiungcity', pinyin: 'maolin', name: '茂林區' },
              { city: 'kaohsiungcity', pinyin: 'qieding', name: '茄萣區' },
              { city: 'nantoucounty', pinyin: 'caotun', name: '草屯鎮' },
              { city: 'lienchiangcounty', pinyin: 'juguang', name: '莒光鄉' },
              { city: 'yunlincounty', pinyin: 'citong', name: '莿桐鄉' },
              { city: 'pingtungcounty', pinyin: 'wandan', name: '萬丹鄉' },
              { city: 'pingtungcounty', pinyin: 'wanluan', name: '萬巒鄉' },
              { city: 'hualiencounty', pinyin: 'wanrong', name: '萬榮鄉' },
              { city: 'taipeicity', pinyin: 'wanhua', name: '萬華區' },
              { city: 'xinbeicity', pinyin: 'wanli', name: '萬里區' },
              { city: 'xinbeicity', pinyin: 'luzhou', name: '蘆洲區' },
              { city: 'taoyuancounty', pinyin: 'luzhu', name: '蘆竹區' },
              { city: 'yilancounty', pinyin: 'suao', name: '蘇澳鎮' },
              { city: 'taitungcounty', pinyin: 'lanyu', name: '蘭嶼鄉' },
              { city: 'yunlincounty', pinyin: 'huwei', name: '虎尾鎮' },
              { city: 'yunlincounty', pinyin: 'baozhong', name: '褒忠鄉' },
              { city: 'chiayicity', pinyin: 'west', name: '西區' },
              { city: 'taichungcity', pinyin: 'west', name: '西區' },
              { city: 'taichungcity', pinyin: 'xitun', name: '西屯區' },
              { city: 'penghucounty', pinyin: 'xiyu', name: '西嶼鄉' },
              { city: 'tainancity', pinyin: 'xigang', name: '西港區' },
              { city: 'miaolicounty', pinyin: 'xihu', name: '西湖鄉' },
              { city: 'yunlincounty', pinyin: 'xiluo', name: '西螺鎮' },
              { city: 'taoyuancounty', pinyin: 'guanyin', name: '觀音區' },
              { city: 'taichungcity', pinyin: 'fengyuan', name: '豐原區' },
              { city: 'hualiencounty', pinyin: 'fengbin', name: '豐濱鄉' },
              { city: 'xinbeicity', pinyin: 'gongliao', name: '貢寮區' },
              { city: 'kaohsiungcity', pinyin: 'luzhu', name: '路竹區' },
              { city: 'pingtungcounty', pinyin: 'checheng', name: '車城鄉' },
              { city: 'miaolicounty', pinyin: 'tongxiao', name: '通霄鎮' },
              { city: 'miaolicounty', pinyin: 'zaoqiao', name: '造橋鄉' },
              { city: 'taitungcounty', pinyin: 'daren', name: '達仁鄉' },
              { city: 'kaohsiungcity', pinyin: 'namaxia', name: '那瑪夏區' },
              { city: 'pingtungcounty', pinyin: 'ligang', name: '里港鄉' },
              { city: 'kinmencounty', pinyin: 'jincheng', name: '金城鎮' },
              { city: 'kinmencounty', pinyin: 'jinning', name: '金寧鄉' },
              { city: 'xinbeicity', pinyin: 'jinshan', name: '金山區' },
              { city: 'taitungcounty', pinyin: 'jinfeng', name: '金峰鄉' },
              { city: 'kinmencounty', pinyin: 'jinsha', name: '金沙鎮' },
              { city: 'kinmencounty', pinyin: 'jinhu', name: '金湖鎮' },
              { city: 'miaolicounty', pinyin: 'tongluo', name: '銅鑼鄉' },
              { city: 'pingtungcounty', pinyin: 'changzhi', name: '長治鄉' },
              { city: 'taitungcounty', pinyin: 'changbin', name: '長濱鄉' },
              { city: 'taitungcounty', pinyin: 'guanshan', name: '關山鎮' },
              { city: 'tainancity', pinyin: 'guanmiao', name: '關廟區' },
              { city: 'hsinchucounty', pinyin: 'guanxi', name: '關西鎮' },
              { city: 'kaohsiungcity', pinyin: 'alian', name: '阿蓮區' },
              { city: 'chiayicounty', pinyin: 'alishan', name: '阿里山鄉' },
              { city: 'nantoucounty', pinyin: 'jiji', name: '集集鎮' },
              { city: 'xinbeicity', pinyin: 'shuangxi', name: '雙溪區' },
              { city: 'pingtungcounty', pinyin: 'wutai', name: '霧台鄉' },
              { city: 'taichungcity', pinyin: 'wufeng', name: '霧峰區' },
              { city: 'miaolicounty', pinyin: 'toufen', name: '頭份鎮' },
              { city: 'yilancounty', pinyin: 'toucheng', name: '頭城鎮' },
              { city: 'miaolicounty', pinyin: 'touwu', name: '頭屋鄉' },
              { city: 'hsinchucity', pinyin: 'xiangshan', name: '香山區' },
              { city: 'penghucounty', pinyin: 'magong', name: '馬公市' },
              { city: 'pingtungcounty', pinyin: 'gaoshu', name: '高樹鄉' },
              { city: 'nantoucounty', pinyin: 'yuchi', name: '魚池鄉' },
              { city: 'kaohsiungcity', pinyin: 'niaosong', name: '鳥松區' },
              { city: 'kaohsiungcity', pinyin: 'fengshan', name: '鳳山區' },
              { city: 'hualiencounty', pinyin: 'fenglin', name: '鳳林鎮' },
              { city: 'xinbeicity', pinyin: 'yingge', name: '鶯歌區' },
              { city: 'pingtungcounty', pinyin: 'yanpu', name: '鹽埔鄉' },
              { city: 'kaohsiungcity', pinyin: 'yancheng', name: '鹽埕區' },
              { city: 'tainancity', pinyin: 'yanshui', name: '鹽水區' },
              { city: 'changhuacounty', pinyin: 'lugang', name: '鹿港鎮' },
              { city: 'chiayicounty', pinyin: 'lucao', name: '鹿草鄉' },
              { city: 'nantoucounty', pinyin: 'lugu', name: '鹿谷鄉' },
              { city: 'taitungcounty', pinyin: 'luye', name: '鹿野鄉' },
              { city: 'pingtungcounty', pinyin: 'linluo', name: '麟洛鄉' },
              { city: 'yunlincounty', pinyin: 'mailiao', name: '麥寮鄉' },
              { city: 'tainancity', pinyin: 'madou', name: '麻豆區' },
              { city: 'kaohsiungcity', pinyin: 'gushan', name: '鼓山區' },
              { city: 'taichungcity', pinyin: 'longjing', name: '龍井區' },
              { city: 'tainancity', pinyin: 'longqi', name: '龍崎區' },
              { city: 'taoyuancounty', pinyin: 'longtan', name: '龍潭區' },
              { city: 'taoyuancounty', pinyin: 'guishan', name: '龜山區' }
              ];
      
          $.fn.twaddress = function () {
              this.each(function () {
                  function info() {
                      this.city = '';
                      this.city_pinyin = '';
                      this.dist = '';
                      this.dist_pinyin = '';
                      this.detail = '';
                  }
                  var o = new info();
                  var old_dist = null;
                  var opts = $.extend({}, $.fn.twaddress.defaults);
                  var options = { city: "", dist: "", detail: "" };
                  var fisttimeflag = true;
                  var $inputbox = $(this);
                  var $twaddrs = $('<div style="display: inline;"></div>');
                  var $twcity = $('<select style="width:100px;"></select>');
                  var $twmore = $('<input type="text" id="address2" class="input" style="width:500px;"></input>');
                  for (var i = 0; i < city_list.length; i++) {
                      if ($inputbox.val().indexOf(city_list[i].name) >= 0) {
                          options.city = city_list[i].name;
                          $inputbox.val($inputbox.val().replace(city_list[i].name, ""));
                      }
                  }
                  var mx_len = 0;
                  for (var i = 0; i < dist_list.length; i++) {
                      if ($inputbox.val().indexOf(dist_list[i].name) >= 0) {
                          if (mx_len < dist_list[i].name.length) {
                              mx_len = dist_list[i].name.length;
                              options.dist = dist_list[i].name;
                          }
                      }
                  }
                  $inputbox.val($inputbox.val().replace(options.dist, ""));
                  options.detail = $inputbox.val();
                  $twcity.keyup(function () {
                      $twcity.change();
                  });
                  $twcity.change(function () {
                      var $new_twdist = $('<select style="width:100px;"></select>');
                      if (options != undefined && options.detail != undefined && fisttimeflag) {
                          if (options.detail != "hidden") {
                              $twmore.val(options.detail);
                          } else {
                              $twmore.hide();
                          }
                      }
                      for (var i = 0; i < dist_list.length; i++) {
                          if (dist_list[i].city == $twcity.val()) {
                              if (options != undefined && options.dist != undefined && fisttimeflag && dist_list[i].name == options.dist) {
                                  $new_twdist.append('<option value="' + dist_list[i].pinyin + '" selected="selected">' + dist_list[i].name + '</option>');
                                  fisttimeflag = false;
                              } else {
                                  $new_twdist.append('<option value="' + dist_list[i].pinyin + '">' + dist_list[i].name + '</option>');
                              }
                          }
                      }
                      $twcity.after($new_twdist);
                      $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                      $inputbox.change();
      
                      $new_twdist.keyup(function () {
                          $new_twdist.change();
                      });
                      $new_twdist.change(function () {
                          $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                          $inputbox.change();
                      });
                      $twmore.change(function () {
                          $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                          $inputbox.change();
                      });
                      if (old_dist != null) {
                          old_dist.hide().remove();
                      }
                      old_dist = $new_twdist;
                  });
      
                  for (var i = 0; i < city_list.length; i++) {
                      if (options != undefined && options.city != undefined && city_list[i].name == options.city) {
                          $twcity.append('<option value="' + city_list[i].pinyin + '" selected="selected">' + city_list[i].name + '</option>');
                      } else {
                          $twcity.append('<option value="' + city_list[i].pinyin + '">' + city_list[i].name + '</option>');
                      }
                  }
                  $twaddrs.append($twcity);
                  $twaddrs.append($twmore);
                  $(this).after($twaddrs);
                  $(this).hide();
                  $twcity.change();
                  return o;
              });
              function getValue(city, dist, more, o) {
                  var full_address = '';
                  for (var i = 0; i < city_list.length; i++) {
                      if (city_list[i].pinyin == city.val()) {
                          full_address = city_list[i].name;
                          o.city = city_list[i].name;
                      }
                  }
                  for (var i = 0; i < dist_list.length; i++) {
                      if (dist_list[i].city == city.val() && dist_list[i].pinyin == dist.val()) {
                          full_address += dist_list[i].name;
                          o.dist = dist_list[i].name;
                      }
                  }
                  o.city_pinyin = city.val();
                  o.dist_pinyin = dist.val();
                  o.detail = more.val();
                  return full_address + more.val();
              }
          };
      
      })(jQuery);
      
      $(function () {
          $(".twaddress").twaddress();
      });
      </script>

<script>  
      $("#leftside-navigation .sub-menu > a").click(function(e) {
        $("#leftside-navigation ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
        e.stopPropagation()
      })
    </script>
<link rel="stylesheet" href="../../css/frontEnd.css">
<style>

/* cyrillic-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OX-hpOqc.woff2)
		format('woff2');
	unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF,
		U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OVuhpOqc.woff2)
		format('woff2');
	unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OXuhpOqc.woff2)
		format('woff2');
	unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OUehpOqc.woff2)
		format('woff2');
	unicode-range: U+0370-03FF;
}
/* vietnamese */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OXehpOqc.woff2)
		format('woff2');
	unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169,
		U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OXOhpOqc.woff2)
		format('woff2');
	unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB,
		U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
	font-family: 'Open Sans';
	



-style: normal;
	font-weight: 300;
	src: local('Open Sans Light'), local('OpenSans-Light'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN_r8OUuhp.woff2)
		format('woff2');
	unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6,
		U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193,
		U+2212, U+2215, U+FEFF, U+FFFD;
}
/* cyrillic-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFWJ0bbck.woff2)
		format('woff2');
	unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF,
		U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {























	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFUZ0bbck.woff2)
		format('woff2');
	unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFWZ0bbck.woff2)
		format('woff2');
	unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFVp0bbck.woff2)
		format('woff2');
	unicode-range: U+0370-03FF;
}
/* vietnamese */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFWp0bbck.woff2)
		format('woff2');
	unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169,
		U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFW50bbck.woff2)
		format('woff2');
	unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB,
		U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 400;
	src: local('Open Sans Regular'), local('OpenSans-Regular'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem8YaGs126MiZpBA-UFVZ0b.woff2)
		format('woff2');
	unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6,
		U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193,
		U+2212, U+2215, U+FEFF, U+FFFD;
}
/* cyrillic-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOX-hpOqc.woff2)
		format('woff2');
	unicode-range: U+0460-052F, U+1C80-1C88, U+20B4, U+2DE0-2DFF,
		U+A640-A69F, U+FE2E-FE2F;
}
/* cyrillic */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOVuhpOqc.woff2)
		format('woff2');
	unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
}
/* greek-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOXuhpOqc.woff2)
		format('woff2');
	unicode-range: U+1F00-1FFF;
}
/* greek */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOUehpOqc.woff2)
		format('woff2');
	unicode-range: U+0370-03FF;
}
/* vietnamese */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOXehpOqc.woff2)
		format('woff2');
	unicode-range: U+0102-0103, U+0110-0111, U+0128-0129, U+0168-0169,
		U+01A0-01A1, U+01AF-01B0, U+1EA0-1EF9, U+20AB;
}
/* latin-ext */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOXOhpOqc.woff2)
		format('woff2');
	unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB,
		U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
}
/* latin */
@font-face {
	font-family: 'Open Sans';
	font-style: normal;
	font-weight: 700;
	src: local('Open Sans Bold'), local('OpenSans-Bold'),
		url(https://fonts.gstatic.com/s/opensans/v17/mem5YaGs126MiZpBA-UN7rgOUuhp.woff2)
		format('woff2');
	unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6,
		U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193,
		U+2212, U+2215, U+FEFF, U+FFFD;
}

.access-menu-icon1 {
	width: 35px;
	position: absolute;
	left: -21px;
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

.sidebar-toggle {
	margin-left: -240px;
}

.sidebar {
	margin-top: 5px;
	margin-left: 17%;
	width: 240px;

	background: white;
	position: absolute;
	-webkit-transition: all .3s ease-in-out;
	-moz-transition: all .3s ease-in-out;
	-o-transition: all .3s ease-in-out;
	-ms-transition: all .3s ease-in-out;
	transition: all .3s ease-in-out;
	z-index: 100;
}

.include-page2{
   margin: 30px 241px;
    width: 349px;
    

}

.include-page{
    margin-right: 300px;
    margin-left: -115px;
}

b{

    margin-left: 195px;
}


.small { 
margin-left:0px;
  width: 129px; 
  height: 129px; 
  overflow: hidden;


}
.avatar {
margin-rught:100px;
  background-image: url("image/genius.jpg.png");
  background-size: cover;
  border-radius: 100%;
  margin: auto 1rem 26px;
  width: 8rem;
  height: 8rem;
  box-shadow: 0 0 0 4px rgba(53, 42, 148, 0.555);
  transition: all 0.5s ease-in-out;
}
.avatar:hover {
  box-shadow: 0 0 0 6px rgba(53, 42, 148, 0.555);
}


.image-wrap img { width: 100%; }
.circle {
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
}














.recipe-main-list {
    width: 784px;
    background-color: #aedee721;
    margin: 24px 29px;
}



.member-center-spann {
    margin-top: 10px;
    float: right;
    font-size: 16px;
    font-weight: 600;
    font-family: "微軟正黑體";
    color: skyblue;
    height: 40px;
    width: 135px;
    padding-left: 5px;
    padding-top: 10px;
    
}














.sidebar #leftside-navigation ul, .sidebar #leftside-navigation ul ul {
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
	padding: 18px 0 21px 25px;
	font-size: 20px;
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

.sidebar #leftside-navigation ul li a span {
	display: inline-block;
}

.sidebar #leftside-navigation ul li a i {
	width: 20px;
}

.sidebar #leftside-navigation ul li a i .fa-angle-left, .sidebar #leftside-navigation ul li a i .fa-angle-right
	{
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
	font-size: 20px;
	font-weight: bold;
	padding-top: 13px;
	padding-bottom: 13px;
	color: #a6b388;
}

.footer-copyright24 {
	margin-top: 50%;
}

h3 {
    margin-top: 17px;
    margin-left: -233px;
	font-family: Calibri;
	font-size: 26pt;
	font-style: normal;
	font-weight: bold;
	 color: rgba(16, 75, 75, 0.66);

	/*text-decoration: underline*/
	margin-bottom: 7px;
}
h1{
margin-left:88px;


}
h2{
margin-left:56px;
margin-bottom:8px;
margin-top:-14px;

}


b{    margin-right: -235px;
    font-size: 19px;}



table {
    margin-left: 10px;
    width: 822px;

	font-family: Calibri;
	color: #e4002b;
	font-size: 12pt;
	font-style: normal;
	font-weight: bold;
	/* text-align:;  */
	background-color: #dff0eba4;
	border: 2px solid rgb(226, 226, 238);
	border-spacing: 60px 40px;
	/*  border-width: 200px;*/
	
	
	margin-left: 20px;
  
    width: 830px;
	
	
	
	
	
	
	
}
table.inner {
	border: 0px
}

.container {
	margin-left: 500px;
}

.textwidth {
	width: 500px;
	height: 30px;
	margin-right: 40px;
}

/* .submit_btn{

  width: 100px;
  height: 40px;
 } */
.submitAndSave {
	text-align: center;
	display: inline-block;
	width: 70px;
	height: 70px;
	letter-spacing: 3px;
	flex: 1;
	padding-top: 16px;
	padding-left: 6px;
	margin: 25px;
	border-radius: 999px;
	border: 1px solid #E4002B;
	font-family: '微軟正黑體', monospace;
	font-weight: 600;
	font-size: 20px;
	text-shadow: 0 3px 5px rgba(0, 0, 0, 0.25);
	white-space: nowrap;
	color: #E4002B;
	background: transparent;
	background-image: url("../image/button-bg-fill-lightBlue.png");
	background-repeat: repeat-x;
	background-position: 0 -70%;
	transition: 1.4s ease;
	background-color: white;
}

.submitAndSave:hover {
	background-position: 500% 100%;
	color: white;
	cursor: pointer;
}

.article-section-seemore:nth-child(1) {
	filter: hue-rotate(20deg);
}







.link-top {

            width: 104%;
            height: 1px;
            border-top: solid rgb(226, 16, 44) 3px;

            margin: 5px 0 0 -20px;
        }









</style>




























<style>
/*---------------------------------article-container-------------------------------------------*/
a {
	text-decoration: none;
	color: #E4002B;
}

.article-container {
	text-align: center;
	/* border: 5px solid black; */
	margin-top: 60px;
}

.article-container h2 {
	font-size: 36px;
	letter-spacing: 10px;
	font-weight: 400;
}

.article-section-description {
	/* border: 5px solid black; */
	max-width: 1024px;
	margin: 10px auto;
	/* height: 600px; */
}

/*-----------------------------廚房專區----------------------------*/
#article-recipe {
	height: 530px;
	margin-top: 210px;
	background-image: url("image/ad/recipeBG.png");
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
}

.ariticle-section-racipe-h2 {
	position: relative;
	top: -90px;
}

.ariticle-section-racipe-h2+span {
	position: relative;
	top: -85px;
}

#article-container-recipe {
	height: 500px;
	overflow: hidden;
}

#article-section-description-recipe {
	margin: auto;
	height: 400px;
}

.article-section-description-recipe-left {
	width: 50%;
	height: 100%;
	float: left;
	padding-top: 90px;
}

.font{
        margin-left: 192px;


}







.article-section-description-recipe-left-span {
	color: white;
	font-size: 28px;
	display: block;
	padding-top: 10px;
}

.article-section-description-recipe-left img {
	float: left;
	margin-top: 30px 5px;
}

.article-section-description-recipe-right {
	float: right;
	width: 45%;
	height: 45%;
	padding: 5px;
	background-color: white;
	margin-bottom: 10px;
	margin-top: 10px;
}

.article-recipe-right-photo-div {
	width: 50%;
	overflow: hidden;
}

.article-recipe-right-title {
	float: left;
	width: 50%;
}

.article-recipe-right-title h3, p {
	font-weight: 500;
	font-size: 18px;
	text-align: left;
	width: 200px;
}

.article-recipe-right-title h3 {
	margin-top: 15px;
	margin-left: 30px;
}

.article-recipe-right-title p {
	display: inline-block;
	color: black;
	border-top: 1px solid #E4002B;
	padding-top: 5px;
}

.article-recipe-right-title p:hover {
	text-decoration: underline;
	color: #E4002B;
}

.article-recipe-right-photo-div img {
	width: 100%;
	height: 170px;
	transition: all ease-in-out 300ms;
	transform: scale(1, 1);
	overflow: hidden;
}

.article-recipe-right-photo-div img:hover {
	transform: scale(1.1, 1.1);
}

#article-section-seemore-recipe {
	margin-top: 90px;
	clear: both;
}

@media screen and (max-width: 575px) {
	#article-recipe {
		height: 880px;
	}
	.article-section-description-recipe-left {
		box-sizing: border-box;
		width: 350px;
		padding-top: 0;
		padding-left: 10px;
		margin: 0 auto;
		height: 300px;
		float: none;
	}
	.article-section-description-recipe-right {
		box-sizing: border-box;
		width: 350px;
		height: 190px;
		float: none;
		margin: 40px auto;
	}
	#article-section-seemore-recipe {
		margin: 70px auto;
	}
	.article-recipe-right-photo-div {
		width: 50%;
		overflow: hidden;
		margin-left: 5px;
	}
	.article-recipe-right-title {
		float: left;
	}
	.article-recipe-right-title h3, p {
		text-align: left;
		width: 90%;
	}
}

/*---------------------------直播專區------------------------------------*/
#livestream {
	border-top: 1px solid rgb(207, 207, 207);
	border-bottom: 1px solid rgb(207, 207, 207);
	padding: 60px 0;
}

#article-container-livestream {
	margin: 0 auto;
	max-width: 1024px;
}

.article-section-description-livestream {
	padding-top: 30px;
	height: 650px;
}

.article-section-description-livestream-left {
	box-sizing: border-box;
	height: 600px;
	width: 50%;
	float: left;
	padding-top: 75px;
}

.article-section-description-livestream-right {
	box-sizing: border-box;
	padding: 20px 10px 0 10px;
	width: 24%;
	display: inline-block;
	text-align: left;
}

.livestream-left-1-video-container iframe {
	width: 95%;
	height: 270px;
	margin-bottom: 5px;
}

.livestream-left-1-video-title {
	padding-left: 10px;
	text-align: left;
	height: 30%;
}

.livestream-left-1-video-title h3 {
	border-bottom: 2px solid #E4002B;
	display: inline-block;
	font-size: 20px;
}

.livestream-left-1-video-title p {
	width: 100%;
	color: black;
	text-align: left;
	font-size: 18px;
	margin-top: 10px;
}

.livestream-right-photo-div {
	overflow: hidden;
	height: 150px;
}

.article-section-description-livestream-right img {
	width: 100%;
	height: auto;
	transition: all ease-in-out 400ms;
	transform: scale(1, 1);
	overflow: hidden;
}

.article-section-description-livestream-right img:hover {
	transform: scale(1.1, 1.1);
}

.livestream-right-title h3 {
	font-weight: 400;
	font-size: 18px;
	border-bottom: 1px solid #E4002B;
	display: inline-block;
	margin-top: 5px;
	clear: both;
}

.livestream-right-title p {
	color: black;
	text-align: left;
	font-size: 18px;
	margin-top: 5px;
	max-width: 100%;
}

@media screen and (max-width: 575px) {
	#article-container-livestream {
		height: 1850px;
	}
	.article-section-description-livestream-left {
		width: 100%;
		padding: 0;
		float: none;
		height: 500px;
		margin: 0 auto;
	}
	.article-section-description-livestream-right {
		width: 350px;
		height: 320px;
		float: none;
	}
	.livestream-right-photo-div {
		height: 200px;
	}
}

@media ( max-width : 576px) and (max-width: 768px) {
}

/*---------------------------食材商城------------------------------------*/
#mallproduct {
	margin: 100px auto;
	margin-bottom: 50px;
	max-width: 1100px;
	min-height: 1000px;
}

ul.imglist a {
	list-style-type: none;
	margin: 0px auto;
	color: black;
	text-decoration: underline;
}

.imglist-li {
	width: 200px;
	margin: 50px 35px;
	padding: 0;
	float: left;
	overflow: hidden;
}

ul.imglist li img {
	width: 200px;
	height: 190px;
}

.imglist-li-span {
	display: inline-block;
	margin: 0 auto;
	width: 200px;
	height: 30px;
	padding: 0 auto;
	line-height: 40px;
}

/*變換大小*/
@media screen and (max-width: 320px) {
	ul.imglist li {
		margin: 5px;
		width: 130px;
		display: inline-block;
		float: none;
		text-align: center;
	}
	ul.imglist li img {
		width: 100%;
		height: 140px;
	}
}

@media ( min-width : 321px) and (max-width: 575px) {
	ul.imglist li {
		margin: 9px;
		width: 160px;
		display: inline-block;
		float: none;
		text-align: center;
	}
	#mallproduct {
		height: 1190px;
	}
	ul.imglist li img {
		width: 100%;
		height: 140px;
	}
	ul.imglist li span {
		width: 100%;
	}
}

@media ( min-width : 575px) and (max-width: 767px) {
	.shop-slide_viewer {
		height: 450px;
		padding-left: 2px;
	}
	#mallproduct {
		width: 500px;
		height: 1300px;
		padding: 0;
	}
	ul.imglist li {
		margin: 20px 0;
		box-sizing: border-box;
	}
	.lookmore button {
		margin: 10px auto;
	}
	ul.imglist li img {
		width: 190px;
		height: 140px;
	}
}

@media ( min-width : 768px) and (max-width: 991px) {
	#mallproduct {
		height: 850px;
		width: 800px;
	}
	ul.imglist li {
		margin: 20px 30px;
	}
}

hr {
	margin-top: 100px;
	background-color: rgba(246, 245, 246, 0.24);
}

.middle-ad-contain {
	max-width: 100%;
	max-height: 350px;
	padding-top: 50px;
	/* overflow: hidden; */
}

.middle-ad-contain img {
	max-width: 100%;
	margin: 0 auto;
	display: block;
}

/*---------------------------課程專區------------------------------------*/
#course-list-div {
	margin: 90px auto;
}

.course-list-li {
	display: inline-block;
	list-style: none;
	width: 40%;
	/* overflow: hidden; */
	/* max-height: 250px; */
	margin: 10px;
	text-align: left;
}

.course-li-img-div {
	margin: 0 auto;
	width: 90%;
	height: 200px;
	overflow: hidden;
}

.course-li-img {
	width: 100%;
	height: 200px;
	transition: all 350ms;
}

.course-li-img:hover {
	transform: scale(1.04, 1.04);
}

.course-li-title {
	display: block;
	margin-left: 5%;
	color: rgb(26, 23, 44);
	font-size: 16px;
	margin-top: 10px;
	font-weight: 600;
}

.course-list-date-span {
	display: inline-block;
	margin-left: 5%;
	margin-top: 0px;
	color: #FF5757;
	font-size: 14px;
	margin-right: 10px;
	font-weight: 600;
}

.course-date-li {
	margin-top: 6px;
	display: inline-block;
	color: rgb(187, 187, 187);
	font-size: 13px;
}

.course-sider-list {
	margin: 60px 0;
}

.course-sider-list-viewer a :active {
	border: none;
}

.article-section-seemore-course {
	margin-top: 80px;
}

@media screen and (max-width: 575px) {
	.course-list-li {
		display: block;
		width: 340px;
		margin: 20px auto;
	}
}



.function {
    float: left;
    border-bottom: 4px solid rgb(199, 199, 199);
    height: 51px;
    width: 85%;
    margin-top: 15px;
}

.function-list {
    display: inline;
}










</style>
</head>

<body>
	<header>
		<div id="top-logo" class="logo">
			<a href="/DA106_G4_Foodporn_Git/index.jsp" title="回首頁"><img
				class="logo-photo" src="../../image/FoodPron_Logo.png" alt="logo"></a>
		</div>
		<div class="function">
			
			<div class="function-list">
				<div class="menu">
					<input type="checkbox" href="#" class="menu-open menu-icon"
						name="menu-open" id="menu-open" /> <label
						class="menu-open-button" for="menu-open"> <span
						class="lines line-1"></span> <span class="lines line-2"></span> <span
						class="lines line-3"></span>
					</label>
				</div>


				<c:if test='${not empty member_id}'>

					<div class="login">

						<a> <img class="header-icon" src="../../image/logout.png"
							alt="login-icon"> <span class="login-span">登出</span>
							<form method="POST" action="member.do">
								<input type="hidden" name="action" value="loginOUT"> <input
									class="login-out" type="submit" name="action"
									style="display: none;">
							</form>
						</a>
					</div>

				</c:if>
				<script>
				$('.login-span').click(function(){
					$('.login-out').click();
				})

				</script>

				<div class="shop-car">
					<a href="#"><img class="header-icon"
						src="../../image/shopping-cart-icon.png" alt="shopping-cart">
						<div class="herder-icon-span">
							<span class="shop-car-span">購物車</span>
						</div> </a>
				</div>
				<div class="notice">
					<a href="#"><img class="header-icon"
						src="../../image/ico_notice.png" alt="notice-icon">
						<div class="herder-icon-span">
							<span class="notice-span">通知總覽</span>
						</div> </a>
				</div>
				

				
				
			
				</div>
				
			
				
				
				
				
				
				
				
							
			<!-- 			<div class="function-list"> -->
				<a href="#"></a>
<!-- 				<div class="member-center"> -->
					<div class="herder-icon-span"><span class="member-center-spann">HI，<%=memberVO1.getMember_name()%></span></div>
<%--                         <span class="member-center-spann">HI，<%=session.getAttribute("member_name") %></span> --%>
<!-- 				</div> -->
				</a>
<!-- 			</div> -->
				


			</div>

	
		<!-- end of function-->
		<nav id="navigation">
			<ul>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/recipe-icon.png"><span class="menu-span">食譜專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_book.svg"><span
								class="dropdown-first-a-span">食譜主頁</span></a></li>
						<li><a href="#">特輯食譜</a></li>
						<li><a href="#">推薦食譜</a></li>
						<li><a href="#">建立食譜</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/livestream-icon.png"><span
						class="menu-span">直播專區</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_movie.svg"><span
								class="dropdown-first-a-span">直播主頁</span></a></li>
						<li><a href="#">直播預告</a></li>
						<li><a href="#">熱門直播</a></li>
						<li><a href="#">建立直播預告</a></li>
					</ul></li>
				<li class="dropdown dropdown-shop"><a><img
						class="access-menu-icon" src="../../image/shop-icon.png"><span
						class="menu-span">食材商城</span></a>
					<ul id="dropdown-shop-ul">
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_salad.svg"><span
								class="dropdown-first-a-span">食材商城主頁</span></a></li>
						<li><a href="#">調味料</a></li>
						<li><a href="#">果醬</a></li>
						<li><a href="#">麵粉</a></li>
						<li><a href="#">酒類</a></li>
						<li><a href="#">蔬菜</a></li>
						<li><a href="#">水果</a></li>
						<li><a href="#">海鮮</a></li>
						<li><a href="#">肉類</a></li>
						<li><a href="#">乳製品</a></li>
						<li><a href="#">香料</a></li>
						<li><a href="#">罐頭</a></li>
						<li><a href="#">乾貨</a></li>
					</ul></li>
				<li class="dropdown"><a><img class="access-menu-icon"
						src="../../image/course-icon.png"><span class="menu-span">料理課程</span></a>
					<ul>
						<li><a class="dropdown-first-a" href="#"><img
								class="dropdown-first-img"
								src="../../image/ico_gnav_recipes_pot.svg"><span
								class="dropdown-first-a-span">課程主頁</span></a></li>
						<li><a href="#">熱門課程</a></li>
						<li><a href="#">建立料理課程</a></li>
					</ul></li>
			</ul>
		</nav>
	</header>
	<!-- end of header-->

	<!-- 登箱開始 -->
	<c:if test='${empty member_id}'>
		<div class="login-wrap">
			<div class="login-html">
				<img class="login-close" src="../../image/close.png" alt="close">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
					for="tab-1" class="tab">Sign In</label> <input id="tab-2"
					type="radio" name="tab" class="sign-up"><label for="tab-2"
					class="tab">Sign Up</label>
				<div class="login-form">
					<form method="POST" action="member.do">
						<div class="sign-in-htm">
							<div class="group">
								<label for="user" class="label">Username</label> <input
									id="user" type="text" class="input">
							</div>
							<div class="group">
								<label for="pass" class="label">Password</label> <input
									id="pass" type="password" class="input" data-type="password">
							</div>
							<input type="hidden" name="action" value="login">
							<div class="group">
								<input id="check" type="checkbox" class="check" checked>
								<label for="check"><span class="icon"></span> Keep me
									Signed in</label>
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
					<div class="sign-up-htm">
						<div class="group">
							<label for="newuser" class="label">User</label> <input
								id="newuser" type="text" class="input">
						</div>
						<div class="group">
							<label for="newpass" class="label">Password</label> <input
								id="newpass" type="password" class="input" data-type="password">
						</div>
						<div class="group">
							<label for="renewpass" class="label">Repeat Password</label> <input
								id="renewpass" type="password" class="input"
								data-type="password">
						</div>
						<div class="group">
							<label for="email" class="label">Email Address</label> <input
								id="email" type="email" class="input">
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
	
	</div>
	</c:if>
	<!-- end of login-->
	<div class="pagetop">
		<img src="../../image/go-top-page.png" alt="BackTop" id="BackTop">
		<!--一鍵置頂-->
	</div>
	<!-- end of pagetop-->
	<main>
		<article>
			<!-- 以下開始你的各種標籤 -->






<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<aside class="sidebar">















  <div class="avatar">



          <div class="circle image-wrap small">
 <img src=DBGifReader4.do?photo_type=mempic&member_id=<%=session.getAttribute("member_id")%> id="preview_progressbarTW_img" width=129px height=129px;/>
</div>
</div>
          <h2><%=membervo.getMember_name()%></h2>

          <div class="link-top"></div>

























	<div id="leftside-navigation" class="nano">
		<ul class="nano-content">
			<li><a
				href="/DA106_G4_Foodporn_Git/front-end/member/TestMemberHomepage.jsp"><i
					class="fa fa-dashboard"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066824.jpg"><span>我的帳戶</span></a></li>
					
					
					<li><a
				href="/DA106_G4_Foodporn_Git/front-end/member/MemberListAllShopOrder01.jsp"><i
					class="fa fa-dashboard"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066823.jpg"><span>購買清單</span></a></li>
					
					
					
					
					
					
					
					
					
					
					
<!-- 			<li class="sub-menu"><a href="javascript:void(0);"><i -->
<!-- 					class="fa fa-cogs"></i><img class="access-menu-icon1" -->
<!-- 					src="../../image/member/S__12066823.jpg"><span>購買清單&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i -->
<!-- 					class="arrow fa fa-angle-right pull-right"></i></a> -->
<!-- 				</li> -->
			<li class="sub-menu"><a href="javascript:void(0);"><i
					class="fa fa-table"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066821.jpg"><span>我的錢包/點數&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>
				</span><i class="arrow fa fa-angle-right pull-right"></i></a>
				<ul>
					<li><a href="tables-basic.html">交易紀錄</a></li>
					
	<li><a href="/DA106_G4_Foodporn_Git/front-end/member/123.jsp">信用卡</a></li>
				<c:if test='${member_status eq 1 }'>
					<li><a href="/DA106_G4_Foodporn_Git/front-end/member/ChefWithdraw.jsp">提款</a></li>
			</c:if>		
					<li><a href="/DA106_G4_Foodporn_Git/front-end/member/MemberStoredValue.jsp">儲值</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:void(0);"><i
					class="fa fa fa-tasks"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066822.jpg"><span>瀏覽紀錄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
					class="arrow fa fa-angle-right pull-right"></i></a>
				<ul>
					<li><a href="forms-components.html">課程瀏覽</a></li>
					<li><a href="forms-validation.html">直播瀏覽</a></li>
					<li><a href="forms-mask.html">食譜瀏覽</a></li>
					<li><a href="forms-wizard.html">1</a></li>
					<li><a href="forms-multiple-file.html">2</a></li>
					<li><a href="forms-wysiwyg.html">3</a></li>
				</ul></li>
			<li class="sub-menu"><a href="javascript:void(0);"><i
					class="fa fa-envelope"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066820.jpg"><span>精選收藏&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
					class="arrow fa fa-angle-right pull-right"></i></a>
				<ul>
					<li><a href="mail-inbox.html">課程收藏</a></li>
					<li><a href="mail-compose.html">直播收藏</a></li>
					<li><a href="mail-compose.html">食譜收藏</a></li>
				</ul></li>
			
			
		<c:if test='${member_status eq 0 }'>
			
			
			<li class="sub-menu"><a href="javascript:void(0);"><i
					class="fa fa-bar-chart-o"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066818.jpg"><span>我的課程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
					class="arrow fa fa-angle-right pull-right"></i></a>
				<ul>
					<li><a href="charts-chartjs.html">課程紀錄</a></li>
					<li><a href="charts-morris.html">1</a></li>
					<li><a href="charts-c3.html">2</a></li>
				</ul></li>
		</c:if>

<c:if test='${member_status eq 1 }'>
			<li class="sub-menu"><a href="javascript:void(0);"><i
					class="fa fa-map-marker"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066827.jpg"><span>直播/課程管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i
					class="arrow fa fa-angle-right pull-right"></i></a>
				<ul>
					<li><a href="MemberCourse.jsp">課程管理</a></li>
					<li><a href="map-vector.html">直播管理</a></li>
				</ul></li>
					</c:if>	
<c:if test='${member_status eq 1 }'>
			<li><a
				href="MemberRecipe.jsp"><i
					class="fa fa-dashboard"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066826.jpg"><span>食譜管理</span></a></li>
					</c:if>	
					
					
		<c:if test='${member_status eq 0 }'>			
			<li class="sub-menu"><a href="/DA106_G4_Foodporn_Git/front-end/member/ChefHomepage.jsp"><i
					class="fa fa-text-height"></i><img class="access-menu-icon1"
					src="../../image/member/S__12066825.jpg"><span>升級廚師</span></a></li>
</c:if>	
		</ul>
	</div>
</aside>
<FORM METHOD="post" ACTION="../recipe/RecipeServlet" name="upateform" id="upateform"
		enctype="multipart/form-data" style="margin-left: 500; margin-top: 34px;">
<table align="center" cellpadding="10">

			<div class="container">
<h3><h3>個人食譜管理</h3></h3>
					<table align="center" cellpadding="10">

						<!----- First Name ---------------------------------------------------------->
					
		                 <div class="recipe-main-list">
		<span class="include-page"><%@ include file="page3.file"%>
		</span>
		<ul class="recipe=list">
			<c:forEach var="RecipeVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<li class="recipe-item"><img
					src="${(RecipeVO.recipe_photo==null)?'../../image/icon/uploadPic.png':RecipeVO.recipe_photo}"
					alt="">
					<div class="recipe-item-caption">
						<div class="recipe-item-caption-header">
							<h4 class="recipe-item-tile">
								<a class="show-one-link"
									href="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet?action=getOne_For_Display&recipe_id=${RecipeVO.recipe_id}">${RecipeVO.recipe_name}</a>
							</h4>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet"
								style="margin-bottom: 0px;">
								<button class="update" value="修改">
									<img src="../../image/icon/update.png" title="修改" alt="修改">
								</button>
								<input type="hidden" name="recipe_id"
									value="${RecipeVO.recipe_id}"> <input type="hidden"
									name="action" value="getOne_For_Update">
							</FORM>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/front-end/recipe/RecipeServlet"
								style="margin-bottom: 0px;">
								<button class="delete" value="刪除">
									<img src="../../image/icon/delete.png" title="刪除" alt="刪除">
								</button>
								<input type="hidden" name="recipe_id"
									value="${RecipeVO.recipe_id}"> <input type="hidden"
									name="action" value="delete">
							</FORM>

						</div>
						<p class="recipe-create-time">建立時間:${RecipeVO.recipe_uldate}</p>
						<p class="recipe-item-ingredient">追蹤人數：${RecipeVO.refollow_num}</p>
					</div></li>
			</c:forEach>
		</ul>
		<div class="include-page2">
			<%@ include file="page2.file"%>
		</div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
		                 
					</table>
					
			<br> <input type="hidden" name="action" value="updateBySelf">	
					
					 <input type="hidden"
							name="member_id" value="<%=session.getAttribute("member_id")%>">
							
							
						
					
					<input type="hidden"
							name="account" value="${membervo.member_photo}">
					
					
					
					
				 
					
					
					
					
					
					
				
				</form>
				</div>
				
				<script>
<%java.sql.Date birthday = null;
                birthday = (membervo == null || membervo.getBirthday() == null)
					? new java.sql.Date(System.currentTimeMillis())
					:membervo.getBirthday();
%>

 	$.datetimepicker.setLocale('zh');
	$('#birthday').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '<%=birthday%>',
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	startDate:	            '2000/01/01',  // 起始日
	//minDate:'-1970-01-07', // 去除今日(不含)之前
	maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});


	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
	
	
	

	<script>
		$("#imgView").change(function() {

			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#preview_progressbarTW_img")
							.attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			
			}
			return;
     	}
		

		
		
		$(function(){
			// 先取得 #password1 及產生一個文字輸入框
			var $password = $('#password'), 
				$passwordInput = $('<input type="text" name="' + $password.attr('name') + '" class="' + $password.attr('className') + '" />');
		 
			// 當勾選顯示密碼框時
			$('#show_password').click(function(){
				// 如果是勾選則...
				if(this.checked){
					// 用 $passwordInput 來取代 $password
					// 並把 $passwordInput 的值設為 $password 的值
					$password.replaceWith($passwordInput.val($password.val()));
				}else{
					// 用 $password 來取代 $passwordInput
					// 並把 $password 的值設為 $passwordInput 的值
					$passwordInput.replaceWith($password.val($passwordInput.val()));
				}
			});
		});
		
		
		
		
		
		
		</script>
				
				
				
</body>






























</article>
</main>






<div class="footer-copyright24">





































	<footer>


		<!--這個是底部,你用不到-->























		<div class="footer-bg">
			<div class="footer-murmur">
				<img src="../../image/FoodPron_Logo_white.png" alt="logo"
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
        $('#BackTop').click(function () {
            $('html,body').animate({ scrollTop: 0 }, 333);
        });
        $(window).scroll(function () {
            if ($(this).scrollTop() > 450) {
                $('#BackTop').fadeIn(222);
            } else {
                $('#BackTop').stop().fadeOut(222);
            }
        });

        //畫面捲動時隱藏
        window.onresize = function () {
            if ($(window).width() > 767) {
                $("nav").show();
            } else $("nav").hide();
        }

    </script>

	<!-- JavasScript for shop-slide -->
	<script>
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
        $(".shop-slide-article-and-photo").hover(function () {
            $(".shop-slide-article-and-photo-img").removeClass('imgSmall');
            $(".shop-slide-article-and-photo-img").addClass('imgScale');
        });
        $(".shop-slide-article-and-photo").mouseleave(function () {
            $(".shop-slide-article-and-photo-img").addClass('imgSmall');
        });

        /* 直播專區 right scale 效果 */
        $(".article-section-description-livestream-right").hover(function () {
            $(".livestream-right-img", this).addClass('imgScale');
        });
        $(".imglist-li a").hover(function () {
            $(".imglist-li-span", this).removeClass('textRecoverColor');
            $(".imglist-li-img", this).removeClass('imgSmall');
            $(".imglist-li-img", this).addClass('imgScale');
            $(".imglist-li-span", this).addClass('textChangeColor');
        });
        $(".imglist-li a").mouseleave(function () {
            $(".imglist-li-span", this).addClass('textRecoverColor');
            $(".imglist-li-img", this).addClass('imgSmall');
        });
    </script>
	<script>
        $(".menu-open").on("click", function () {
            $("nav").slideToggle();
            $(this).toggleClass("active");
            $('body,html').toggleClass('add')
        });

    </script>
	<script>
        AOS.init();
    </script>

	<script>
        $(document).ready(function () {
            $('.course-sider-list').slick({
                dots: true,
                centerMode: true,
                centerPadding: '60px',
                slidesToShow: 3,
                responsive: [
                    {
                        breakpoint: 768,
                        settings: {
                            arrows: false,
                            centerMode: true,
                            centerPadding: '40px',
                            slidesToShow: 3
                        }
                    },
                    {
                        breakpoint: 480,
                        settings: {
                            arrows: false,
                            centerMode: true,
                            centerPadding: '40px',
                            slidesToShow: 1
                        }
                    }
                ]
            });
        });

    </script>









	<script>  
    $("#leftside-navigation .sub-menu > a").click(function(e) {
      $("#leftside-navigation ul ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
      e.stopPropagation()
    })

    $(document).ready(function() {  
  $('.multiselect').multiselect();
  $('.datepicker').datepicker();  
});

  </script>



<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

	<script>
var state = '${param.state}';
console.log("state"+state);
if(state=="sucess"){
	
	Swal.fire({
		
		  icon: 'success',
		  title: '已送出申請',
		  showConfirmButton: false,
		  timer: 1500
		})
	
}
	</script>










	</body>
</html>