'use strict';

GetSocial.service('FacebookService', function FacebookService($rootScope, $http, alert, API_END_POINT) {
  var self = this;

      self.facebookOauth = function() {

          return $http({
              method: 'GET',
              url: API_END_POINT + '/facebook/oauthRedirect'
          }).then(function successCallback(response) {
              return response.data.data.value;
          }, function errorCallback(response) {
              alert(response);
          });
      }


    self.sendCode = function(code) {
        var req = {
            method: 'POST',
            url: API_END_POINT + '/facebook/authCode',
            headers: {
                'Content-Type': 'application/json'
            },
            data: { authorizationCode: code }
        };

        return $http(req).then(function successCallback(response) {
            return response.data.data.value;
        }, function errorCallback(response) {
            alert(response);
        });
    }
});
