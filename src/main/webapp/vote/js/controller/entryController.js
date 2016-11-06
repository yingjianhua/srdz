angular.module("mainApp")
.controller("entryController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	function(r, s, l, c) {
		r.home.currentTabId = 3;
	}]
);