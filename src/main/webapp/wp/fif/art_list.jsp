<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="zh">
<head>
<title>${show.title}</title>
<meta name="Keywords" content="${show.title}">
<meta name="Description" content="${show.title}">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=ture">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/reset.css">
<link rel="stylesheet" type="text/css" href="wp/fif/assets/css/style.css?v=0.6">
<link rel="stylesheet" type="text/css" href="style/dropload.css">

<!-- Grab Google CDN's jQuery. fall back to local if necessary -->
<script src="js/jquery-1.11.3.min.js"></script>
<script src="wp/fif/assets/js/functions.js"></script>
<script src="js/dropload.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
${jsCode}
</script>
<script>
var start = 0;
var limit = 6;
var dropload;
$(function(){
	start = $(".search-list ul li").length;
	var _areaPkey = "${area.pkey}";
	var _ctgPkey = "${ctg.pkey}";
	var _themePkey = "${theme.pkey}";
	if (_areaPkey == "")
		_areaPkey = 0;
	if (_ctgPkey == "")
		_ctgPkey = 0;
	if (_themePkey == "")
		_themePkey = 0;
	$(".serch-qx").click(function(){
		$("input[name='search']").reset();
	});
	dropload = $('.search-list').dropload({
        scrollArea : window,
        loadDownFn : function(me){
        	loadImage();
        	var section = $("section");
        	console.log("section.length:"+section.length)
        }
    });
	function loadImage() {
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				limit : limit,
				start : start,
				search : "${search}",
				"show.pkey" : ${show.pkey},
				"theme.pkey" : _themePkey,
				"area.pkey" : _areaPkey,
				"ctg.pkey" : _ctgPkey,
			},
			url : "/expand_wp_WpArt_ajaxcute",
			success : function(data) {
				var li = "";
				console.log("result.length:"+data.result.length)
				$.each(data.result, function(index, line) {
					li += "<li><a href=" +line.url+"><img src=" +line.imgUrl+ ">";
					li += "<div class=\"search-bg\"></div>";
					li += "<div class=\"search-about\">";
					li += "<p>" +line.title+ "</p>";
					li += "<p>" +line.description+ "</p>";
					li += "<p class=\"ttb\">${sarg1} · " +line.theme+ "<i class=\"icon-map-marker\"></i>" +line.bsn+ "</p>";
					li += "</div>";
					li += "</a>";
					li += "</li>";
				});
				start += data.result.length;
				setTimeout(function(){
					$(".search-list ul").append(li);
	                dropload.resetload();
	            },1000);
			},
			error : function(a,b,c) {
				console.log(a)
				console.log(b)
				console.log(c)
				console.log("error")
				dropload.resetload();
			}
		});
	}
	$(".themeList, .areaList, .ctgList").click(function(){
		if ($(this).hasClass("areaList"))
			_areaPkey = $(this).attr("pkey");
		else if($(this).hasClass("ctgList"))
			_ctgPkey = $(this).attr("pkey");
		else
			_themePkey=$(this).attr("pkey");
		window.location.href="expand_wp_WpArt_showList?show.pkey=${show.pkey}&theme.pkey=" + _themePkey+"&area.pkey=" + _areaPkey + "&ctg.pkey=" + _ctgPkey;
	});
	$("#searchText").click(function(){
		$("#form").submit();
		});
	$("#wpXwTheme").click(function() {
		$("#area").hide();
		$("#ctg").hide();
		$("#i1").removeClass("icon-angle-down");
		$("#i2").removeClass("icon-angle-down");
		$("#i3").toggleClass("icon-angle-down");
		$("#theme").slideToggle();
	});
	$("#wpXwArea").click(function() {
		$("#ctg").hide();
		$("#theme").hide();
		$("#i2").removeClass("icon-angle-down");
		$("#i3").removeClass("icon-angle-down");
		$("#i1").toggleClass("icon-angle-down");
		$("#area").slideToggle();
	});
	$("#wpXwCtg").click(function() {
		$("#area").hide();
		$("#theme").hide();
		$("#i1").removeClass("icon-angle-down");
		$("#i3").removeClass("icon-angle-down");
		$("#i2").toggleClass("icon-angle-down");
		$("#ctg").slideToggle();
	}); 
	
});
</script>
</head>

