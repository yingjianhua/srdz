var resource = angular.module("resourceModule");
resource.factory("eventService",["$http","$rootScope","SITE_CONFIG",function(e,t,o){
	var n=o.API_URL;
	return{
		event:function(t){return e({method:"GET",url:n+"m/event/item-v2.3/"+t+"?from=h5"})},
		eventList:function(t,o,a,r,i){var s=n+"m/event/list";return 0!==o&&(s+="?categoryId="+o),e({method:"GET",url:s,params:{cityId:t?t:0,page:a,pageSize:10,pid:r,eventType:i}})},
		submitOrder:function(o,a,r,i,s,l,c,d){return e({method:"POST",url:n+"m/event/order",params:{appUserId:o,eventInfoId:a,eventFieldId:r,subscribeNum:i,mobilePhone:s,userName:l,smsCode:c,subscribeId:d,siteCode:t.siteCode}})}
		}
	}]
)