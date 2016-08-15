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
<style type="text/css">
.xxx {
	display:block;
	width:100%;
	height:68px;
	line-height:68px;	
	text-align:center;	
	font-size:26px;
	color:#999;
}
</style>
<title>享食光</title>
</head>

<body>	
	
	<h2 class="cm_head1">开启你的私人定制之旅</h2>
	
	<div class="service_apply">
		<form action="listCombo?account.pkey=${account.pkey }" method="post">
			<div class="sap_in">
				<select name="banquetId">
					<option value="">宴会类型不限</option>
					<s:iterator value="banquets" var="banquet">
					<option value="<s:property value="#banquet.pkey" />"><s:property value="#banquet.name" /></option>
					</s:iterator>
				</select>
			</div>
			<div class="sap_in">
				<select name="pnum">
					<option value="">人数不限</option>
					<option value="1">1人</option>
					<option value="2">2人</option>
					<option value="3,4">3-4人</option>
					<option value="4,">4人以上</option>
				</select>
			</div>
			<div class="sap_in">
				<select name="perCapitaBudget">
					<option value="">预算不限</option>
					<option value=",100">100以下</option>
					<option value="100,200">100-200</option>
					<option value="200,300">200-300</option>
					<option value="300,500">300-500</option>
					<option value="500,">500以上</option>
				</select>
			</div>
			<div class="sap_in sap_nb">
				
				<div class="sap_city sap_cityleft lt">
					<span><s:property value="#session.city.name" /></span>	
				</div>
				
				<div class="sap_city rt">
					<span class="select_txt">区域</span>
					<select name="areaId">
						<option value="">区域不限</option>
						<s:iterator value="areas" var="area">
							<option value="<s:property value="#area.pkey" />"><s:property value="#area.name" /></option>
						</s:iterator>
					</select>
				</div>
					
			</div>
		</form>	
	</div>
	
	<a href="#" class="cm_btn1">下一步</a>
	
	<!--footer 底部导航-->
	<jsp:include page="../menu.jsp">
		<jsp:param value="" name="currMenu"/>
	</jsp:include>
	<!--footer 底部导航-->
	<jsp:include page="../messagebox.jsp"/>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
	
	//select和date两个特殊情况为了IOS下做的数据同步
	$(".sap_in select").change(function(){
		var _par = $(this).parents(".sap_in");
		$(".select_txt",_par).html($(this).find("option:selected").text()).addClass("select_txth");
	});
	
	$(".dateType").on("click",function(){
		var _par = $(this).parents(".sap_in");
		$(this).css("opacity",1);
		$(".date_txt",_par).hide();
	});
	
</script>
<script>
</script>
<script>
$(function() {
	$(".cm_btn1").click(function() {
		$(".service_apply form").submit();
	})
})
</script>
</html>