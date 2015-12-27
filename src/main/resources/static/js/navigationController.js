l4fApp.controller('navigationController', ['$rootScope', '$scope', '$http', '$location', '$route',
    function ($rootScope, $scope, $http, $location, $route) {

        $rootScope.greeting = 'Hello, User!';

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function(credentials, callback) {

            var headers = credentials ? {
                authorization : "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get('user', {
                headers : headers
            }).success(function(data) {
                $rootScope.authenticated = !!data.name;
                callback && callback($rootScope.authenticated);
            }).error(function() {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        };

        authenticate();

        $scope.credentials = {};
        $scope.login = function() {
            authenticate($scope.credentials, function(authenticated) {
                if (authenticated) {
                    console.log("Login succeeded");
                    $location.path("/");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed");
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        $scope.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function() {
                console.log("Logout failed");
                $rootScope.authenticated = false;
            });
        }

    }]);