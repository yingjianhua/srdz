<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.subs_banner {
	background: rgba(255, 255, 255, 0.95) url("images/share.jpg") no-repeat scroll 0.65rem center / auto 0.5rem;
    border-top: 1px solid #ccc;
    bottom: 0;
    height: 0.7rem;
    left: 0;
    position: fixed;
    width: 100%;
    z-index: 99;
}
.subs_banner a.close {
    background: rgba(0, 0, 0, 0) url("images/banner_close.png") no-repeat scroll 0.2rem center / auto 0.25rem;
    height: 100%;
    left: 0;
    opacity: 1;
    width: 0.7rem;
}
.subs_banner a {
    cursor: pointer;
    display: block;
    position: absolute;
    top: 0;
}
.subs_banner a.open_url {
    background-color: #ffc83b;
    border-radius: 2px;
    color: #fff;
    font-size: 0.2rem;
    height: 0.5rem;
    line-height: 0.5rem;
    right: 0.2rem;
    text-align: center;
    top: 0.1rem;
    width: 1.2rem;
}
.subs_banner p {
	position:absolute;
	left:1.3rem;
	line-height:0.7rem;
	color:#4c4c4c;
}

.cm_flog{
	position:fixed;
	left:0;
	top:0;
	width:100%;	
	height:100%;
	z-index:200;
	background:#000;
	opacity:0.2;
}
.cm_win{
	background-color:#fff;
	width:5.5rem;	
	position:fixed;
	left:50%;
	top:50%;
	margin:-3.13rem 0 0 -2.75rem;
	z-index:201;
	overflow:hidden;
	text-align:center;
	border-radius:0.3rem;
	border:1px solid #dbdbdb;
}
.cm_win .qrcode{
	width:4.5rem;
	height:4.5rem;
	margin:0.5rem;
}
.cm_win .tip{
	font-size:20px;
}
.cm_win .close{
	background: rgba(0, 0, 0, 0) url("images/banner_close.png") no-repeat scroll 0rem center / auto 0.45rem;
	position:absolute;
	top:0;
	right:0;
	height:0.7rem;
    width: 0.7rem;
}
</style>
<div class="subs_banner">
	<a class="close"></a>
	<a class="open_url">关注公众号</a>
	<p>享食光带你吃遍全城美食</p>
</div>
<div class="cm_flog"></div><!--遮罩层-->
<div class="cm_win">
	<img class="qrcode" src="images/1.jpg">
	<p class="tip">轻扫二维码，关注微信公众号</p>
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
			$(".qrcode").attr("src", "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3851013351,2699800874&fm=58");
			//$(".qrcode").attr("src", result.qrcode);
			$(".cm_flog").show();
			$(".cm_win").show();
			console.log(result.qrcode)
		}
	});
});
$(".cm_win .close").click(function() {
	$(".cm_flog").hide();
	$(".cm_win").hide();
});
</script>