		var app = angular.module('myApp', []);

		app
				.controller(
						'customersCtrl',
						function($scope, $http) {
							$http
									.get(
											"http://192.168.0.166:8080/MediaManagerFinal/mediaItem?mediaItemName=&action=getAllItemsJSON&btnSubmit=Submit+Query")
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
