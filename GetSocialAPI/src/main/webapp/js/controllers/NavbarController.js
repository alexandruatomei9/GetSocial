'use strict';

GetSocial.controller('NavbarController', function NavbarController($scope, $cookies, $location) {

    $scope.routeIs = function(routeName) {
      return $location.path() === routeName;
    };

    $scope.logout = function() {
      var authCookie = $cookies['AUTH-TOKEN'];
      if (authCookie) {
        delete $cookies['AUTH-TOKEN'];
      }
      $location.url('/');
    };

});
