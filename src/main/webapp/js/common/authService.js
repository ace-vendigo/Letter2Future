'use strict';
(function () {
    angular.module('L2F.Common').service('AuthService', ['$rootScope', '$http', '$location', 'URLs', AuthService]);

    function AuthService($rootScope, $http, $location, URLs) {
        var auth = this;
        auth.authenticate = function (credentials, callback) {
            var headers = credentials ? {
                authorization: "Basic " +
                btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get(URLs.currentUser, {headers: headers}).then(function (response) {
                $rootScope.authenticated = response.data.name ? true : false;
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });
        };

        auth.login = function (credentials) {
            auth.authenticate(credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded");
                    $location.path(URLs.home);
                    $rootScope.loginError = false;
                } else {
                    console.log("Login failed");
                    $location.path(URLs.login);
                    $rootScope.loginError = true;
                }
            });
        };

        auth.logout = function () {
            $http.post(URLs.logout, {}).finally(
                function () {
                    $rootScope.authenticated = false;
                    $location.path(URLs.home);
                }
            );
        }
    }
})();