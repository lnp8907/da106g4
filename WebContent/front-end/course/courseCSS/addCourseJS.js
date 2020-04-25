//新增課程料理
$(".course-recipe-add")
		.click(
				function(e) {
					$("#course-recipe-group>div:last").after(
									"<div class='ingredient-group-row' id='course-recipe-group'><input name='course_detail1' type='text' class='recipe-ingredient-row-name' placeholder='輸入課程料理...''><span class='recipe-ingredient-delete'></span></div>");
				});
// 新增學習重點

$(".recipe-point-add")
		.click(
				function(e) {
					$("#course-point-group>div:last").after(
									"<div  id='course-point-group' class='ingredient-group-row course-point-row'><textarea type='text' class='recipe-ingredient-row-name' name='course_detail2'placeholder='輸入課程重點...' rows='3'></textarea><span class='recipe-ingredient-delete'></span></div>");
				});
// 移除元素
$('.course-container').on("click", ".recipe-ingredient-delete", function() {
	$(this).parent(".ingredient-group-row").remove();
});

// 點擊圖片觸發檔案上傳
$("#uploadFile").click(function() {
	$("#imageFile").click();
});

// 實現圖片預覽
$("#imageFile").change(function() {
	readURL(this);
});
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#uploadFile").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

// 清除預覽圖片
$("#reset").click(function() {
	$(".course_price").val('');
	$("#uploadFile").attr('src', '../../image/icon/uploadPic.png');
});
