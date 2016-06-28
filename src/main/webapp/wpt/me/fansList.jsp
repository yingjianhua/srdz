<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet" href="css/css.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
    var phoneWidth =  parseInt(window.screen.width);
    var phoneScale = phoneWidth/640;
    var ua = navigator.userAgent;
    if (/Android (\d+\.\d+)/.test(ua)){
        var version = parseFloat(RegExp.$1);
        if(version>2.3){
            document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
        }else{
            document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
        }
    } else {
        document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
    }
</script>
<title>享食光-私人定制</title>
</head>
<body>
	<div class="main">
		<div class="search">
			<img src="sousuo.png">
			<div style="border: 1px solid; height: 0.84rem;">
				<input type="text" placeholder="输入粉丝ID" id="inp"></input>
			</div>
			<div class="searchBtn" id="btn">搜索</div>
		</div>
		<div class="nav">
			<ul>
				<li class="change"><div class="num">我的粉丝（1）</div></li>
				<li><div class="num">粉丝圈（1）</div></li>
			</ul>
			<div class="clean"></div>
		</div>
		<div class="list">
			<ul class="listul">
			</ul>
			<div class="empty">
				<img src="fans.png">
				<p class="emptyT">
					你还没有<span></span>粉丝呢
				</p>
			</div>
			<div class="more">
				<div class="morebtn" id="morebtn">
					<span>查看更多</span> <img src="down.png">
				</div>
			</div>
		</div>
	</div>
	<div class="tongjicnzz" style="display: none;">
		<script
			src="http://s95.cnzz.com/z_stat.php?id=1258654236&web_id=1258654236"
			language="JavaScript"></script>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
</html>