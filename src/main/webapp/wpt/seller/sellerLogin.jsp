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
<title>享食光</title>
</head>

<body class="seller_loginbg">	
	
	<form action="listSellerOrder" method = "post">
	<div class="seller_login">
		
		<div class="selg_in">
			<input type="text" class="text" style="color:#fff"  id="phone" name="manager"  placeHolder="请输入店长电话" />	
		</div>
		
		<div class="selg_in">
			<input type="text" style="color:#fff" class="text yzm" name="identify" placeHolder="请输入验证码" />	
			<a href="javascript:;" class="selg_send">发送验证码</a>
			<span class="selg_wait">60S</span>
		</div>
		<input type="hidden" name="account.pkey" value="${account.pkey }">
		<input type="button" class="selg_sub" value="登录" />
			
	</div>
	</form>
		<div class="selg_error">
		<dl>
			<dt><img src="images/selg_error.png" /></dt>	
			<dd><p>号码或验证码</p>输入有误</dd>
		</dl>	
	</div>
<jsp:include page="../messagebox.jsp" />
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
${jsCode}
</script>
<script type="text/javascript">
	var vTime = 60;
	//selg_send发送验证码
	$(".selg_send").on("click",function(){
			var pattern = /^((13[0-9])|(147)|(15[0-3,5-9])|(17[6-8])|(171)|(18[0-3,5-9]))\d{8}$/;
			if($("[name='manager']").val() === "") {
				showMessage("电话号码不能为空");
				return false;
			} else if (!pattern.test($("[name='manager']").val())){
				showMessage("电话号码格式不正确");
				return false;
			}
		$(".selg_send").hide();
		$(".selg_wait").show();
		InterValObj = setInterval(SetRemainTime, 1000);
	    $.ajax({
	        url:'resource/seller_sendCheckCode?account.pkey=${account.pkey}',
	        type:"POST",
	        data:$('#phone').serialize(),
	        success: function(data) {
	        	if(data == "null")
	        		showMessage("您还没有入住享食光");
	        }
	    });

	});	
	function SetRemainTime(){
		vTime--;
		if(vTime < 0){
			$(".selg_wait").hide();
			$(".selg_send").show();
			clearInterval(InterValObj);
			vTime = 60;
		}
		$(".selg_wait").html(vTime+"S");
	}
	$(".selg_sub").click(function(){
		var pattern = /^((13[0-9])|(147)|(15[0-3,5-9])|(17[6-8])|(171)|(18[0-3,5-9]))\d{8}$/;
		var _manager = $("[name='manager']").val();
			if($("[name='manager']").val() === "") {
				showMessage("电话号码不能为空");
				return false;
			} else if (!pattern.test($("[name='manager']").val())){
				showMessage("电话号码格式不正确");
					return false;
			}else if($("[name='identify']").val() ==""){
				showMessage("验证码");
				return false;
			}else{
		    $.ajax({
		        url:'resource/seller_checkCode?account.pkey=${account.pkey}',
		        type:"POST",
		        data:{
		        	manager : $("[name='manager']").val(),
		        	identify : $("[name='identify']").val()
		        },
		        success: function(data) {
		        	if(data == 'ok')
		        		$("form").submit();
		        	else
		        		$(".selg_error").show();
		        }
		    
		    });
		}
	});
	$(".selg_error").click(function(){
		$(".selg_error").hide();
	});
	
	function showMessage(message){
		$(".selg_error p").text(message);
		$(".selg_error").fadeIn(200);
		setTimeout("$('.selg_error').fadeOut(200)",2000)
	}
	
</script>
</html>