var resource = angular.module("resourceModule");
resource.factory("mealService",["$http","$rootScope","SITE_CONFIG",function(e,t,o){
	var n=o.API_URL;
	return{
		meal:function(t){return e({method:"GET",url:n+"m/meal/item-v2.3/"+t+"?from=h5"})},
		reserve:function(t){return e({method:"GET",url:n+"m/meal/reserve/"+t+"/today?count=30"})},
		submitOrder:function(o,a,r,i,s,l,c,d){return a=a?a:"",r=r?r:"",e({method:"GET",url:n+"m/meal/order",params:{mealId:o,orderDate:a,orderTime:r,mealNum:i,userName:s,orderPhone:l,smsCode:c,message:d,siteCode:t.siteCode}})},
		mealList:function(t,o,a,r){var i=n+"m/meal/list?";return t&&0!=t&&(i+="&cityId="+t),o&&0!=o&&(i+="&categoryId="+o),e({method:"GET",url:i,params:{page:a,pageSize:10,pid:r}})}
		}
	}]
)