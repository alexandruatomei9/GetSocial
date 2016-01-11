'use strict';

GetSocial.service('UserService', function UserService(API_END_POINT, $cookies, $http) {
    var self = this;

    var authCookie = $cookies['AUTH-TOKEN'];
    var headers = {
      'X-AUTH-TOKEN': authCookie
    };

    var  config = {
      headers: headers
    };

    self.getUserProfile = function() {
        return $http.get(API_END_POINT + '/rest/user/details', config)
            .success(function(data) {
                return data;
            })
            .error(function(data) {
                console.log(data);
            });
    };

    self.getUserFriends = function() {
        return $http.get(API_END_POINT + '/rest/user/friends', config)
            .success(function(data) {
              return data;
            })
            .error(function(data) {
              console.log(data);
            });
    }

});
