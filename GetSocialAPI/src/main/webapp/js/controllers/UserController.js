'use strict';

GetSocial.controller('UserController',
    function UserController($scope, $cookies, $location) {

        var authCookie = $cookies['AUTH-TOKEN'];
        $scope.init = function () {
            if (authCookie) {
                $location.url('/profile');
            }
        };

        $scope.init();
});
