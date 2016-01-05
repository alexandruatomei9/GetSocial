'use strict';

var GetSocial = angular.module('GetSocial', ['ngResource']);

GetSocial.config(function($routeProvider) {

  $routeProvider.
      when('/', {
        controller: 'UserController',
        templateUrl: 'views/login.html'
      }).
      when('/profile', {
        controller: 'ProfileController',
        templateUrl: 'views/profile.html'
      }).
      when('/my-friends', {
		controller: 'MyFriendsController',
        templateUrl: 'views/my-friends.html'
      }).
      when('/how-it-works', {
        controller: 'WhoWeAreController',
        templateUrl: 'views/who-we-are.html'
      }).
      otherwise({
        redirectTo: '/'
      });;
}).constant('API_END_POINT', 'http://localhost:8080/rest');
