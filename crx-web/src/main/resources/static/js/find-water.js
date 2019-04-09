var app = angular.module('app', []);

app.controller('findWaterController', function($scope, $http, $location) {

	$scope.surfaceProfile = [7, 1, 8, 11, 7, 9, 12];

	function findWaterVolume(elements){
		//var url = $location.absUrl() + "v1/api/algorithm/find-water-volume";
		var url = $location.absUrl() + "www/algorithm/find-water-summary";
		$http.post(url, elements).then(function (response) {
			var body = response.data;
			$scope.waterInHole = body.volume;
			$scope.totalVolume = body.total;
		}, function error(response) {
			$scope.postResultMessage = "Error Status: " +  response.statusText;
		});
	}

	$scope.addRow = function(index) {
		$scope.surfaceProfile.splice(index+1, 0, 0);
	};

	$scope.removeRow = function (index) {
		$scope.surfaceProfile.splice(index, 1);
	};

	$scope.$watchCollection('surfaceProfile', function (newVal, oldVal) {
		findWaterVolume(newVal);
	});
});
