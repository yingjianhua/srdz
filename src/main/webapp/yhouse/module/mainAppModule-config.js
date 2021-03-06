mainApp.config([
                "$routeProvider",
                "$locationProvider",
                "$compileProvider",
                "$analyticsProvider",
                "$translateProvider",
                function(e,t,o,n,a){
	t.html5Mode(!0),
	o.aHrefSanitizationWhitelist(/^\s*(https?|mailto|tel|javascript|yhouse):/),
	n.virtualPageviews(!1),
	e.when("/",{name:"home",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/redirect-app/:encodedRedirect",{name:"redirect-app",templateUrl:"partials/blank.html",controller:"redirectController"})
		.when("/city/:cityId",{name:"home",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/event-list",{name:"event-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/event-list/city/:cityId",{name:"event-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/event-list-category/:categoryId",{name:"event-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/meal-list",{name:"meal-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/meal-list/city/:cityId",{name:"meal-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/meal-list-category/:categoryId",{name:"meal-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/course-list",{name:"course-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/course-list/city/:cityId",{name:"course-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/family-list",{name:"family-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/family-list/city/:cityId",{name:"family-list",templateUrl:"partials/home.html",controller:"homeController"})
		.when("/course/:eventId",{name:"course",templateUrl:"partials/event.html",controller:"eventController"})
		.when("/course-app/:eventId",{name:"course-app",templateUrl:"partials/event-app.html",controller:"eventController"})
		.when("/course-order/:eventId",{name:"course-order",templateUrl:"partials/event-order.html",controller:"eventOrderController",requireLogin:!1,requireLoginInWX:!0})
		.when("/course-pay",{name:"course-pay",templateUrl:"partials/event-pay.html",controller:"eventPayController",requireLogin:!0,requireOrderInfo:!0})
		.when("/family/:eventId",{name:"family",templateUrl:"partials/event.html",controller:"eventController"})
		.when("/family-app/:eventId",{name:"family-app",templateUrl:"partials/event-app.html",controller:"eventController"})
		.when("/family-order/:eventId",{name:"course-order",templateUrl:"partials/event-order.html",controller:"eventOrderController",requireLogin:!1,requireLoginInWX:!0})
		.when("/family-pay",{name:"course-pay",templateUrl:"partials/event-pay.html",controller:"eventPayController",requireLogin:!0,requireOrderInfo:!0})
		.when("/event/:eventId",{name:"event",templateUrl:"partials/event.html",controller:"eventController"})
		.when("/event-app/:eventId",{name:"event-app",templateUrl:"partials/event-app.html",controller:"eventController"})
		.when("/event-order/:eventId",{name:"event-order",templateUrl:"partials/event-order.html",controller:"eventOrderController",requireLogin:!1,requireLoginInWX:!0})
		.when("/event-pay",{name:"event-pay",templateUrl:"partials/event-pay.html",controller:"eventPayController",requireLogin:!0,requireOrderInfo:!0})
		.when("/topic/:topicId",{name:"topic",templateUrl:function(e){return/136[1234]/.test(e.topicId)?"partials/topic-lincoln.html":1394==e.topicId?"partials/topic-lexus.html":"partials/topic.html"}})
		.when("/topic-app/:topicId",{name:"topic-app",templateUrl:function(e){return/136[1234]/.test(e.topicId)?"partials/topic-lincoln.html":1394==e.topicId?"partials/topic-lexus.html":"partials/topic.html"}})
		.when("/meal/:mealId",{name:"meal",templateUrl:"partials/meal.html",controller:"mealController"})
		.when("/meal-app/:mealId",{name:"meal-app",templateUrl:"partials/meal-app.html",controller:"mealController"})
		.when("/meal-order/:mealId",{name:"meal-order",templateUrl:"partials/meal-order.html",controller:"mealOrderController",requireLogin:!1,requireLoginInWX:!0})
		.when("/meal-pay",{name:"meal-pay",templateUrl:"partials/meal-pay.html",controller:"mealPayController",requireLogin:!0,requireOrderInfo:!0})
		.when("/sku-pop/:skuType/:content/:skuId",{name:"sku-pop",templateUrl:"partials/sku-pop.html",controller:"skuPopController"})
		.when("/sku-pop-app/:skuType/:content/:skuId",{name:"sku-pop",templateUrl:"partials/sku-pop.html",controller:"skuPopController"})
		.when("/host/:hostId",{name:"host",templateUrl:"partials/host.html",controller:"hostController"})
		.when("/host-app/:hostId",{name:"host-app",templateUrl:"partials/host-app.html",controller:"hostController"})
		.when("/host-pay-result/:outTradeNo",{name:"host-pay-result",templateUrl:"partials/host-pay-result.html",controller:"hostPayResultController"})
		.when("/host-pay-result-app/:outTradeNo",{name:"host-pay-result-app",templateUrl:"partials/host-pay-result.html",controller:"hostPayResultController"})
		.when("/host-order-rule",{name:"host-order-rule",templateUrl:"partials/host-order-rule.html",controller:"hostOrderRuleController"})
		.when("/host-order-rule-app",{name:"host-order-rule-app",templateUrl:"partials/host-order-rule.html",controller:"hostOrderRuleController"})
		.when("/news/:newsId",{name:"news",templateUrl:"partials/news.html",controller:"newsController"})
		.when("/news-app/:newsId",{name:"news-app",templateUrl:"partials/news.html",controller:"newsController"})
		.when("/experience/:experienceId",{name:"experience",templateUrl:"partials/experience.html",controller:"experienceController"})
		.when("/experience-app/:experienceId",{name:"experience-app",templateUrl:"partials/experience.html",controller:"experienceController"})
		.when("/guide/:guideId",{name:"guide",templateUrl:"partials/guide.html",controller:"guideController"})
		.when("/guide-app/:guideId",{name:"guide-app",templateUrl:"partials/guide.html",controller:"guideController"})
		.when("/my-order-list",{name:"my-order-list",templateUrl:"partials/my-order-list.html",controller:"myOrderListController",requireLogin:!0})
		.when("/order-event/:orderId",{name:"order-event",templateUrl:"partials/order-event.html",controller:"orderEventController",requireLogin:!0})
		.when("/order-course/:orderId",{name:"order-course",templateUrl:"partials/order-event.html",controller:"orderEventController",requireLogin:!0})
		.when("/order-family/:orderId",{name:"order-family",templateUrl:"partials/order-event.html",controller:"orderEventController",requireLogin:!0})
		.when("/order-meal/:orderId",{name:"order-meal",templateUrl:"partials/order-meal.html",controller:"orderMealController",requireLogin:!0})
		.when("/order-host/:orderId",{name:"order-host",templateUrl:"partials/order-host.html",controller:"orderHostController",requireLogin:!0})
		.when("/meal-party/:partyId",{name:"meal-party",templateUrl:"partials/meal-party.html",controller:"mealPartyController",requireLogin:!0})
		.when("/meal-party-app/:partyId",{name:"meal-party",templateUrl:"partials/meal-party.html",controller:"mealPartyController",requireLogin:!0})
		.when("/meal-party-order/:partyId",{name:"meal-party-order",templateUrl:"partials/meal-party-order.html",controller:"mealPartyOrderController",requireLogin:!0})
		.when("/pay-result/:outTradeNo",{name:"pay-result",templateUrl:"partials/pay-result.html",controller:"payResultController"})
		.when("/pay-result-app/:outTradeNo",{name:"pay-result-app",templateUrl:"partials/pay-result-app.html",controller:"payResultController"})
		.when("/pay-result-event/:eventSubscribeId",{name:"pay-result-event",templateUrl:"partials/pay-result.html",controller:"payResultEventController"})
		.when("/pay-result-event-app/:eventSubscribeId",{name:"pay-result-event-app",templateUrl:"partials/pay-result-app.html",controller:"payResultEventController"})
		.when("/pay-dae-app",{name:"pay-dae-app",templateUrl:"partials/pay-dae-app.html"})
		.when("/login-wx/:code",{name:"login-wx",templateUrl:"partials/login-wx.html",controller:"loginWxController"})
		.when("/login",{name:"login",templateUrl:"partials/login.html",controller:"loginController"})
		.when("/logout",{name:"logout",templateUrl:"partials/login.html",controller:"logoutController"})
		.when("/register",{name:"register",templateUrl:"partials/register.html",controller:"registerController"})
		.when("/forget-pwd",{name:"forget-pwd",templateUrl:"partials/forget-pwd.html",controller:"forgetPwdController"})
		.when("/reset-pwd",{name:"reset-pwd",templateUrl:"partials/reset-pwd.html",controller:"forgetPwdController"})
		.when("/promo/:promoCode",{name:"promo",templateUrl:"partials/promo.html",controller:"promoController"})
		.when("/promo-partner/:promoCode",{name:"promo-partner",templateUrl:"partials/promo-partner.html",controller:"promoPartnerController"})
		.when("/promo-new",{name:"promo-new",templateUrl:"partials/promo-new.html",controller:"promoNewController"})
		.when("/invitation/:invitationId/:inviterName",{name:"invitation",templateUrl:"partials/invitation.html",controller:"invitationController"})
		.when("/invitation/:invitationCode",{name:"invitation",templateUrl:"partials/invitation.html",controller:"invitationController"})
		.when("/invitation-app/:detailType/:orderId/:styleId",{name:"invitation-app",templateUrl:"partials/invitation-app.html",controller:"invitationAppController"})
		.when("/vip-code-app/:noWX",{name:"vip-code-app",templateUrl:"partials/vip-code-app.html",controller:"vipCodeController"})
		.when("/vip-code-app",{name:"vip-code-app",templateUrl:"partials/vip-code-app.html",controller:"vipCodeController"})
		.when("/vip-prize-app",{name:"vip-prize-app",templateUrl:"partials/vip-prize-app.html",controller:"vipPrizeController"})
		.when("/load-vip-code-app",{name:"load-vip-code-app",templateUrl:"partials/load-vip-code.html",controller:"loadVipCodeController"})
		.when("/recruit",{name:"recruit",templateUrl:"partials/recruit.html",controller:"recruitController"})
		.when("/article/:articleId",{name:"article",templateUrl:"partials/article.html",controller:"articleController"})
		.when("/share-tag",{name:"share-tag",templateUrl:"partials/share-tag.html",controller:"shareTagController"})
		.when("/webcast-stat/:webcastId",{name:"webcast-stat",templateUrl:"partials/webcast-stat.html",controller:"webcastStatController"})
		.when("/webcast-live/:webcastId",{name:"webcast-live",templateUrl:"partials/webcast-live.html",controller:"webcastLiveController",requireLogin:!0,requireLoginInWX:!0})
		.when("/cs-im",{name:"cs-im",templateUrl:"partials/custom-service.html",controller:"customServiceController"})
		.when("/cs-im-app",{name:"cs-im-app",templateUrl:"partials/custom-service-app.html",controller:"customServiceController"})
		.when("/webcast-video/:videoId",{name:"webcast-video",templateUrl:"partials/webcast-video.html",controller:"webcastVideoController"})
		.when("/webcast-preview",{name:"webcast-preview",templateUrl:"partials/webcast-preview.html",controller:"webcastPreviewController"})
		.when("/webcast-forenotice-list",{name:"webcast-forenotice-list",templateUrl:"partials/webcast-forenotice-list.html",controller:"webcastForenoticeListController"})
		.when("/webcast-forenotice-list-app",{name:"webcast-forenotice-list",templateUrl:"partials/webcast-forenotice-list.html",controller:"webcastForenoticeListController"})
		.when("/search",{name:"search",templateUrl:"partials/search.html",controller:"searchController"})
		.when("/coupon-rules-app",{name:"coupon-rules-app",templateUrl:"partials/coupon-rules-app.html"})
		.when("/agreement",{name:"agreement",templateUrl:"partials/agreement.html"})
		.when("/agreement-app",{name:"agreement-app",templateUrl:"partials/agreement.html"})
		.when("/about",{name:"about",templateUrl:"partials/about.html"})
		.when("/about-app",{name:"about-app",templateUrl:"partials/about.html"})
		.when("/qa",{name:"qa",templateUrl:"partials/qa.html",controller:"qaController"})
		.when("/qa-app",{name:"qa-app",templateUrl:"partials/qa.html",controller:"qaController"})
		.when("/empty-app",{name:"empty-app",templateUrl:"partials/empty-app.html",controller:"emptyController"})
		.when("/contest-host-list-app",{name:"contest-host-list-app",templateUrl:"partials/contest-host.html",controller:"contestHostController"})
		.when("/contest-host/:hostId",{name:"contest-host",templateUrl:"partials/contest-host.html",controller:"contestHostController",requireLogin:!1,requireLoginInWX:!1})
		.when("/contest-host-prize",{name:"contest-host-prize",templateUrl:"partials/contest-host-prize.html"})
		.when("/contest-host-rules",{name:"contest-host-rules",templateUrl:"partials/contest-host-rules.html"})
		.when("/contest-host-ranking",{name:"contest-host-ranking",templateUrl:"partials/contest-host-ranking.html",controller:"contestRankingController"})
		.when("/contest-host-mobile-bind",{name:"contest-host-mobile-bind",templateUrl:"partials/contest-host-mobile-bind.html",controller:"contestHostMobileBindController",requireLogin:!0,requireLoginInWX:!0})
		.when("/share-tag-upload",{name:"share-tag-upload",templateUrl:"partials/share-tag-upload.html",controller:"shareTagUploadController"})
		.when("/404",{name:"404",templateUrl:"partials/404.html"})
		.otherwise({redirectTo:"/"}),
	a.translations("zh",{
		REASONS_FOR_RECOMMENDATION:"推荐理由",
		MORE_DETAILS:"查看详情",
		MENU:"菜单",
		FULL_MENU:"完整图文菜单",FULL_EVENT:"完整活动详情",FULL_COURSE:"完整课程详情",DINING_FACILITIES:"餐厅设施",NOTICE:"注意事项",MORE:"更多",TIPS:"温馨提示",
		CUSTOMER_IM_SERVICE:"在线客服",CUSTOMER_TEL_SERVICE:"电话客服",YOU_MAY_LIKE:"猜你喜欢",FOR_FREE:"免费"
			}),
	a.translations("en",{
		REASONS_FOR_RECOMMENDATION:"Highlights",MORE_DETAILS:"More",MENU:"Menu",FULL_MENU:"Full Menu",FULL_EVENT:"Full Details",
		FULL_COURSE:"Full Details",DINING_FACILITIES:"Dining Facilities",NOTICE:"Notice",MORE:"More",TIPS:"Tips",
		CUSTOMER_IM_SERVICE:"Online Service",CUSTOMER_TEL_SERVICE:"Call Us",YOU_MAY_LIKE:"You May Like",FOR_FREE:"For Free"
			}),
	a.preferredLanguage("zh"),
	a.useSanitizeValueStrategy("escape")
	}]
)