<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/tip.css" />
<div class="subs_banner">
	<a class="close"></a>
	<a class="open_url">关注公众号</a>
	<p>享食光带你吃遍全城美食</p>
</div>
<div class="cm_flog"></div><!--遮罩层-->
<div class="cm_win">
	<img class="qrcode" src="images/1.jpg">
	<p class="tip">轻扫上方二维码，关注微信公众号，获取更多资讯</p>
	<a href="javascript:;" class="close"></a>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script>
$(".open_url").click(function() {
	$.ajax({
		url:"resource/serviceCenter_info?account.pkey=${account.pkey}",
		type:"POST",
		dataType:"json",
		success:function(result) {
			$(".qrcode").attr("src", "../"+result.qrcode);
			$(".cm_flog").show();
			$(".cm_win").show();
		}
	});
});
$(".subs_banner .close").click(function() {
	$(".subs_banner").hide();
});
$(".cm_win .close").click(function() {
	$(".cm_flog").hide();
	$(".cm_win").hide();
});
</script>