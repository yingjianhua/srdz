var resource = angular.module("resourceModule");
resource.factory("loginService",["$http","$rootScope","md5","SITE_CONFIG",function(e,t,o,n){
	var a=n.API_URL;
	return{
		login:function(n,r){var i=o.createHash(r||"");return e({method:"POST",url:a+"m/user/login",params:{username:n,password:i,siteCode:t.siteCode}})},
		wxOAuthEntry:function(e){return a+"m/wechat/login?redirect="+encodeURIComponent(e)},
		wxLogin:function(o){return e({method:"GET",url:a+"m/wechat/userLogin",params:{code:o,siteCode:t.siteCode}})},
		register:function(o,n){return e({method:"POST",url:a+"m/user/unified/smsCodeLogin",params:{mobilePhone:o,smsCode:n,siteCode:t.siteCode}})},
		findPassCode:function(t){return e({method:"POST",url:a+"m/smscode/user/getSmsCodeByResetPwd",params:{params:t}})},
		resetPassCode:function(t,n,r,i){var s=o.createHash(r||"");return e({method:"POST",url:a+"m/user/resetPassWord",params:{userId:t,params:n,pass:s,code:i}})},
		getUserInfo:function(){return e({method:"GET",url:a+"m/user/info"})},
		bindMobileByLogin:function(t){return e({method:"POST",url:a+"m/user/bindMobileByLogin",params:{showPicUrl:t.showPicUrl,unionId:t.unionId,nickName:t.nickName,appId:t.appId,token:t.token,type:t.type,deviceid:t.deviceid,mobilePhone:t.mobilePhone,smsCode:t.smsCode,externalUserCheckCode:t.externalUserCheckCode}})}
		}
	}]
)