var mainApp = angular.module("mainApp");
mainApp.controller("homeController",[
                                     "$window",
                                     "$location",
                                     "$routeParams",
                                     "$rootScope",
                                     "$scope",
                                     "$timeout",
                                     "$q",
                                     "eventService",
                                     "mealService",
                                     "commonService",
                                     "utilService",
                                     "infoService",
                                     "specialService",
                                     "SITE_CONFIG",
                                     function(e,t,o,n,a,r,i,s,l,c,d,p,u,m){
		n.page.pageTitle="YHOUSE悦会 - 精选轻奢美食、玩乐活动、生活学院课程预订",
		"undefined"!=typeof n.homeTabs&&"undefined"!=typeof n.home&&(n.page.showFilterCategory="course"!==n.homeTabs[n.home.currentTabId].key,
				n.page.showFilterCategory="family"!==n.homeTabs[n.home.currentTabId].key);
		var h=function(e){
				if("undefined"==typeof e||null===e||""===e||0==e||isNaN(e))
					return 0;
				e=parseInt(e);
				for(var t=0;t<n.cityList.length;t++)
					if(e===n.cityList[t].id)
						return n.cityList[t].id;
				return 0
			},
		g=function(e){
				if("undefined"==typeof e||null===e||""===e||"unknown"===e)
					return 0;
				for(var t=0;t<n.cityList.length;t++)
					if(e==n.cityList[t].name)
						return n.cityList[t].id;
				return 0
			},
		f=function(e){
				if("undefined"==typeof e||null===e||""===e||0==e||isNaN(e))
					return"城市";
				for(var t=0;t<n.cityList.length;t++)
					if(e===n.cityList[t].id)
						return n.cityList[t].name;
				return"城市"
			},
		y=function(){
				var a=i.defer(),
				r=i.defer();
				c.getHomeNav().success(function(e,t,o){a.resolve(e)}),
				o.cityId?r.resolve(o.cityId):e.localStorage.getItem("cityId")?r.resolve(e.localStorage.getItem("cityId")):c.getCity().success(function(e,t,o){e.data&&e.data.city?r.resolve(e.data.city):r.resolve()}).error(function(){r.resolve()}),i.all([a.promise,r.promise]).then(function(o){
					for(var a=o[0],r=[],i=0;i<a.cityShow.length;i++)
						r[i]=a.city[a.cityShow[i]];
					n.cityList=r;
					var s=o[1],l=0;
					l=isNaN(s)&&"string"==typeof s?g(s):h(s),
							n.initCityId=l,
							"seal|onstar|m"===n.siteCode&&null==e.sessionStorage.getItem("visitTopic")&&u.topicList([0,l,2,1,1],
									function(o){
										if(o.doc&&o.doc[0]&&o.doc[0].id){
											t.path("/topic/"+o.doc[0].id);
											try{
												e.sessionStorage.setItem("visitTopic","visited")
											}catch(n){}
										}
									});
					for(var d=[],p=0;p<a.data[0].catalogs.length;p++)
						a.city[l].item1===a.data[0].catalogs[p].catalogId&&(d[0]=c.getHomeTabValue(0,a.data[0].catalogs[p].catalogName,a.data[0].catalogs[p].catalogId)),
						a.city[l].item2===a.data[0].catalogs[p].catalogId&&(d[1]=c.getHomeTabValue(1,a.data[0].catalogs[p].catalogName,a.data[0].catalogs[p].catalogId)),
						a.city[l].item3===a.data[0].catalogs[p].catalogId&&(d[2]=c.getHomeTabValue(2,a.data[0].catalogs[p].catalogName,a.data[0].catalogs[p].catalogId)),
						a.city[l].item4===a.data[0].catalogs[p].catalogId&&(d[3]=c.getHomeTabValue(3,a.data[0].catalogs[p].catalogName,a.data[0].catalogs[p].catalogId));
				d&&d.length>=1&&(n.homeTabs=d),
				n.$emit("initHomeTabsSucc")
				})
			};
		void 0!==typeof n.homeTabs&&n.homeTabs||y(),
		n.$on("initHomeTabsSucc",function(){
			if(n.home.currentTabId)
				n.switchTab(n.home.currentTabId);
			else if("meal-list"===n.pageName||"event-list"===n.pageName||"course-list"===n.pageName||"family-list"===n.pageName){
				if(n.homeTabs)
					for(var e=0;e<n.homeTabs.length;e++)
						if(n.pageName===n.homeTabs[e].key+"-list"){
							n.switchTab(n.homeTabs[e].id);
							break
						}
			}else 
				n.switchTab(0)
		});
		var v=function(){
				r(function(){
						n.page.okSaveScroll&&!n.page.showFilterWindow&&n.page.showLogo&&(e.pageYOffset<35?n.page.showLogoOut=!0:e.pageYOffset+e.innerHeight>document.body.scrollHeight-35||(n.page.lastScrollPos&&e.pageYOffset<n.page.lastScrollPos?n.page.showLogoOut=!0:e.pageYOffset>n.page.lastScrollPos&&(n.page.showLogoOut=!1))),n.page.lastScrollPos=e.pageYOffset
					},300
				),
				n.page.okSaveScroll&&(n.home.currentTabId?n.home.tabs[n.home.currentTabId].scrollPos=e.pageYOffset:n.home.tabs[0].scrollPos=e.pageYOffset),
				n.home.listAutoLoad&&e.pageYOffset+e.innerHeight>document.body.scrollHeight-20&&n.loadPage()
			};
		a.$on("$routeChangeStart",function(){n.page.okSaveScroll=!1}),
		a.$on("$routeChangeSuccess",function(){angular.element(e).on("scroll touchmove",v),r(function(){e.scrollTo(0,n.home.tabs[n.home.currentTabId]&&n.home.tabs[n.home.currentTabId].scrollPos?n.home.tabs[n.home.currentTabId].scrollPos:0),n.page.okSaveScroll=!0},800)}),
		a.$on("$destroy",function(){angular.element(e).off("scroll touchmove",v)}),
		n.showFilter=function(e){e===n.home.filter.filterType?n.page.showFilterWindow=!n.page.showFilterWindow:e?("undefined"!=typeof _paq&&("city"===e&&_paq.push(["trackEvent","City","City Filter Click"]),"category"===e&&_paq.push(["trackEvent","Category","Category Filter Click"])),n.home.filter.filterType=e,n.page.showFilterWindow=!0):n.page.showFilterWindow=!1;var t=document.getElementById("cityList").getElementsByClassName("active").length;1!=t&&(n.page.showFilterWindow=!0)},
		n.filterCity=function(t){
			if(t=d.clearId(t),n.home.filter.cityId!==t){
				try{
					e.localStorage.setItem("cityId",t)
				}catch(o){
					
				}
				n.home.filter.cityId=t,n.home.filter.city=f(t);
				for(var a=0;a<n.home.tabs.length;a++)
					delete n.home.tabs[a].list
			}
			c.getHomeNav().success(function(e,o,a){
				if(e.hasOwnProperty("code")&&"0"===e.code){
					for(var r=[],i=0;i<e.data[0].catalogs.length;i++)
						e.city[t].item1===e.data[0].catalogs[i].catalogId&&(r[0]=c.getHomeTabValue(0,e.data[0].catalogs[i].catalogName,e.data[0].catalogs[i].catalogId)),
						e.city[t].item2===e.data[0].catalogs[i].catalogId&&(r[1]=c.getHomeTabValue(1,e.data[0].catalogs[i].catalogName,e.data[0].catalogs[i].catalogId)),
						e.city[t].item3===e.data[0].catalogs[i].catalogId&&(r[2]=c.getHomeTabValue(2,e.data[0].catalogs[i].catalogName,e.data[0].catalogs[i].catalogId)),
						e.city[t].item4===e.data[0].catalogs[i].catalogId&&(r[3]=c.getHomeTabValue(3,e.data[0].catalogs[i].catalogName,e.data[0].catalogs[i].catalogId));
					if(r&&r.length>=1){
						var s=n.homeTabs[n.home.currentTabId].key;
						n.home.currentTabId=0;
						for(var l=0;l<r.length;l++)
							if(s===r[l].key){
								n.home.currentTabId=r[l].id;
								break
							}
						n.homeTabs=r,0===n.home.currentTabId&&(n.page.showFilterCategory=0===n.home.currentTabId&&("event"===n.homeTabs[0].key||"meal"===n.homeTabs[0].key),n.home.filter.listType=n.homeTabs[0].key)
					}
				}
				n.reloadPage(),
				n.getAd()
			});
			var r=d.capitalizeString(n.home.filter.listType);
			"undefined"!=typeof _paq&&_paq.push(["trackEvent",r,r+" City Selected",t])
		},
		n.filterCategory=function(e){"meal"===n.home.filter.listType?n.home.filter.mealCategoryId=e:n.home.filter.eventCategoryId=e,n.reloadPage();var t=d.capitalizeString(n.home.filter.listType);"undefined"!=typeof _paq&&_paq.push(["trackEvent",t,t+" Category Selected",e])},
		n.closeFilterWindow=function(){n.page.showFilterWindow=!1},
		n.loadPage=function(){
						var e=n.home.currentTabId;
						n.home.tabs[e].isEnd||n.home.tabs[e].isBusy||(n.home.tabs[e].isBusy=!0,"event"===n.homeTabs[e].key?s.eventList(n.home.filter.cityId,n.home.filter.eventCategoryId,n.home.tabs[e].page,n.home.tabs[e].pid,0).success(function(t,o,a){1===t.data.isEnd&&(n.home.tabs[e].isEnd=!0);
						var r=t.data.doc;
						n.home.tabs[e].list?n.home.tabs[e].list=n.home.tabs[e].list.concat(r):n.home.tabs[e].list=r,
						n.home.tabs[e].page++,
						n.home.tabs[e].isBusy=!1,
						n.home.tabs[e].pid=t.data.pid}):"meal"===n.homeTabs[e].key?l.mealList(n.home.filter.cityId,n.home.filter.mealCategoryId,n.home.tabs[e].page,n.home.tabs[e].pid).success(function(t,o,a){1===t.data.isEnd&&(n.home.tabs[e].isEnd=!0);
						var r=t.data.doc;
						n.home.tabs[e].list?n.home.tabs[e].list=n.home.tabs[e].list.concat(r):n.home.tabs[e].list=r,n.home.tabs[e].page++,n.home.tabs[e].isBusy=!1,n.home.tabs[e].pid=t.data.pid}):"course"===n.homeTabs[e].key?s.eventList(n.home.filter.cityId,0,n.home.tabs[e].page,n.home.tabs[e].pid,1).success(function(t,o,a){1===t.data.isEnd&&(n.home.tabs[e].isEnd=!0);
						var r=t.data.doc;
						n.home.tabs[e].list?n.home.tabs[e].list=n.home.tabs[e].list.concat(r):n.home.tabs[e].list=r,n.home.tabs[e].page++,n.home.tabs[e].isBusy=!1,n.home.tabs[e].pid=t.data.pid}):"family"===n.homeTabs[e].key&&s.eventList(n.home.filter.cityId,0,n.home.tabs[e].page,n.home.tabs[e].pid,2).success(function(t,o,a){1===t.data.isEnd&&(n.home.tabs[e].isEnd=!0);
						var r=t.data.doc;
						n.home.tabs[e].list?n.home.tabs[e].list=n.home.tabs[e].list.concat(r):n.home.tabs[e].list=r,n.home.tabs[e].page++,n.home.tabs[e].isBusy=!1,n.home.tabs[e].pid=t.data.pid}),n.reloadPv(n.home.tabs[e].catalogId))
					},
		n.reloadPage=function(){
						e.scrollTo(0,0);
						var t=n.home.currentTabId;
						n.home.tabs[t].isEnd=!1,
						n.home.tabs[t].page=1,
						n.home.tabs[t].fromPageSize=0,
						n.home.tabs[t].scrollPos=0,
						n.home.tabs[t].list=[],
						n.loadPage(),
						n.page.showFilterWindow=!1
					},
		n.getAd=function(){
			function e(e){
				u.ads(["100,101",n.home.filter.cityId,e],
					function(e){
						var o={bundle:[],reserveList:[]};
						e.bundle&&e.bundle.forEach(function(e){
							o.bundle[e.position]=e
							}),
						e.reserveList&&e.reserveList.forEach(function(e){
							o.reserveList[e.position]=e
							}),
						n.home.tabs[t].carouselSlides=o
						}
					)}
					if(!d.isEmptyNumber(n.home.filter.cityId)){
						var t=n.home.currentTabId;
						n.home.tabs[t].carouselSlides=[],
						("event"===n.homeTabs[t].key||"meal"===n.homeTabs[t].key||"course"===n.homeTabs[t].key||"family"===n.homeTabs[t].key)&&e(n.homeTabs[t].key)
					}
				};
		var b=function(){
				n.home.filter.cityId?n.$emit("getCity",n.home.filter.cityId):o.cityId?n.$emit("getCity",o.cityId):n.initCityId?n.$emit("getCity",n.initCityId):e.localStorage.getItem("cityId")?n.$emit("getCity",h(e.localStorage.getItem("cityId"))):c.getCity().success(function(e){n.$emit("getCity",g(e.data.city))}).error(function(){n.$emit("getCity",0)})
			};
		if(n.$on("getCity",function(e,t){d.isEmptyNumber(t)?n.showFilter("city"):n.filterCity(t)}),
				n.switchTab=function(t){
						t!==n.home.currentTabId&&(
									t=t>=n.homeTabs.length?0:t,
									t=0>t?n.homeTabs.length-1:t,
									n.page.showFilterCategory="course"!==n.homeTabs[t].key&&"family"!==n.homeTabs[t].key,
									n.home.filter.listType=n.homeTabs[t].key,
									n.page.okSaveScroll=!1,
									n.home.currentTabId=t,
									"undefined"==typeof n.home.tabs[n.home.currentTabId].list&&b(),
									e.scrollTo(0,n.home.tabs[n.home.currentTabId].scrollPos?n.home.tabs[n.home.currentTabId].scrollPos:0),
									r(function(){n.page.okSaveScroll=!0},500))
					},!n.home){
			n.home={currentTabId:null,scrollPos:0,city:"",tabs:new Array(4)};
			for(var I=0;I<n.home.tabs.length;I++)
				n.home.tabs[I]={scrollPos:0,isBusy:!1,isEnd:!1,fromPageSize:0,page:1,invalidPage:0,subPage:0,carouselSlides:null,pid:null};
			n.home.filter={cityId:null,filterType:"city",listType:"event",eventCategoryId:0,mealCategoryId:0},
			n.home.eventListType=m.EVENT_LIST_TYPE,
			n.home.listAutoLoad=m.LIST_AUTO_LOAD,
			n.eventCategoryList=[{id:0,name:"全部活动"}],
			n.mealCategoryList=[{id:0,name:"全部美食"}],
			c.categoryList().success(function(e,t,o){
				e.data&&(n.eventCategoryList=n.eventCategoryList.concat(e.data.eventCategory),
						n.mealCategoryList=n.mealCategoryList.concat(e.data.mealCategory))
				})
		}
		o.categoryId&&(n.home.filter.eventCategoryId=d.clearId(o.categoryId)),
		n.goSearch=function(){0===n.home.currentTabId?t.search("type",2).path("/search"):1===n.home.currentTabId?t.search("type",1).path("/search"):2===n.home.currentTabId?t.search("type",6).path("/search"):3===n.home.currentTabId&&t.search("type",7).path("/search")},
		n.goFilter=function(){0===n.home.currentTabId?t.search({bizType:1,catalog:2}).path("/search"):1===n.home.currentTabId?t.search({bizType:1,catalog:1}).path("/search"):2===n.home.currentTabId?t.search({bizType:1,catalog:6}).path("/search"):3===n.home.currentTabId&&t.search({bizType:1,catalog:7}).path("/search")}
     }
])