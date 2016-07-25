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
<style type="text/css">
a.select_city_btn{
	height:30px;	
	line-height:30px;
	color:#fff;
	font-size:26px;
	padding:35px;
	margin:0 0 0 35px;
	background:url("images/hm_citybtn.png") no-repeat left center;
}
.bg{
	position: relative;
	margin-top: 300px;
	text-align: center;
	width: 100%;
}
.bg img{
	width: 220px;
}
.bg p{
	font-size: 27px;
	margin-top: 20px;
	color: #9a9a9a;
}
.gray{
	background: #f0f0f0;
}
</style>
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
		<a href="javascript:;" class="select_city_btn select_city" >${sessionScope.city.name }</a>	
	</div>
	<div class="special">
	<s:iterator value="specials" var="line" >
	<a href="javascript:;" class="find_spe" pkey="${line.pkey}">
		<div class="txt">${line.title }</div>
		<div class="findspe_flog"></div>
		<img data-original="../${line.baseImgUrl}" class="lazy"/>	
	</a>
	<div class="find_line" style="background-color:#fff"></div>
	</s:iterator>
	</div>
	<div class="hmcity_flog"></div>
	<!--hmcity_list 城市列表-->
		<div class="hmcity_list" style="position:fixed;">
			<ul class="hmcity_items">
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
	<div class="bg" style="display: none;">
		<img alt="" src="images/emptyJ.png">
		<p>享食光尚未登陆该城市</p>
		<p>点击<a href="javascript:;" class="select_city" style="color:black;">选择城市</a><p>
	</div>
	<jsp:include page="../messagebox.jsp"/>
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
	if($(".special a").size() == 0) {
		$(".bg").show();
		$("body").addClass("gray");
	}
	var currCity = $(".select_city_btn").text();
	$.ajax({
		url:"resource/city_listCity?account.pkey=${account.pkey}",
		type:"POST",
		dataType:"json",
		success:function(result) {
			var citys = "";
			$.each(result, function(index, line){
				if(line.name == currCity) {
					citys += '<li><a pkey="'+line.id+'" href="javascript:;" class="hover">'+line.name+'</a></li>';	
				} else {
					citys += '<li><a pkey="'+line.id+'" href="javascript:;" >'+line.name+'</a></li>';
				}
			});
			$(".hmcity_items").html(citys);
			$(".hmcity_items li a").on("click",function(){	//选择城市
				if($(this).hasClass("hover")) {return;}
				$(".hmcity_items li a").removeClass("hover");
				$(this).addClass("hover");
				$(".select_city_btn").html($(this).html());
				$(".select_city_btn").attr("pkey", $(this).attr("pkey"));
				$(".hmcity_list").hide();
				$(".hmcity_add").hide();
				$(".hmcity_flog").removeClass("hmcity_flogshow");	
				$.ajax({
					url : "resource/city_select?account.pkey=${account.pkey}",
					type : "POST",
					data : {"id" : $(this).attr("pkey")},
					success:function(data) {
						location.reload();
					}
				})
			});
		}
	})	
	//城市选择的JS
	$(".select_city").on("click",function(){
		
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
				url : "resource/city_petition?account.pkey=${account.pkey}",
				type : "POST",
				data : {
					"petitionCity" : $(".hmcity_add .new_city").val()
				},
				success: function(data){
					tipbox(data);
				}	
			})
			
		}
			
	});
})
</script>
</html>