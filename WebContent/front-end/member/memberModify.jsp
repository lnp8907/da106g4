<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
    String member_id =(String) session.getAttribute("member_id");
out.println(member_id);
%>
    
    
    
    
    
    
    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="../../front-end/css/member.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    
    <script type="text/javascript">

      $().ready(function(){
      
      $( "#date" ).datepicker({
      changeMonth: true,     //可以限定是否需要月份的下拉是選單，預設是沒有
      changeYear: true, 　　//可以限定是否需要年份的下拉是選單，預設是沒有
      dateFormat: 'yy/mm/dd',　　//所顯示的default
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
    position: absolute;
   left: -21px;
}


main{

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
    margin-top: -800px;
    margin-left: 13%;
  width: 240px;
  height: 100%;
  background: white;
  position: absolute;
  -webkit-transition: all .3s ease-in-out;
  -moz-transition: all .3s ease-in-out;
  -o-transition: all .3s ease-in-out;
  -ms-transition: all .3s ease-in-out;
  transition: all .3s ease-in-out;
  z-index: 100;
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
.sidebar #leftside-navigation ul li.active > a {
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
  font-size: 20px;
  font-weight: bold;
  padding-top: 13px;
  padding-bottom: 13px;
  color: #a6b388;
}
.footer-copyright24{
 
  margin-top: 50%;

}














h3{
  font-family: Calibri; 
  font-size: 20pt;         
  font-style: normal; 
  font-weight: bold; 
  color:black;
   
  /*text-decoration: underline*/
}

table{
  width:800px;

  font-family: Calibri; 
  color:#e4002b; 
  font-size: 12pt; 
  font-style: normal;
  font-weight: bold;
  /* text-align:;  */
  background-color: #dff0eba4; 
 
  border: 2px solid rgb(226, 226, 238);
  border-spacing:60px 40px;
/*  border-width: 200px;*/



}
table.inner{
  border: 0px
}
.container{

  margin-left: 500px;
}
.textwidth{
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













    </style>

</head>

<body>

    <header>
        <!--這個是上方選單,你用不到-->
        <div id="top-logo" class="logo"><a href="frontEnd.html" title="回首頁"><img class="logo-photo"
                    src="../image/FoodPron_Logo.png" alt="logo"></a></div>
        <div class="function">
            <div class="function-list">
                <div class="member-center">
                    <a href="#"><span class="member-center-spann">會員中心</span></a>
                </div>
            </div>
            <div class="function-list">
                <div class="shop-car">
                    <a href="#"><img class="header-icon" src="../../image/shopping-cart-icon.png" alt="shopping-cart">
                        <div class="herder-icon-span"><span class="shop-car-span">購物車</span></div>
                    </a>
                </div>
            </div>
            <div class="login">
                <div class="function-list">
                    <a href="#"><img class="header-icon" src="../../image/user-icon.png" alt="login-icon">
                        <div class="herder-icon-span"><span class="login-span">登入</span></div>
                    </a>
                </div>
            </div>
        </div><!-- end of function-->
        <nav role="navigation">
            <ul class="access-menu">
                <li>
                    <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../image/recipe-icon.png"><span
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
                    <a href="#" class="access-menu-a"><img class="access-menu-icon"
                            src="../../image/livestream-icon.png"><span class="access-menu-span">直播專區</span></a>
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
                    <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../image/shop-icon.png"><span
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
                    <a href="#" class="access-menu-a"><img class="access-menu-icon" src="../../image/course-icon.png"><span
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
            <img class="login-close" src="../../image/close.png" alt="close">
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
        <a href="#top-logo"><img src="../../image/go-top-page.png" alt="go-top-page"></a>


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
            <!-- 以下開始你的各種標籤 -->
        



  













   <div class="container">
      
<h3>我的檔案</h3>

<h1>管理你的檔案以保護你的帳戶</h1>

 
<table align="center" cellpadding = "10">
 
<!----- First Name ---------------------------------------------------------->
<tr>
<td>姓名:</td>

<td><input type="text" placeholder="S許嘉宏" class="textwidth" name="First_Name" maxlength="30" />
  
<!-- (max 30 characters a-z and A-Z) -->
</td>
</tr>
 
<!----- Last Name ---------------------------------------------------------->
<tr>
<td>帳號:</td>
<td>
  <div class="mem_account">xxxxxxxxxxxxxxxxxxxxx</div>
<!-- <td><input type="text" class="textwidth" name="Last_Name" maxlength="30"/> -->
<!-- (max 30 characters a-z and A-Z) -->
</td>
</tr>

<tr>
<td>密碼:</td>
<td><input type="text" class="textwidth" name="Last_Name" maxlength="30"/>
<!-- (max 30 characters a-z and A-Z) -->
</td>
</tr>



 
<!----- Date Of Birth -------------------------------------------------------->
<tr>
<td>生日:</td>
 
<td>
  <input size="10"  type="text" id="date" readonly="readonly" />
<!-- <select name="Birthday_day" id="Birthday_Day">
<option value="-1">日</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
 
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
 
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
 
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
 
<option value="31">31</option>
</select>
 
<select id="Birthday_Month" name="Birthday_Month">
<option value="-1">月</option>
<option value="January">Jan</option>
<option value="February">Feb</option>
<option value="March">Mar</option>
<option value="April">Apr</option>
<option value="May">May</option>
<option value="June">Jun</option>
<option value="July">Jul</option>
<option value="August">Aug</option>
<option value="September">Sep</option>
<option value="October">Oct</option>
<option value="November">Nov</option>
<option value="December">Dec</option>
</select>
 
<select name="Birthday_Year" id="Birthday_Year">
 
<option value="-1">年</option>
<option value="2012">2012</option>
<option value="2011">2011</option>
<option value="2010">2010</option>
<option value="2009">2009</option>
<option value="2008">2008</option>
<option value="2007">2007</option>
<option value="2006">2006</option>
<option value="2005">2005</option>
<option value="2004">2004</option>
<option value="2003">2003</option>
<option value="2002">2002</option>
<option value="2001">2001</option>
<option value="2000">2000</option>
 
<option value="1999">1999</option>
<option value="1998">1998</option>
<option value="1997">1997</option>
<option value="1996">1996</option>
<option value="1995">1995</option>
<option value="1994">1994</option>
<option value="1993">1993</option>
<option value="1992">1992</option>
<option value="1991">1991</option>
<option value="1990">1990</option>
 
<option value="1989">1989</option>
<option value="1988">1988</option>
<option value="1987">1987</option>
<option value="1986">1986</option>
<option value="1985">1985</option>
<option value="1984">1984</option>
<option value="1983">1983</option>
<option value="1982">1982</option>
<option value="1981">1981</option>
<option value="1980">1980</option>
</select> -->
</td>
</tr>
 
<!----- Email Id ---------------------------------------------------------->
<tr>
<td>信箱:</td>
<td><input type="text" name="Email_Id" maxlength="100" /></td>
</tr>
 
<!----- Mobile Number ---------------------------------------------------------->
<tr>
<td>電話號碼:</td>
<td>
<input type="text" name="Mobile_Number" maxlength="10" />
(10 digit number)
</td>
</tr>
 
<!----- Gender ----------------------------------------------------------->
<tr>
<td>性別:</td>
<td>
男 <input type="radio" name="Gender" value="Male" />
女 <input type="radio" name="Gender" value="Female" />
</td>
</tr>
 
<!----- Address ---------------------------------------------------------->
<tr>
<td>地址: <br /><br /><br /></td>
<!-- <td><textarea name="Address" rows="4" cols="30"></textarea></td> -->
<td>
  <div class="row">								
  <div class="col-md-12 mb-3">									
      <input id="address" value="桃園市中壢區" class="twaddress" name="address" />		
  </div>
</td>
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
<!-- <input type="submit" class="submit_btn" value="儲存"> -->
<!-- <div class="submit">
  <input type="submit"  value="儲存" id="button-blue"/>
  <div class="ease"></div> -->

  <span class="submitAndSave" id="article-section-seemore-recipe">儲存</span>
</div>




<!-- <input type="reset" value="Reset"> -->
</td>
</tr>
</table>
 
</form>
 
</body>

              </div>
          
            















          








            
            <aside class="sidebar">
                <div id="leftside-navigation" class="nano">
                  <ul class="nano-content">
                    <li>
                      <a href="index.html"><i class="fa fa-dashboard"></i><img class="access-menu-icon1" src="image/S__12066824.jpg" ><span>我的帳戶</span></a>
                    </li>
                    <li class="sub-menu">
                      <a href="javascript:void(0);"><i class="fa fa-cogs"></i><img class="access-menu-icon1" src="image/S__12066823.jpg"><span>購買清單&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i class="arrow fa fa-angle-right pull-right"></i></a>
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
                      <a href="javascript:void(0);"><i class="fa fa-table"></i><img class="access-menu-icon1" src="image/S__12066821.jpg"><span>我的錢包/點數&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> </span><i class="arrow fa fa-angle-right pull-right"></i></a>
                      <ul>
                        <li><a href="tables-basic.html">交易紀錄</a>
                        </li>
              
                        <li><a href="tables-data.html">提款</a>
                        </li>
                      </ul>
                    </li>
                    <li class="sub-menu">
                      <a href="javascript:void(0);"><i class="fa fa fa-tasks"></i><img class="access-menu-icon1" src="image/S__12066822.jpg"><span>瀏覽紀錄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i class="arrow fa fa-angle-right pull-right"></i></a>
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
                      <a href="javascript:void(0);"><i class="fa fa-envelope"></i><img class="access-menu-icon1" src="image/S__12066820.jpg"><span>精選收藏&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i class="arrow fa fa-angle-right pull-right"></i></a>
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
                      <a href="javascript:void(0);"><i class="fa fa-bar-chart-o"></i><img class="access-menu-icon1" src="image/S__12066818.jpg"><span>我的課程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i class="arrow fa fa-angle-right pull-right"></i></a>
                      <ul>
                        <li><a href="charts-chartjs.html">課程紀錄</a>
                        </li>
                        <li><a href="charts-morris.html">1</a>
                        </li>
                        <li><a href="charts-c3.html">2</a></li>
                      </ul>
                    </li>
                    <li class="sub-menu">
                      <a href="javascript:void(0);"><i class="fa fa-map-marker"></i><img class="access-menu-icon1" src="image/S__12066827.jpg"><span>直播/課程管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i class="arrow fa fa-angle-right pull-right"></i></a>
                      <ul>
                        <li><a href="map-google.html">1</a>
                        </li>
                        <li><a href="map-vector.html">2</a>
                        </li>
                      </ul>
                    </li>
                   
                    <li class="sub-menu">
                      <a href="javascript:void(0);"><i class="fa fa-file"></i><img class="access-menu-icon1" src="image/S__12066826.jpg"><span>食譜管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></span><i class="arrow fa fa-angle-right pull-right"></i></a>
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
                      <a href="typography.html"><i class="fa fa-text-height"></i><img class="access-menu-icon1" src="image/S__12066825.jpg"><span>帳號設定</span></a>
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
		AOS.init();
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


















</body>

</html>