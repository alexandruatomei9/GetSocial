'use strict';

foodMeApp.service('FacebookService', function FacebookService($rootScope, $http, alert, API_END_POINT) {
  var self = this;

  self.facebookOauth = function() {
      return $http.get(API_END_POINT + '/facebook/login', {
        id: '123'
      }).then(function(response) {
        return response.data.orderId;
      });
  }
});
