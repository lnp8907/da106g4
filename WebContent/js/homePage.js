	var getLatest = function() {
		$.ajax({
			type : "GET",
			url : "/DA106_G4_Foodporn_Git/AjaxResponse",
			data : {
				"action" : "getLatest"
			},
			dataType : "json",
			success : function(data) {
				$("#recipe-right-title-p-1").text(data.recipe_name);
				$("#article-recipe-right-photo-1").attr("src",data.recipe_photo);
				$("#recipe-right-title-a-1").attr("href",'/DA106_G4_Foodporn_Git/front-end/recipe/RecipeServlet?action=getOne_For_Display&recipe_id=' + data.recipe_id)
			},
			error : function() {
				alert('Ajax連線異常');
			}
		})
	}

	var getMostPopular = function() {
		$.ajax({
			type : "GET",
			url : "/DA106_G4_Foodporn_Git/AjaxResponse",
			data : {
				"action" : "getMostPopular"
			},
			dataType : "json",
			success : function(data) {
				$("#recipe-right-title-p-2").text(data.recipe_name);
				$("#article-recipe-right-photo-2").attr("src",data.recipe_photo);
				$("#recipe-right-title-a-2").attr("href",'/DA106_G4_Foodporn_Git/front-end/recipe/RecipeServlet?action=getOne_For_Display&recipe_id=' + data.recipe_id)
			},
			error : function() {
				alert('Ajax連線異常');
			}
		})
	}
	
	var getCourse = function() {
		$.ajax({
			type : "GET",
			url : "/DA106_G4_Foodporn_Git/AjaxResponse",
			data : {
				"action" : "getCourse"
			},
			dataType : "json",
			success : function(data) {
				$.each(data, function(i, courseVO){	
				$('#course-list-ul').append(
							"<li class='course-list-li' data-aos='fade-up' data-aos-duration='2000'>" +
							"<a href='/DA106_G4_Foodporn_Git/front-end/course/course.do?action=getOne_For_Display&course_id="+ courseVO.course_id +"'> " +
							"<div class='course-li-img-div'> " +
							"<img class='course-li-img' " +
							"src='/DA106_G4_Foodporn_Git/front-end/course/photo?course_id="+ courseVO.course_id +"'>"+
							"</div> <span class='course-li-title'>"+courseVO.course_name +"</span>" +
							"<span class='course-list-date-span'>開課時間</span><span " +
							"class='course-date-li' >"+ courseVO.course_start+"</span> </a></li>");
				$('#course-sider-list').append(						
						"<div class='course-sider-list-viewer'>"+
						"<a href='/DA106_G4_Foodporn_Git/front-end/course/course.do?action=getOne_For_Display&course_id="+courseVO.course_id+"'>" +
						"<div class='course-li-img-div' >"+	
							"<img class='course-li-img'  src='/DA106_G4_Foodporn_Git/front-end/course/photo?course_id="+ courseVO.course_id +"' alt='"+courseVO.course_name +"的圖片'>"+
						"</div>"+
						"<span class='course-li-title' >"+courseVO.course_name +"</span>"+
						"<span class='course-list-date-span'>開課時間</span><span class='course-date-li' >"+ courseVO.course_start+"</span>"+
						"</a></div>");
						});
				
				//<!--課程橫幅陳列-->
				$('.course-sider-list').slick({
					dots : true,
					centerMode : true,
					centerPadding : '60px',
					slidesToShow : 3,
					responsive : [ {
						breakpoint : 768,
						settings : {
							arrows : false,
							centerMode : true,
							centerPadding : '40px',
							slidesToShow : 3
						}
					}, {
						breakpoint : 480,
						settings : {
							arrows : false,
							centerMode : true,
							centerPadding : '40px',
							slidesToShow : 1
						}
					} ]
				});					
				},
						error : function() {
						alert('Ajax連線異常');
			}
		});

	}
	
	
	var getLivestream = function() {
		$.ajax({
			type : "GET",
			url : "/DA106_G4_Foodporn_Git/AjaxResponse",
			data : {
				"action" : "getLivestream"
			},
			dataType : "json",
			success : function(data) {
				var i = 1;
				$.each(data, function(a, livestreamVO){	
					
					$("#livestream-right-link-"+i).attr("href","/DA106_G4_Foodporn_Git/front-end/livestream/LivestreamServlet?action=getOne_For_Display&livestream_id="+livestreamVO.livestream_id)
					$("#livestream-right-photo-"+i).attr("src","/DA106_G4_Foodporn_Git/LivestreamPhotoReader?livestream_id="+ livestreamVO.livestream_id);
					$("#livestream-right-title-chef-"+i).text(livestreamVO.title);
					$("#livestream-right-livestream-name-"+i).text(livestreamVO.introduction);
					i++;
				});
			},
			error : function() {
				alert('Ajax連線異常');
			}
		})
	}
	
	var getMostPopLS = function() {
		$.ajax({
			type : "GET",
			url : "/DA106_G4_Foodporn_Git/AjaxResponse",
			data : {
				"action" : "getMostPopLS"
			},
			dataType : "json",
			success : function(livestreamVO) {
				$("#livestream-left-1-video-h3").text(livestreamVO.title);
				$("#livestream-left-1-video").attr("src","/DA106_G4_Foodporn_Git/LivestreamVideoReader?livestream_id="+ livestreamVO.livestream_id);
				$("#livestream-left-1-video-p").text(livestreamVO.introduction);
			},
			error : function() {
				alert('Ajax連線異常');
			}
		})
	}
	
	
	
	
	var getMonth = function(){
		var date = new Date();
		var month = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
		var blessing = new Array(
			"下雪了，我帶著祝福走向田間，雪花落在唇上，伴著想念的味道，慢慢融入口中。冬天到了，願雪花帶去我的祝福，讓你的心情如雪花般飄揚。",
			"潔白的雪花，給你美麗的冬季；合理的飲食，給你健康的腸胃；適當的鍛煉，給你健康的身體；溫馨的祝福，給你快樂的心情。願健康快樂陪伴你整個冬季！",
			"春天到，踏青郊遊把煩惱都拋掉；春天到，早睡早起把疲勞都趕跑；春天到，堅持鍛煉把健康來擁抱；春天到，朋友相互用短信來問好：春分快樂！",
			"春天是溫暖的代言，活力是造型，美麗是背景，快樂是台詞，幸福是心思，問候首播，祝福重播，如果你喜歡可以存在手機裡反複播，祝你立春好運。",
			"春風微拂，拂動快樂的心弦；春雨如織，潤透心靈的梯田；春光燦爛，炫動明媚的春色；春天來到，憑添無限的生機。願你播下春天的種子，幸福一生！",
			"天空中白雲從不向天空承諾去和留，卻朝夕相伴；星星從不向夜許諾光明，卻努力閃爍；朋友從不向對方傾訴思念，卻永遠牽掛！朋友，天熱了注意防暑！",
			"天氣是炎熱的，心情是愉悅的，果汁是冰鎮的，悲傷是凍結的，煩惱是沒有的，健康是常在的，幸福是一直的，夏季是涼快的！",
			"一片綠葉，帶著夏日的清涼；一縷清風，瀰漫田野的芳香；一朵白雲，伴隨心靈的歡暢；一絲真情，承載所有的夢想，輕輕地為你送上祝福與安詳。",
			"立秋時，要盡量少吃蔥薑等，多食酸味果蔬，宜食生地粥，以滋陰潤燥。可食用芝麻糯米粳米蜂蜜枇杷菠蘿乳品等柔潤食物，以益胃生津。",
			"落葉知秋，情誼相守；今日立秋，真心問候。祝你：開心勿愁，快樂無憂；愛情醇厚，浪漫依舊；名利雙收，幸福永久！",
			"落葉黃，秋雨涼，一年立秋又來到；早晚涼，溫差大，增減衣服莫偷懶；工作忙，生活累，身體健康是關鍵；朋友情，記心中，短信替我送關懷！",
			"冬天到，寒氣襲，鍛煉不忘記，多做運動好身體；天氣變，北風起，記得多添衣，注意保暖要切記；好朋友，常惦記，問候短信表心意，願你開心快樂過冬季！"	
		);
		$("#month").text(month[date.getMonth()]);
		$("#blessing").text(blessing[date.getMonth()]);
	}