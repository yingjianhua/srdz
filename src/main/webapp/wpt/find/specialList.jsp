﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<link type="text/css" rel="stylesheet" href="css/css.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<!--这段JS不能删除，且前面最好别再放其它JS-->
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
	<!--footer 底部导航-->
		<jsp:include page="../menu.jsp">
			<jsp:param value="2" name="currMenu"/>
		</jsp:include>
	<!--footer 底部导航-->
	<div class="find_top">
		<span><a href="javascript:;" class="hover" id="special">专题</a></span>
		<span><a href="listHot?account.pkey=${account.pkey }" id="hot">热销</a></span>
		<a href="javascript:;" class="select_city" >温州</a>	
	</div>
	<div class="special">
	<s:iterator value="specials" var="line" >
	<a href="javascript:;" class="find_spe" pkey="${line.pkey}">
		<div class="txt">${line.title }</div>
		<div class="findspe_flog"></div>
		<img data-original="../${line.baseImgUrl}" class="lazy"/>	
	</a>
	<div class="find_line"></div>
	</s:iterator>
	</div>
	<div class="hmcity_flog"></div>
	<!--hmcity_list 城市列表-->
		<div class="hmcity_list">
			<ul class="hmcity_items">
			<s:iterator value="citys" var="city">
				<li><a pkey="<s:property value="#city.pkey" />" href="javascript:;" ><s:property value="#city.name" /></a></li>
			</s:iterator>
			</ul>	
			<div class="hmcity_nobtn">
				<span>没有您的城市？来告诉我们<i></i></span>
			</div>	
		</div>
	<!--hmcity_list 城市列表-->
	
	<!--hmcity_add 添加城市-->
		<div class="hmcity_add">
			
			<a href="javascript:;" class="back_btn">&lt; 返回</a>
			<dl>
				<dt>输入您所在的城市   享食光马上就来</dt>
				<dd>
					<input type="text" class="new_city" placeHolder="例: 温州" />	
				</dd>	
			</dl>
			
			<dl>
				<dt>PS.城市合作请联系客服</dt>
				<dd>
					<a href="javascript:;" class="btn ok lt">确 认</a>
					<a href="javascript:;" class="btn esc rt">取 消</a>
				</dd>	
			</dl>
				
		</div>
	<!--hmcity_add 添加城市-->
<div style="width:640px;height:83px"></div>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script>
$(function(){
	$("img.lazy").lazyload({
		placeholder : "images/emptySpecial.jpg",
		effect: "show",
		threshold:200
		});
	$(".find_spe").click(function(){
		var pkey = $(this).attr("pkey");
		window.location.href="showSpecial?id="+pkey+"&account.pkey=${account.pkey}";
	});
	//城市选择的JS
	$(".hm_citybtn").on("click",function(){
		
		if($(".hmcity_flog").hasClass("hmcity_flogshow")){	//隐藏
			
			$(".hmcity_list").hide();
			$(".hmcity_add").hide();
			$(".hmcity_flog").removeClass("hmcity_flogshow");	
			
		}else{	//显示
			$(".hmcity_list").show();
			$(".hmcity_flog").addClass("hmcity_flogshow");		
		}
			
	});
	$(".hmcity_flog").on("click",function(){
		$(".hmcity_list").hide();
		$(".hmcity_flog").removeClass("hmcity_flogshow");			
	});
	$(".hmcity_items li a").on("click",function(){	//选择城市
		
		$(".hmcity_items li a").removeClass("hover");
		$(this).addClass("hover");
		$(".hm_citybtn").html($(this).html());
		$(".hm_citybtn").attr("pkey", $(this).attr("pkey"));
		$(".hmcity_list").hide();
		$(".hmcity_add").hide();
		$(".hmcity_flog").removeClass("hmcity_flogshow");	
		$.ajax({
			dataType : "json",
			url : "resource/city_select?account.pkey=${account.pkey}",
			type : "POST",
			data : {"id" : $(this).attr("pkey")}
		})
	});	
})
</script>
</html>