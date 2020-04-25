// JavasScript for LoginForm
$(".login").click(function () {
    $(".login-wrap").show()
    $(function () {
        $('body,html').addClass('add');//彈出視窗時給body新增CSS 禁止滾動
    });
});
$(".login-close").click(function () {
    $(".login-wrap").hide()
    $('body,html').removeClass('add');//關閉視窗時給body移除CSS 禁止滾動
});
function centerHandler() {/*設定置中的函式*/
    var scrollDist = $(window).scrollTop();/*取得捲動長度*/
    var myTop = ($(window).height() - $(".login-wrap").height()) / 2 + scrollDist;
    /*取得垂直中央位置*/
    var myLeft = ($(window).width() - $(".login-wrap").width()) / 2;
    /*取得水平中央位置*/
    $(".login-wrap").offset({ top: myTop, left: myLeft });
    /*設定區塊於水平與垂直置中*/
}
// // centerHandler(); /*呼叫置中函式，使廣告區塊置中*/
// // $(window).scroll(centerHandler); /*當網頁捲動時呼叫置中函式*/
// $(window).resize(centerHandler); /*當視窗縮放時呼叫置中函式*/