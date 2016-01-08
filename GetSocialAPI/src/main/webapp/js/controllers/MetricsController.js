'use strict';

GetSocial.controller('MetricsController', function MetricsController($scope, FacebookService) {

  $scope.customerName = 'adsad';
  $scope.customerAddress = 'asdasdsadas';


  $scope.connectToFacebook = function() {
	FacebookService.facebookOauth();
  };
});
