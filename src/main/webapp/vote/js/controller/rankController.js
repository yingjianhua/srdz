angular.module("mainApp")
.controller("rankController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	function(r, s, l, c) {
		r.home.currentTabId = 2;
	}]
);