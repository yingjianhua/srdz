 angular.module('mainApp').controller('DemoCtrl', ["$scope","commonService", function($scope, c) {

	 		$scope.banners=c.getBanners();

        }])