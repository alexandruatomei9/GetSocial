'use strict';

GetSocial.controller('OauthCallbackController',
    function OauthCallbackController(FacebookService) {
        var substring = (document.URL).split("#")[0];
        var code = substring.split("=")[1];
        console.log(code);
        FacebookService.sendCode(code);
        //window.location.href = "http://localhost:9000/";
    });