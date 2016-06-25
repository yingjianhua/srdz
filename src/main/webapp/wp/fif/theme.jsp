<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="zh">
<head>
<title>${bean.title}</title>
<meta name="Keywords" content="${bean.title}">
<meta name="Description" content="${bean.title}">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/style.css">
<style type="text/css">
.banner img{
	display: block;
}
.banner{ 
	width:100%;
	margin:0 auto; 
	position:relative; 
	overflow:hidden;   
}
.banner .hd{ 
	width:100%; 
	height:11px;  
	position:absolute; 
	z-index:1; 
	bottom:5px; 
	text-align:center;  
}
.banner .hd ul{ 
	height:5px; 
	padding:3px 5px;
	font-size:0; 
	vertical-align:top;
}
.banner .hd ul li{ 
	display:inline-block; 
	width:0.4rem; 
	height:0.4rem; 
	border-radius:0.4rem; 
	background:#d6b395; 
	margin:0 .2rem;  
	vertical-align:top; 
	overflow:hidden;   
}
.banner .hd ul .on{ background:#f0ba00;  }

.banner .bd{ 
	position:relative; 
	z-index:0; 
}
.banner .bd li img{ 
	width:100%;
	background:url(../img/loading.gif) center center no-repeat;  
}
.banner .bd li a{ 
	-webkit-tap-highlight-color:rgba(0, 0, 0, 0);
}
.banner .bd div{color:#fff;}
</style>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
${jsCode}
</script>
</head>

<body>
	<header>
		<div class="search">
			<div class="search-main">
				<div class="search-input">
					<form action="expand_wp_WpArt_showList" id="form"  name= "myform" method = "post">
				    <a id="searchText" href="#"><i class="icon-search icon-large"></i></a>
					<input type="search" name="search" placeholder="请输入餐厅名字">
					<input type="hidden" name="show.pkey" value="${bean.pkey}">
				</form>
				</div>
				<div class="serch-qx">
					<a href="">取消</a>
				</div>
			</div>
		</div>
	</header>

	<section>
	<div class="banner" id="banner">
		<div class="hd">
        	<ul></ul>
    	</div>
    	<div class="bd">
        	<ul>
        	 <s:iterator value="bannerList" var="banner">
        	<li><a href="${banner.artObj.url}"><img src="${banner.artObj.imgUrl}">
        		<div class="banner-about">
					<p class="tt1">${banner.artObj.title}</p>
					<s:iterator value="themeList" var="t" status="s"><s:if test="#banner.artObj.theme== #t.pkey">${sarg1} ·${t.name}</s:if></s:iterator>
					<s:iterator value="bsnList" var="b" status="x"><s:if test="#banner.artObj.bsn== #b.pkey"><i class="icon-map-marker"></i>${b.name}</s:if></s:iterator>
				</div></a>
					</li>
        	</s:iterator>
        	</ul>
    	</div>
	</div>
		<div class="index_tt">
			<img src="wp/fif/assets/img/index_tt.jpg">
		</div>

		<div class="index-list clearfix">
			<ul>
			<s:iterator value="themeList" var="line" status="st">
				<li><a href="expand_wp_WpArt_showList?show.pkey=${bean.pkey}&theme.pkey=${line.pkey}"><img src="${line.imgUrl}">
					<div class="list-bg"></div>
					<div class="list-box">
						-${line.name}-
					</div>
					</a>
				</li>
			</s:iterator>
			</ul>
		</div>
	</section>

	<footer>
		<a href="expand_wp_WpArt_eat?show.pkey=${bean.pkey}"><img src="wp/fif/assets/img/footer.jpg">
			<div class="footer-tt">
				今天吃什么
			</div>
		</a>
	</footer>


</body>
<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
<script>
$(function(){
		$("#searchText").click(function(){
			 $("#form").submit();
		});
		$(".serch-qx").click(function(){
			$("input[name='search']").reset();
		});
});
</script>
<script src="wp/fif/assets/js/functions.js"></script>
<script src="wp/fif/assets/js/libs/TouchSlide.1.1.js"></script>
<script>
TouchSlide({ 
	slideCell:"#banner",
	titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
	mainCell:".bd ul", 
	effect:"leftLoop", 
	autoPlay:true,//自动播放
	autoPage:true, //自动分页
});
</script>
</html>
