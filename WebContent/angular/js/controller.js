		var app = angular.module('myApp', []);

		//the ip address needs to be the ip address of the application server
		var ip = location.host;
		//var ip = "localhost:8080";
		console.log(ip);

		app
				.controller(
						'customersCtrl',
						function($scope, $http) {
							$http
									.get(
											"http://" + ip + "/MediaManagerFinal/mediaItem?mediaItemName=&action=getAllItemsJSON&btnSubmit=Submit+Query")
									.success(
											function(response) {
												$scope.mediaItems = response.mediaItemList;
											})
									.error(
											function(data, status, headers, config) {
												alert("Unable to load page:" + status);
											})
											;
						});
