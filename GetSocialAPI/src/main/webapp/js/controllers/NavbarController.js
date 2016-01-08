'use strict';

GetSocial.controller('NavbarController', function NavbarController($scope, $cookies, $location, $rootScope) {

    $scope.routeIs = function(routeName) {
      return $location.path() === routeName;
    };

    $scope.logout = function() {
      var authCookie = $cookies['AUTH-TOKEN'];
      if (authCookie) {
        delete $cookies['AUTH-TOKEN'];
      }
      $rootScope.authenticated = true;
      $location.url('/');
    };

});
