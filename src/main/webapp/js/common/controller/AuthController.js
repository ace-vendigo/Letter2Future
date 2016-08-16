'use strict';
(function () {
    angular.module('L2F.Common').controller('AuthController', ['AuthService', AuthController]);

    function AuthController(AuthService) {
        var auth = this;

        auth.credentials = {};

        auth.login = function () {
            AuthService.login(auth.credentials);
        };

    }
})();