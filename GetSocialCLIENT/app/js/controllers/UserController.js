'use strict';

GetSocial.controller('UserController',
    function UserController($scope, FacebookService, $window) {

        $scope.getFacebookRequestUrl = function(){
            FacebookService.facebookOauth().then(function(facebookRedirectUrl) {
                $window.location.href = facebookRedirectUrl;
            });
        }
});
