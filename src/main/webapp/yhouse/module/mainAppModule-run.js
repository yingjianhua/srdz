mainApp.run([
             "$location",
             "$rootScope",
             "$window",
             "$timeout",
             "$cookies",
             "$http",
             "$interval",
             "commonService",
             "loginService",
             "utilService",
             "specialService",
             "SITE_CONFIG",
             "$translate",
             function(e,t,o,n,a,r,i,s,l,c,d,p,u){
	if("undefined"!=typeof a.get("locale")&&"en"===a.get("locale")&&u.use(a.get("locale")),
			t.$on("$routeChangeSuccess",function(e,t,o){
				"undefined"!=typeof _paq&&"meal"!==t.name&&"meal-app"!==t.name&&"event"!==t.name&&"event-app"!==t.name&&"topic"!==t.name&&"topic-app"!==t.name&&"news"!==t.name&&"news-app"!==t.name&&"guide"!==t.name&&"guide-app"!==t.name&&"experience"!==t.name&&"experience-app"!==t.name&&"empty-app"!==t.name&&"host"!==t.name&&"share-tag"!==t.name&&"article"!==t.name&&"webcast-stat"!==t.name&&"webcast-video"!==t.name&&"webcast-live"!==t.name&&"webcast-forenotice-list"!==t.name&&"webcast-preview"!==t.name&&_paq.push(["trackPageView"])
				}),
			t.$on("$routeChangeStart",function(n,r,i){
				if(t.univseralLinksParams="?open="+encodeURIComponent(e.url().substr(e.url().indexOf("/")+1))+"&siteCode="+t.siteCode,"webcast-forenotice-list"===r.name&&(t.univseralLinksParams="?open="+encodeURIComponent("webcast-forenotice-list?title="+encodeURIComponent("直播预告")+"&url="+encodeURIComponent(document.location.protocol+"//"+document.location.host+"/webcast-forenotice-list-app")+"&linkTitle="+encodeURIComponent("坐享身临其境的现场体验")+"&linkContent="+encodeURIComponent("还不快上车？开启YHOUSE直播提醒，精彩不容错过！")+"&linkPic="+encodeURIComponent("http://f.yhres.com/config/forenotice/2016/07/01/1467369206025221.jpg-ssq75")+"&hideShortcut=0&isBack=1")+"&siteCode="+t.siteCode),t.univseralLinksURL=p.UNIVERSAL_LINK_URL,t.pageName=r.name,r.name&&r.name.indexOf("-app")>0||c.checkInApp())
					return t.inAPP=!0,
					t.appOpenClosed=!0,
					t.page={showFilter:!1,showFilterWindow:!1,showHeader:!1,showFooter:!1,showApp:!1,showBg:!1,showPaddingBottom:!1,allowCoupon:!1,banner:"打开APP",showBundle:!0},
					("topic-app"===r.name||"special-topic-app"===r.name||"vip-code-app"===r.name||"vip-prize-app"===r.name||"load-vip-code-app"===r.name||"invitation-app"===r.name||"host-pay-result-app"===r.name)&&(t.page.showBg=!0),
					"pay-result-app"===r.name&&(t.page.showPaddingBottom=!0),
					void("meal-party"===r.name&&(t.page.showPaddingBottom=!0));
				if(null==o.sessionStorage.getItem("currentUser")&&"undefined"!=typeof a.get("UA"))
					try{
						var d=new XMLHttpRequest;
						if(d.open("get","/api/m/user/info",!1),d.send(null),t.currentUser=JSON.parse(d.responseText).data,o.sessionStorage.setItem("currentUser",JSON.stringify(t.currentUser)),"login-wx"===r.name||"login"===r.name||"register"===r.name)
							return void e.path(t.loginNextPath)
						}
					catch(u){}
					if("undefined"==typeof t.currentUser&&o.sessionStorage.getItem("currentUser")&&(t.currentUser=JSON.parse(o.sessionStorage.getItem("currentUser")),t.currentUser&&("undefined"!=typeof _paq&&_paq.push(["setUserId",t.currentUser.id]),"undefined"!=typeof _paq&&t.currentUser.mobilephone&&_paq.push(["setCustomVariable",1,"mobile",t.currentUser.mobilephone,"visit"]),"undefined"!=typeof _vds&&_vds.push(["setCS1","user_id",t.currentUser.id]))),"undefined"==typeof a.get("UA")&&(o.sessionStorage.removeItem("currentUser"),delete t.currentUser),"login-wx"!==r.name&&"login"!==r.name&&"register"!==r.name&&"logout"!==r.name&&"reset-pwd"!==r.name&&"forget-pwd"!==r.name&&"promo"!==r.name&&"404"!==r.name&&"cs-im"!==r.name)
						try{
							o.sessionStorage.setItem("path",e.path())
						}catch(u){}
						if(t.loginNextPath=o.sessionStorage.getItem("path")?o.sessionStorage.getItem("path"):"/","undefined"==typeof t.eventOrderInfo&&o.sessionStorage.getItem("eventOrderInfo")&&(t.eventOrderInfo=JSON.parse(o.sessionStorage.getItem("eventOrderInfo"))),"undefined"==typeof t.mealOrderInfo&&o.sessionStorage.getItem("mealOrderInfo")&&(t.mealOrderInfo=JSON.parse(o.sessionStorage.getItem("mealOrderInfo"))),t.currentUser&&"undefined"==typeof t.userOrderInfo&&o.sessionStorage.getItem("userOrderInfo")&&(t.userOrderInfo=JSON.parse(o.sessionStorage.getItem("userOrderInfo"))),"undefined"==typeof t.currentUser)
							if(t.inWX){
								if(r.requireLoginInWX||r.requireLogin||"login"===r.name||"register"===r.name)
									return void(window.location=l.wxOAuthEntry(t.loginNextPath))
							}else if(r.requireLogin)
								return void e.path("/register");
						if(r.requireOrderInfo&&"undefined"==typeof t.eventOrderInfo&&"undefined"==typeof t.mealOrderInfo)
							return void e.path(t.loginNextPath);
						if(t.currentUser&&("login-wx"===r.name||"login"===r.name||"register"===r.name||"reset-pwd"===r.name||"forget-pwd"===r.name))
							return void e.path(t.loginNextPath);
						if(t.page={scrollPos:0,okSaveScroll:!1,showFilterWindow:!1,showDialog:!1,showBackBtn:!0,allowCoupon:!1,skuYhouseBanner:!1,banner:"打开APP",shareUi:null},t.page.showFooter=p.SHOW_FOOTER&&!t.inAPP,t.page.showHeader=p.SHOW_HEADER&&!t.inAPP,t.page.showApp2=t.page.showApp=p.SHOW_APP&&"undefined"==typeof t.appOpenClosed&&!t.inAPP?!0:!1,e.search().show_app?"0"===e.search().show_app&&(t.page.showApp2=t.page.showApp=!1,o.sessionStorage.setItem("show_app","0")):"0"===o.sessionStorage.getItem("show_app")&&(t.page.showApp2=t.page.showApp=!1),t.page.showBundle=!0,e.search().show_bundle?"0"===e.search().show_bundle&&(t.page.showBundle=!1,o.sessionStorage.setItem("show_bundle","0")):"0"===o.sessionStorage.getItem("show_bundle")&&(t.page.showBundle=!1),t.page.showLogoOut=t.page.showLogo=p.SHOW_LOGO,("event-pay"===r.name||"event-order"===r.name||"meal-pay"===r.name||"meal-order"===r.name||"pay-result"===r.name||"pay-result-event"===r.name||"order-event"===r.name||"order-meal"===r.name||"register"===r.name||"recruit"===r.name||"host-pay-result"===r.name)&&(t.page.showFooter=!1),("meal"===r.name||"event"===r.name||"host"===r.name)&&(t.page.skuYhouseBanner=!1),("pay-result"===r.name||"pay-result-event"===r.name)&&(t.page.showBackBtn=!1),("promo"===r.name||"invitation"===r.name||"promo-partner"===r.name||"promo-new"===r.name||"contest-host-mobile-bind"===r.name)&&(t.page.showFooter=!1,t.page.showHeader=!1),("article"===r.name||"share-tag"===r.name||"webcast-stat"===r.name||"webcast-live"==r.name||"webcast-video"==r.name||"webcast-preview"==r.name||"webcast-forenotice-list"==r.name||"cs-im"==r.name||"cs-im-app"==r.name||"contest-host"==r.name||"contest-host-list-app"==r.name||"contest-host-prize"==r.name||"contest-host-ranking"==r.name||"share-tag-upload"==r.name||"contest-host-rules"==r.name)&&(t.page.showFooter=!1,t.page.showHeader=!1),"search"===r.name&&(t.page.showHeader=!1,t.page.showFooter=!1),("sku-pop"===r.name||"host-order-rule"===r.name)&&(t.page.showFooter=!1,t.page.showBg=!0),("login"===r.name||"forget-pwd"===r.name||"register"===r.name||"reset-pwd"===r.name||"topic"===r.name||"special-topic"===r.name||"invitation"===r.name||"host-pay-result"===r.name||"share-tag-upload"===r.name||"contest-host-mobile-bind"===r.name)&&(t.page.showBg=!0),("event"===r.name||"event-order"===r.name||"event-pay"===r.name||"meal"===r.name||"meal-order"===r.name||"meal-pay"===r.name||"order-event"===r.name||"order-meal"===r.name||"pay-result"===r.name||"user-wx"===r.name||"meal-party"===r.name||"contest-host"===r.name)&&(t.page.showPaddingBottom=!0,"event"!==r.name&&"meal"!==r.name||!e.search()||"1"!==e.search().hide_buy||(t.page.showPaddingBottom=!1)),"home"===r.name||"event-list"===r.name||"meal-list"===r.name||"course-list"===r.name||"family-list"===r.name?(t.page.showHomeMenu=!0,t.page.showFilterCity=!0,t.page.showFilterCategory="course-list"!==r.name,t.page.showFilterCategory="family-list"!==r.name):(t.page.showHomeMenu=!1,t.page.showFilterCity=!1,t.page.showFilterCategory=!1,"special-topic"!==r.name&&"topic"!==r.name&&"news"!==r.name&&"guide"!==r.name&&"experience"!==r.name&&"article"!==r.name&&"share-tag"!==r.name&&"webcast-stat"!==r.name&&"webcast-live"!==r.name&&"webcast-video"!==r.name&&"webcast-preview"!==r.name&&(t.page.showApp=!1)),"share-tag"==r.name&&(t.page.banner="参与话题"),t.headTitle=c.getPageName()[r.name]?c.getPageName()[r.name]:"",t.backBtn=function(){"404"===r.name&&window.history.length>2?window.history.go(-2):window.history.length>2&&void 0!=i?window.history.back():"meal"===r.name?e.path("/meal-list"):"event"===r.name?e.path("/event-list"):"family"===r.name?e.path("/family-list"):"sku-pop"===r.name?"event"==r.params.skuType&&r.params.skuId?e.path("/event/"+r.params.skuId):"meal"==r.params.skuType&&r.params.skuId?e.path("/meal/"+r.params.skuId):e.path("/"):e.path("/")},t.homeBtn=function(){e.path("/").search({catalog:null})},t.inWX&&"home"===r.name&&s.initWxConfig(e.absUrl()),e.search()&&e.search().share_ui){
							t.page.shareUi=e.search().share_ui;
							try{
								o.sessionStorage.setItem("shareUi",t.page.shareUi)
							}catch(u){}
						}
						o.sessionStorage.getItem("shareUi")&&(t.page.shareUi=o.sessionStorage.getItem("shareUi"))
				}),
			e.search().pk_campaign
		){
		var m=e.search().pk_campaign+"|";
		e.search().pk_kwd&&(m+=e.search().pk_kwd),m+="|"+p.SITE_CODE;
		try{
			o.sessionStorage.setItem("siteCode",m)
		}catch(h){
			
		}
	}
	t.siteCode=o.sessionStorage.getItem("siteCode")?o.sessionStorage.getItem("siteCode"):p.SITE_CODE,
			("cheetah|onstar|m"==t.siteCode||"seal|onstar|m"==t.siteCode)&&(r.defaults.headers.common.channelType="onstar"),
			"seal|onstar|m"==t.siteCode&&e.search().data&&e.search().timestamp&&e.search().signature&&s.channelPromocodeHandler(location.search.split("?")[1]).success(function(e,n,a){if(e.hasOwnProperty("code")&&"0"===e.code){t.currentUser=e.data;try{o.sessionStorage.setItem("currentUser",JSON.stringify(e.data))}catch(r){}}}),
			t.inWX=c.checkInWX()?!0:!1,
			t.inAlipay=c.checkInAlipay()?!0:!1,
			t.inUnionPay="seal|unionpay"===t.siteCode,
			t.appGt=function(e){return t.inAPP&&c.checkAppVersionGT(e)},
			t.mOrAppGt=function(e){return!t.inAPP||t.inAPP&&c.checkAppVersionGT(e)},
			r.defaults.headers.common.YhouseSiteVersion=p.SITE_VERSION,
			t.closeBtn=function(){t.appOpenClosed=!0,t.page.showApp=!1,t.page.skuYhouseBanner=!1},
			t.openApp=function(e){("undefined"==typeof t.appOpenEnabled||t.appOpenEnabled===!0)&&(t.appOpenEnabled=!1,"undefined"!=typeof _paq&&_paq.push(["trackEvent","App","App Download"]),
			c.openApp(),
			n(function(){t.appOpenEnabled=!0},1e3))},
			t.openContentUrl=function(n,a){var r=[void 0,"event","meal","guide","experience","news","topic","host"];"undefined"!=typeof r[n]&&(t.inAPP?o.location="yhouse://"+r[n]+"/"+a:e.path("/"+r[n]+"/"+a))},
			t.videoStyle={width:"100%",height:"414px"},
			t.showDialog=function(e){t.page.dialogText=e,t.page.showDialog=!0,n(function(){t.page.showDialog=!1,t.page.dialogText=""},2e3)},
			t.openAppSnsDialog=function(){t.snsDialog.showConfirm=!0},
			t.snsDialog={showConfirm:!1,theme:"",desc:"下载YHOUSE和TA互动吧！",leftBtn:"取消",rightBtn:"下载",cancel:function(){t.snsDialog.showConfirm=!t.snsDialog.showConfirm},confirm:function(){t.snsDialog.showConfirm=!1,t.openApp()}};
	try{o.sessionStorage.setItem("tipDialog","true")}catch(h){}
	if(t.tipDialog={showConfirm:!1,theme:"",desc:"",btn:"确定",cancel:function(){t.tipDialog.showConfirm=!0},confirm:function(){t.tipDialog.showConfirm=!1,o.sessionStorage.removeItem("tipDialog")}},t.$on("pageTitleChanged",function(){"undefined"!=typeof _paq&&_paq.push(["trackPageView",t.page.pageTitle])}),t.inWX){
		var g=document.createElement("script");
		g.src="//res.wx.qq.com/open/js/jweixin-1.0.0.js",
		g.async=!1;
		var f=document.getElementsByTagName("script")[0];
		f.parentNode.insertBefore(g,f)
	}
	var y=c.getAppDeviceid();
	if(y)
		r.defaults.headers.common.deviceId=y,"undefined"!=typeof _paq&&_paq.push(["setCustomVariable",2,"deviceid",y,"visit"]);
	else if(null===o.localStorage.getItem("deviceId")||"undefined"==typeof o.localStorage.getItem("deviceId"))
		d.deviceIdAndSign(function(e){try{o.localStorage.setItem("deviceId",JSON.stringify(e))}catch(t){}r.defaults.headers.common.deviceId=e.deviceId,r.defaults.headers.common.deviceidSign=e.deviceidSign,"undefined"!=typeof _paq&&_paq.push(["setCustomVariable",2,"deviceid",e.deviceId,"visit"])});
	else{
		var v=JSON.parse(o.localStorage.getItem("deviceId"));
		r.defaults.headers.common.deviceId=v.deviceId,
		r.defaults.headers.common.deviceidSign=v.deviceidSign,
		"undefined"!=typeof _paq&&_paq.push(["setCustomVariable",2,"deviceid",v.deviceId,"visit"])
	}
	"undefined"!=typeof _paq&&"undefined"!=typeof a.get("UI")&&_paq.push(["setUserId",a.get("UI")]),
	"undefined"!=typeof _paq&&_paq.push(["setCustomVariable",3,"userAgent",window.navigator.userAgent,"visit"]),
	"undefined"!=typeof _paq&&c.checkInApp()&&_paq.push(["setCustomVariable",4,"YhouseAppVersion",c.checkAppVersion(),"visit"]),
	"undefined"!=typeof _vds&&"undefined"!=typeof a.get("UI")&&_vds.push(["setCS1","user_id",a.get("UI")]),
	o.sessionStorage.getItem("channelType")&&(r.defaults.headers.common.channelType=o.sessionStorage.getItem("channelType")),
	(null===o.localStorage.getItem("rocket")||"undefined"==typeof o.localStorage.getItem("rocket")||JSON.parse(o.localStorage.getItem("rocket")).timestamp+6e5<(new Date).getTime())&&d.ads([],function(e){e.timestamp=(new Date).getTime();
	try{
		o.localStorage.setItem("rocket",JSON.stringify(e))
	}catch(t){}}),
	t.reloadPv=function(e){var t=o.localStorage.getItem("cityId");"undefined"==typeof e&&(e=o.localStorage.getItem("catalogId"));var n=o.localStorage.getItem("rocket")?JSON.parse(o.localStorage.getItem("rocket")).pvList:[];n.forEach(function(o){!o.catalogId||0!==o.cityId.length&&-1===o.cityId.indexOf(t)||0!==o.catalogId.length&&-1===o.catalogId.indexOf(e)||((new Image).src=o.pvUrl,"undefined"!=typeof _paq&&_paq.push(["trackEvent","adPv",o.id]))})},
	t.reloadAdPv=function(e){var t=o.localStorage.getItem("rocket")?JSON.parse(o.localStorage.getItem("rocket")).pvList:[];t.forEach(function(t){t.id==e&&((new Image).src=t.pvUrl,"undefined"!=typeof _paq&&_paq.push(["trackEvent","adPv",t.id]))})},
	t.adClick=function(e,t,n,a){var r=o.localStorage.getItem("cityId"),i=o.localStorage.getItem("catalogId");"undefined"!=typeof _paq&&_paq.push(["trackEvent",AD_TYPE+"  Click",r+"@"+i+"@"+n+"@"+a,e])}
	}
])