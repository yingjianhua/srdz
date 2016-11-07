angular.module("utilModule",[]).factory("utilService",["$rootScope","$location",function(e,t){
	var o=window.navigator.userAgent.toLowerCase();
	return{
		checkInWX:function(){return/(MicroMessenger)/i.test(o)},
		checkInAlipay:function(){return/(Alipay)/i.test(o)},
		checkInIOS:function(){return/(iPhone|iPad|iPod|iOS)/i.test(o)},
		checkInAndroid:function(){return/(Android)/i.test(o)},
		checkInApp:function(){return/(YhouseAppVersion|YHMainApp)/i.test(o)},
		checkAppVersion:function(){for(var e=null,t=o.split(" "),n=0;n<t.length;n++)0===t[n].indexOf("yhouseappversion")&&(e=t[n].substring("yhouseappversion".length+1));return e},
		checkAppVersionGT:function(e){return this.checkAppVersion()>=e},
		getAppDeviceid:function(){for(var e="",t=o.split(" "),n=0;n<t.length;n++)0===t[n].indexOf("deviceid")&&(e=t[n].substring("deviceid".length+1));return e},
		openApp:function(t,o){var n="yhouse://";"undefined"!=typeof t&&(n+=t),"undefined"==typeof o&&(o=!0);var a="https://appsto.re/cn/eWMhV.i",r="http://d.yhouse.com/YHOUSE.apk";"seal|100057|m"===e.siteCode?(a="https://lnk0.com/ZJpwxp",r="http://d.yhouse.com/YHOUSE_100057.apk"):"seal|100028|m"===e.siteCode?(a="https://lnk0.com/kgogY1",r="http://d.yhouse.com/YHOUSE_100028.apk"):"seal|100051|m"===e.siteCode?(a="https://lnk0.com/Vd4oog",r="http://d.yhouse.com/YHOUSE_100051.apk"):"sms|2016marriott|m"===e.siteCode?a="https://lnk0.com/tAdMd4":"market|100060|m"===e.siteCode?(a="https://lnk0.com/gcMpEh",r="http://d.yhouse.com/YHOUSE_100060.apk"):"market|2016michelin|m"===e.siteCode&&(a="https://lnk0.com/sUhUh0");var i="http://a.app.qq.com/o/simple.jsp?pkgname=com.yhouse.code";if(this.checkInWX()){if(!o&&!confirm("只有下载客户端\n才能享受免费活动的福利哦"))return;window.location=i}else{var s=r;this.checkInIOS()&&(s=a);var l=(new Date).valueOf();setTimeout(function(){(new Date).valueOf()-l>100||(o||confirm("只有下载客户端\n才能享受免费活动的福利哦"))&&(window.location=s)},50);var c=document.createElement("iframe");c.src=n,c.style.display="none",document.body.appendChild(c),setTimeout(function(){document.body.removeChild(c)},1e3)}},
		openH5:function(e){var t="yhouse://",o="/";"undefined"!=typeof e&&(t+=e,o+=e);var n=(new Date).valueOf();setTimeout(function(){(new Date).valueOf()-n>100||(window.location=o)},50);var a=document.createElement("iframe");a.src=t,a.style.display="none",document.body.appendChild(a),setTimeout(function(){document.body.removeChild(a)},1e3)},
		getPageName:function(){return{topic:"专题详情",event:"活动详情",meal:"套餐详情",course:"生活学院详情",family:"亲子详情","event-order":"填写订单","meal-order":"填写订单","course-order":"填写订单","family-order":"填写订单","event-pay":"支付","meal-pay":"支付","course-pay":"支付","family-pay":"支付","my-order-list":"我的订单","order-event":"订单详情","order-host":"到店付详情","order-meal":"订单详情","pay-result":"购买成功",login:"登录",register:"手机登录","forget-pwd":"忘记密码",about:"关于我们","about-app":"关于我们",agreement:"服务条款及隐私政策","agreement-app":"服务条款及隐私政策",qa:"YHOUSE悦会APP常见问题","qa-app":"YHOUSE悦会APP常见问题",invitation:"邀请函","special-topic":"","special-topic-app":"","meal-party":"组局详情","meal-party-order":"订单详情",article:"图文详情"}},
		getHomeTabs:function(){return[{id:0,title:"活动",key:"event"},{id:1,title:"套餐",key:"meal"},{id:2,title:"生活学院",key:"course"},{id:3,title:"亲子",key:"family"}]},
		getEventKey:function(e){var t=["event","course","family"];return e&&angular.isNumber(e)&&t[e]?t[e]:"event"},
		getMealTabName:function(){return[{id:0,title:"亮点",content:"","class":"highlight"},{id:1,title:"菜单",content:"","class":"menu"},{id:2,title:"须知",content:"","class":"guideline"}]},
		getOrderListTabName:function(){return[{id:0,title:"全部",content:[],showGetMore:!1,currentPage:1,caption:"暂无购买信息"},{id:1,title:"购买成功",content:[],showGetMore:!1,currentPage:1,caption:"暂无成功订单"},{id:2,title:"未支付",content:[],showGetMore:!1,currentPage:1,caption:"暂无未支付订单"},{id:3,title:"无效订单",content:[],showGetMore:!1,currentPage:1,caption:"暂无无效订单"}]},
		getShareTagTabName:function(){return[{id:0,title:"最热",content:""},{id:1,title:"最新",content:""}]},
		clearId:function(e){return parseInt(e)},
		isEmptyNumber:function(e){return"undefined"==typeof e||null===e||0===e||"0"===e||""===e||isNaN(e)},
		isEmptyString:function(e){return"undefined"==typeof e||null===e||""===e},
		isOnlyNumAlpha:function(e){return/^(\d|[a-zA-Z])+$/.test(e)?"":"请输入数字、字母"},
		capitalizeString:function(e){return e.charAt(0).toUpperCase()+e.slice(1)},
		unionPayPara:function(){return{unionPayScript:"http://wallet.95516.com/s/wl/web/sdk/js/UPSdk.js",unionPayDebug:!1,unionPayAppId:"123456",unionPayAppName:"Wallet"}},
		payLimit:function(){return{wxLimit:8e3,alipayLimit:2e4,cardLimit:1e4,unionPayLimit:1e4,unionWebPayLimit:2e3,cmbPayLimit:5e3}},
		checkMobile:function(e){return e&&/^1[3|4|5|7|8]\d{9}$/.test(e)?"":"请输入正确的11位手机号码"},
		handleRecommendManual:function(e,t,o){var n="";"guide"===e?n=t.data.text:("news"===e||"experience"===e)&&(n=t.data.content);var a=t.recommendManual,r=/<img\s*src=['"][^'"]*['"]\s*dataid=['"][^'"]*['"]\s*datatype=['"][^'"]*['"]\s*name=['"]recommendImg['"]\s*style=['"][^'"]*['"]\s*[\/]?>/g,i=/dataid=['"][^'"]*['"]/,s=/datatype=['"][^'"]*['"]/,l=n.match(r);if(l&&l.length>0)for(var c=0;c<l.length;c++){for(var d=l[c],p=i.exec(d).toString().replace(/'/g,"").replace(/\"/g,"").replace("dataid=",""),u=s.exec(d).toString().replace(/'/g,"").replace(/\"/g,"").replace("datatype=",""),m="",h="",g="",f="",y="",v=0;v<a.length;v++)if(1===a[v].contentType?(y=a[v].data.district,f=a[v].data.neededCredits,g=a[v].data.hostName):2===a[v].contentType?(y=a[v].data.businessesDistrict,f=a[v].data.priceStr,g=a[v].data.hostName):(y="",f="",g=""),a[v].data.id&&a[v].data.id==p&&a[v].contentType&&a[v].contentType==u){m=a[v].data.title,h=a[v].data.picUrl;break}var b="";(this.checkAppVersionGT("2.3")||this.checkInIOS()||o!==!0)&&(b='<y-recommend-manual id="'+p+'" type="'+u+'" title="'+m+'" category="'+e+'" source="'+t.data.id+'" pic-url="'+h+'" host-name="'+g+'" price-str="'+f+'" district-name="'+y+'" pos="'+c+'"></y-recommend-manual>'),n=n.replace(d,b)}return n},
		handleImage:function(t,o){if(!t)return"";var n="-q75m";(e.inAPP||o===!1)&&(n="-q75");var a=/\ssrc=['"][^'"]*['"]/gi,r=t.match(a);if(r&&r.length>0)for(var i=0;i<r.length;i++)t=t.replace(r[i],' class="lazy_img" y-lazy-src="'+r[i].replace(/'/g,"").replace(/\"/g,"").replace("src=","")+n+'"');return t}
		}
	}]
);