<body>
	<header>
		<div class="search">
			<div class="search-main">
				<div class="search-input">
				<form action="expand_wp_WpArt_showList" id="form"  name= "myform" method = "post">
				  <a id="searchText" href="#"><i class="icon-search icon-large"></i></a>
					<input type="search" name="search" placeholder="请输入餐厅名字" value="${search}">
					<input type="hidden" name="show.pkey" value="${show.pkey}">
					<input type="hidden" name="area.pkey" value="${area.pkey}">
                    <input type="hidden" name="ctg.pkey" value="${ctg.pkey}">
				</form>
					
				</div>
				<div class="serch-qx">
					<a href="">取消</a>
				</div>
			</div>
		</div>
	</header>

	<section>
        <div class="search-type">
            <a  href="#"><span  id="wpXwTheme"><s:if test="theme.pkey != null && theme.pkey != 0">${theme.name }</s:if><s:else>主题</s:else> <i class="icon-angle-up" id="i3"></i></span></a>
            <a  href="#"><span  id="wpXwArea"><s:if test="area.pkey != null && area.pkey != 0">${area.name }</s:if><s:else>区域</s:else> <i class="icon-angle-up" id="i1"></i></span></a>
            <a  href="#"><span  id="wpXwCtg"><s:if test="ctg.pkey != null && ctg.pkey != 0">${ctg.name }</s:if><s:else>分类</s:else> <i class="icon-angle-up" id="i2"></i></span></a>
        </div>
        <div class="search-box" id="theme">
        	<div class="search-box-list clearfix">
                <ul>
                <li pkey="0" class="themeList"><a>全部主题</a></li>
                <s:iterator value="themeList" id="line" status="st">
                    <li pkey=${line.pkey} class="themeList"><a>${line.name}</a></li>
                </s:iterator>
                    <li class="left_fix"></li>
                    <li class="left_fix"></li>
                </ul>
            </div>
        </div>
        <div class="search-box" id="area">
        	<div class="search-box-list clearfix">
                <ul>
                <li pkey="0" class="areaList"><a>全部区域</a></li>
                <s:iterator value="areaList" id="line" status="st">
                    <li pkey=${line.pkey} class="areaList"><a>${line.name}</a></li>
                </s:iterator>
                    <li class="left_fix"></li>
                    <li class="left_fix"></li>
                </ul>
            </div>
        </div>
        <div class="search-box" id="ctg">
        	<div class="search-box-list clearfix">
                <ul>
                <li pkey ="0" class="ctgList"><a>全部分类</a></li>
                <s:iterator value="ctgList" id="line" status="st">
                    <li pkey=${line.pkey} class="ctgList"><a>${line.name}</a></li>
                </s:iterator>
                    <li class="left_fix"></li>
                    <li class="left_fix"></li>
                </ul>
            </div>
        </div>
		<div class="search-list">
			<ul>
			    <s:iterator value="list" id="line" status="st">
				<li><a href="${line.url}"><img src="${line.imgUrl}">
					<div class="search-bg"></div>
						<div class="search-about">
							<p>${line.title}</p>
							<p class="ttb">
							<s:iterator value="themeList" id="t" status="s"><s:if test="#line.theme== #t.pkey">${sarg1} · ${t.name}</s:if></s:iterator>
							<s:iterator value="bsnList" id="b" status="x"><s:if test="#line.bsn== #b.pkey"><i class="icon-map-marker" style="height:50px;" latitude="${b.latitude}" longitude="${b.longitude}" name="${b.name}" address="${b.addr}"></i>${b.name}</s:if></s:iterator>
							</p>
						</div>
					</a>
				</li>
			</s:iterator>
			</ul>
		</div>
	</section>
</body>
</html>