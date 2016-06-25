<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="zh">
<head>
<title>公众号活动</title>
<meta name="Keywords" content="公众号活动! ">
<meta name="Description" content="公众号活动! ">
<meta name="viewport" content="initial-scale=1,maximum-scale=1, minimum-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<script>
	var deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth > 750){
		deviceWidth = 750
	};
	document.documentElement.style.fontSize = deviceWidth/7.5 + 'px';
</script>
</head>

<body>
	<header>
		<div class="banner">
			<img src="${bnft.imgUrl}">
		</div>
	</header>

	<section>
		<div class="hdjs">
			<div class="hdjs-tt">
			    <p>${bnft.name}</p>
				<p>活动介绍</p>
				<p>将此微信转发到朋友圈，并将“<span class="xian">截图+姓名+手机号</span>”按照以下表单反馈给我们，我们将抽出五名超幸运粉丝获得文章中所提到的<span>温州大剧院</span>12月任意一场演出的<span>门票两张</span>！</p>
			</div>
			<form action="expand_wax_WaxBnftEntry_xcute" method="post" enctype="multipart/form-data">
				<div class="hdjs-form">
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">您的姓名：</div>
						<div class="hdjs-form-right"><input type="text" placeholder="请输入您的姓名" name="bean.name"></div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">联系电话：</div>
						<div class="hdjs-form-right"><input type="text" placeholder="请输入手机号" name="bean.phone"></div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left"><p style="padding-top:0.4rem;">朋友圈截图：</p></div>
						<div class="hdjs-form-img1" style="height:1.8rem;"><img src="assets/img/pic1.jpg" height="100%"></div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">活动联系人：</div>
						<div class="hdjs-form-right">天天天</div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-left">联系人电话：</div>
						<div class="hdjs-form-right">12345678901</div>
					</div>
					<div class="hdjs-form-tr clearfix">
						<div class="hdjs-form-btn"><input type="submit" value="提交"></div>
					</div>
				</div>
			</form>
		</div>
	</section>



</body>
<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
<script src="../../js/jquery-1.11.3.min.js"></script>

<script src="assets/js/functions.js"></script>

</html>
