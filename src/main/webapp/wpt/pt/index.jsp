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
<script type="text/javascript">
${jsCode}
</script>
<body class="home-bg">	
	<a href="javascript:;" class="hm_citybtn" ></a>
	<dl class="hm_tip">
		<dt><img src="images/hm_logo.png" /></dt>
		<dd>—<span>量身定制您的私享盛宴</span>—</dd>	
	</dl>
	
	<a href="javascript:;" class="hm_go">开启你的私人定制之旅</a>
	
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="1" name="currMenu"/>
	</jsp:include>
	<!--footer 底部导航-->
	
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
<jsp:include page="../messagebox.jsp"/>
	<!--hmcity_add 添加城市-->
	
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
$(function() {
	$.ajax({
		url : "resource/city_currCity",
		type : "post",
		dataType : "json",
		success : function(result) {
			if(result) {
				$(".hm_citybtn").attr("pkey", result.id);
				$(".hm_citybtn").html(result.name);
			}
		}
	})
})
		var currCity = $(".hm_citybtn").text();
	$(".hmcity_items li a").each(function(index){
		var city = $(this).text();
		if(city == currCity)
			$(this).addClass("hover");
	})
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
			url : "resource/city_select",
			type : "POST",
			data : {"id" : $(this).attr("pkey")}
		})
	});
	
	$(".hmcity_nobtn").on("click",function(){	//其它城市
		$(".hmcity_add").show();
		$(".hmcity_list").hide();	
		$(".hmcity_flog").addClass("hmcity_flogshow");	
	});
	
	$(".hmcity_add .back_btn,.hmcity_add .esc").on("click",function(){	//其它城市
		$(".hmcity_add").hide();
		$(".hmcity_list").show();	
		$(".hmcity_flog").addClass("hmcity_flogshow");	
	});
	
	$(".hmcity_add .ok").on("click",function(){
		if($(".hmcity_add .new_city").val() == ""){
			tipbox("请输入城市");	
			return;
		}else{
			$(".hmcity_list").hide();
			$(".hmcity_add").hide();
			$(".hmcity_flog").removeClass("hmcity_flogshow");
			$.ajax({
				url : "resource/city_petition",
				type : "POST",
				data : {"petitionCity" : $(".hmcity_add .new_city").val(),
						"account.pkey"  : "${account.pkey}"},
				success: function(data){
					tipbox(data);
				}	
			})
			
		}
			
	});
</script>
<script>
$(function() {
	$(".hm_go").click(function() {
		location.href = "applyService?account.pkey="+${account.pkey };
	})
})
</script>
</html>