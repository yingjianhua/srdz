var resource = angular.module("resourceModule");
resource.factory("orderService",["$http","$rootScope","$location","SITE_CONFIG",function(e,t,o,n){
	var a=n.API_URL,
	r=o.protocol()+"://"+o.host();
	return{
		checkPromoCode:function(t,o,n){return e({method:"GET",url:a+"ipay/subscribe/promocodecheck/"+t,params:{productType:o,productId:n}})},
		submitOrderPayUP:function(o,n,r){return r=r?r:"",e({method:"GET",url:a+"ipay/unionpay/unifiedorder",params:{subscribeType:n,subscribeId:o,siteCode:t.siteCode,promoCode:r}})},
		submitOrderPayWX:function(o,n,i,s,l,c){return s=s?s:"",e({method:"GET",url:a+"ipay/wx/unifiedorder",params:{subscribeType:i,subscribeId:o,openId:n,siteUrl:r,siteCode:t.siteCode,promoCode:s,concatName:l,concatPhone:c}})},
		submitOrderPayAlipay:function(e,o,n,i){return i=i?i:"",a+"ipay/alipay/mobilewebpay?&subscribeType="+o+"&subscribeId="+e+"&showUri="+encodeURIComponent(n)+"&siteUrl="+encodeURIComponent(r+"/pay-result/")+"&siteCode="+t.siteCode+"&promoCode="+i},
		submitOrderPayCmbpay:function(e,o,n,i){return i=i?i:"",a+"ipay/cmb/wap?&subscribeType="+o+"&subscribeId="+e+"&showUri="+encodeURIComponent(n)+"&siteUrl="+encodeURIComponent(r+"/pay-result/")+"&siteCode="+t.siteCode+"&promoCode="+i},
		submitOrderPayUnionWebPay:function(e,o,n,i){return i=i?i:"",a+"ipay/unionpay/wap?&subscribeType="+o+"&subscribeId="+e+"&showUri="+encodeURIComponent(n)+"&siteUrl="+encodeURIComponent(r+"/pay-result/")+"&siteCode="+t.siteCode+"&promoCode="+i},
		orderPayBack:function(e){return a+"ipay/ret/payback/"+e+"?from=m&siteUrl="+encodeURIComponent(r)},
		payResult:function(t){return e({method:"GET",url:a+"ipay/subscribe/item/"+t})},
		getReserveCode:function(t){return e({method:"GET",url:a+"ipay/subscribe/reservecode/list/"+t})},
		payResultEvent:function(t){return e({method:"GET",url:a+"ipay/subscribe/event/"+t})},
		myOrderList:function(t,o,n){return e({method:"GET",url:a+"m/user/reserve_2.8/list",params:{status:t,page:o,pageSize:n}})},
		detail:function(o,n,r){return e({url:a+"m/user/reserve/detail",params:{userId:o,subscribeId:n,type:r,siteCode:t.siteCode}})},
		hostPayDetail:function(t,o){return e({url:a+"m/shopPay/reserve/detail",params:{subscribeId:t,type:o}})},
		setreservedate:function(t,o,n){return e({method:"POST",url:a+"m/meal/setreservedate/"+t,params:{orderDate:o,orderTime:n}})}
		}
	}]
)