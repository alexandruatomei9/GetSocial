'use strict';

GetSocial.controller('ProfileController', function ProfileController($scope, UserService) {

    $scope.user = {};

    $scope.init = function() {
        UserService.getUserProfile().then(function(response) {
            $scope.user = convertResponseToUser(response.data);
        });
    };

    $scope.init();

    function convertResponseToUser(data){
        var user = {
            firstName: '',
            lastName: '',
            gender: '',
            profilePhoto: ''
        };
        user.firstName = data.firstName;
        user.lastName = data.lastName;
        user.gender = data.gender;
        user.profilePhoto = 'http://graph.facebook.com/'+data.id+'/picture?width=250';

        return user;
    }
});