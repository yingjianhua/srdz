var resource = angular.module("resourceModule");
resource.factory("commonService",["$http","$interval","$rootScope","SITE_CONFIG",function(e,t,o,n){
	var a=n.API_URL,r=n.SAPI_URL;
	return{
		getCity:function(){return e({method:"GET",url:a+"m/city/getCityByIP"})},
		initWxConfig:function(o,n,r,i,s){("undefined"==typeof n||""===n)&&(n="YHOUSE悦会"),("undefined"==typeof r||""===r)&&(r="YHOUSE精选美食与玩乐，款待最重要的人。"),("undefined"==typeof i||""===i)&&(i="http://m.yhouse.com"),("undefined"==typeof s||""===s)&&(s="http://r.yhres.com/logo.jpg");var l=t(function(){"undefined"!=typeof wx&&(t.cancel(l),e({method:"GET",url:a+"m/securityTool/wxjsconfig",params:{url:o}}).success(function(e,t,o){e.hasOwnProperty("code")&&"0"===e.code&&(wx.config({debug:!1,appId:e.data.appID,timestamp:e.data.timestamp,nonceStr:e.data.nonceStr,signature:e.data.signature,jsApiList:["onMenuShareAppMessage"]}),wx.ready(function(){wx.onMenuShareAppMessage({title:n,desc:r,link:i,imgUrl:s,success:function(){},cancel:function(){}})}))}))},200,0)},
		getHomeNav:function(){return e({method:"GET",url:a+"config/homeNav.m"})},
		getHomeTabs:function(){return[{id:0,title:"玩乐",catalogId:1,key:"event",event:"Event Tab Click",category:"Event"},{id:1,title:"美食",catalogId:2,key:"meal",event:"Meal Tab Click",category:"Meal"},{id:2,title:"生活学院",catalogId:3,key:"course",event:"Course Tab Click",category:"Course"},{id:3,title:"亲子",catalogId:7,key:"family",event:"Family Tab Click",category:"Family"}]},
		getHomeTabValue:function(e,t,o){var n={};switch(o){case 1:n={id:e,title:t,catalogId:o,key:"event",event:"Event Tab Click",category:"Event"};break;case 2:n={id:e,title:t,catalogId:o,key:"meal",event:"Meal Tab Click",category:"Meal"};break;case 3:n={id:e,title:t,catalogId:o,key:"course",event:"Course Tab Click",category:"Course"};break;case 7:n={id:e,title:t,catalogId:o,key:"family",event:"Family Tab Click",category:"Family"};break;default:n=""}return n},
		categoryList:function(){return e({method:"GET",url:a+"m/category/list/all"})},
		promoList:function(t,o){return e({method:"GET",url:a+"m/promocode/exchangedlist",params:{subscribeType:t,subscribeId:o}})},
		queryPromocode27:function(t){return e({method:"POST",url:a+"m/promocode/exclusive/collectinfo/"+t})},
		queryPromoUserId:function(t){return e({method:"POST",url:a+"m/promocode/exclusive/collectinfo/",params:{promoUserId:t}})},
		promo:function(t,o){return e({method:"GET",url:a+"m/promocode/exclusive/collect",params:{mobilePhone:t,promocode:o}})},
		promo22:function(t){return e({method:"POST",url:a+"m/promocode/exchange",params:{promocode:t}})},
		queryPromocode:function(){return e({method:"GET",url:a+"m/promocode/exclusive"})},
		queryVipcode27:function(){return e({method:"GET",url:a+"m/promocode/exclusive-v2.5?from=h5"})},
		modifyVipcode27:function(t){return e({method:"POST",url:a+"m/promocode/exclusive/modify",params:{promocode:t}})},
		queryInviteList:function(t,o){return e({method:"GET",url:a+"m/promocode/awardList-v2.7",params:{page:t,pageSize:o}})},
		isGetPromo:function(t){var o=a+"m/promocode/my/"+t;return e({method:"GET",url:o})},
		promoThirdParty:function(t,o){return e({method:"GET",url:a+"m/promocode/thirdParty/collect",params:{mobilePhone:t,promocode:o}})},
		invitation:function(t){return e({method:"GET",url:a+"m/invitation/guest",params:{code:t}})},
		invitationNew:function(t){return e({method:"GET",url:a+"m/invitation/guest-v2.3.1",params:{code:t}})},
		invitationHost:function(t,o,n,r){return e({method:"GET",url:a+"m/invitation/host-v2.6/"+t+"/"+o,params:{styleId:n,subscribeDate:r}})},
		invitationStyle:function(){return e({method:"GET",url:a+"config/invitationStyles"})},
		invitationChangeName:function(t,o){return e({method:"POST",url:a+"m/invitation/name/changed",params:{code:t,name:o}})},
		invitationChange:function(t,o,n,r){return e({method:"POST",url:a+"m/invitation/invitationInfo/changed",params:{code:t,name:o,subscribeDate:n,styleId:r}})},
		catalog:function(t,o){if("event"===t)t=1;else if("meal"===t)t=2;else if("course"===t)t=3;else{if("family"!==t)return;t=7}return e({method:"GET",url:a+"m/promotion/list_v2.5/"+t+"?",params:{cityId:o,siteInfo:"m"}})},
		fullSearch:function(t,o,n){return e({method:"GET",url:a+"m/fullSearch-v3.3",params:{cityId:t,key:o,type:n}})},
		searchHotKey:function(t){return e({method:"GET",url:a+"m/search/hostKeys",params:{cityId:t}})},
		filterList:function(t){return e({method:"GET",url:a+"m/filter/list-v31/"+t})},
		checkApi:function(){return e({method:"GET",url:a+"check-api"})},
		checkSapi:function(){return e({method:"GET",url:r+"check-api"})},
		filterResult:function(t,o,n,r,i,s,l,c,d,p,u,m,h,g,f,y,v,b){return e({method:"GET",url:a+"m/search-v3.1",params:{bizType:t,catalog:o,cityId:n,filter:r,page:i,pageSize:10,pid:s,key:l,ag:c,bc:d,ct:p,cx:u,dt:m,mc:h,pc:g,ps:f,sort:y,pos:v,coordType:b}})},
		channelPromocodeHandler:function(t){return e({method:"GET",url:a+"m/promocode/channelPromocodeHandler",params:{query:t}})}
		}
	}]
)