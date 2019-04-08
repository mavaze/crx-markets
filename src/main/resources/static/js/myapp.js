var app = angular.module('app', []);

//#######################
//JSA CONTROLLER
//#######################

app.controller('jsaController', function($scope, $http, $location) {
	$scope.listCustomers = [];

	// $scope.getAllCustomer =
	function getAllCustomer(){
		// get URL
		var url = $location.absUrl() + "algorithm/find-water-volume";

		// do getting
		$http.get(url).then(function (response) {
			$scope.getDivAvailable = true;
			$scope.listCustomers = response.data;
		}, function error(response) {
			$scope.postResultMessage = "Error Status: " +  response.statusText;
		});
	}

	getAllCustomer();
});
