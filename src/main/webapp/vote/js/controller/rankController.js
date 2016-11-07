angular.module("mainApp")
.controller("rankController", [
	"$rootScope", 
	"$scope", 
	"$location",
	"commonService",
	function(r, s, l, c) {
		r.home.currentTabId = 2;
		s.page = 1;
		s.orderBy = 1;
		s.search = function() {
			s.page = 1;
			s.entrys = c.getEntryList(s.page++).items;
		}
		s.search();
		s.getMore = function() {
			var items = c.getEntryList(s.page++).items;
			items.length>0&&items.forEach(function(item) {
				s.entrys.push(item);
			})
		}
	}]
);