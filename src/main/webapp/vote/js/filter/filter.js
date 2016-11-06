angular.module("mainApp")
.filter("recordFilter", function() {
	return function(input) {
		return input+"票"; 
	}
})
.filter("numberFilter", function() {
	return function(input) {
		return "编号"+input;
	}
})