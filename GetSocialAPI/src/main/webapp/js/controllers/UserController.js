'use strict';

GetSocial.controller('UserController',
    function UserController($scope, $cookies, $location, $rootScope) {

        $scope.init = function () {
            var authCookie = $cookies['AUTH-TOKEN'];
            if (authCookie) {
                /*$http.get('/api/user/current').success(function (user) {
                    if (user.username) {
                        $rootScope.username = user.username;
                    }
                });*/
                $rootScope.authenticated = true;
                $location.url('/profile');
            }
        };

        $scope.init();
});
