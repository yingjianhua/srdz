var resource = angular.module("resourceModule");
resource.factory("yHttp",[
                          "$rootScope",
                          "$http",
                          "$httpParamSerializer",
		 "SITE_CONFIG",
		 function(e,t,o,n){
			var a={};
			return{
					http:function(e,o,r,i,s,l){
						return 
							o=n.API_URL+o,
							r?r.ts=(new Date).getTime():r={ts:(new Date).getTime()},
							"undefined"==typeof a[o]?(a[o]=!0,t({method:e,url:o,params:r,timeout:15e3}).success(function(e,t,n,r){e.hasOwnProperty("code")&&"0"===e.code?i&&i(e.data,t,n,r):s&&s(e),delete a[o]}).error(function(){l?l():s&&s({code:"1002",message:"网络异常，请稍后重试"}),delete a[o]})):void 0
					},
					get:function(e,t,o,n,a){
						return this.http("GET",e,t,o,n,a)
					},
					post:function(e,t,o,n,a){
						return this.http("POST",e,t,o,n,a)
					},
					url:function(e,t){
						return t?t.ts=(new Date).getTime():t={ts:(new Date).getTime()},e+"?"+o(t)
					}
				}
			}
		]
)