'use strict';

foodMeApp.controller('WhoWeAreController', function WhoWeAreController($scope, FacebookService) {

  $scope.customerName = 'adsad';
  $scope.customerAddress = 'asdasdsadas';


  $scope.connectToFacebook = function() {
    //alert('bla bla');
	FacebookService.facebookOauth();
  };
});
