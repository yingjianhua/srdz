angular.module("mainApp")
.controller("homeController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	"$sce",
	function(r, s, l, c, $sce) {
		r.home.currentTabId = 1;
		s.des = $sce.trustAsHtml(c.getHomeDes());
	}]
);