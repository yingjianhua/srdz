var resource = angular.module("resourceModule");
resource.factory("partyService",["$http","$rootScope","SITE_CONFIG",function(e,t,o){
	var n=o.API_URL;
	return{
		getPartyInfo:function(t){return e({method:"GET",url:n+"m/party/"+t+"?from=h5"})},
		joinParty:function(t,o,a,r){return e({method:"POST",url:n+"m/party/join",params:{partyId:t,name:o,phoneNumber:a,smsCode:r}})},
		checkPayer:function(t,o){return e({method:"GET",url:n+"m/user/unified/smsCheckAndBindPhone",params:{mobilephone:t,smscode:o}})}
		}
	}]
)