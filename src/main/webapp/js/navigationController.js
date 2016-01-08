l4fApp.controller('navigationController', ['$rootScope', '$scope', '$http', '$location', '$route',
    function ($rootScope, $scope, $http, $location, $route) {

        $rootScope.greeting = 'Hello,';
        $scope.credentials = {};

        $scope.errorOccurred = function (errorText, nextLocation) {
            $scope.error = errorText;
            if (nextLocation) {
                $location.path(nextLocation);
            }
        };

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function(credentials, callback) {
            $http.post("/user/login", $scope.credentials).success(function (data) {
                if (data.message) {
                    console.log("Login succeeded");
                    $location.path("/");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                    $rootScope.currentUser = data.currentUser;
                } else {
                    console.log("Login failed");
                    $rootScope.authenticated = false;
                    $scope.errorOccurred(data.error, "/login");
                }
            });
        };

        $scope.login = function() {
            authenticate($scope.credentials);
        };

        $scope.logout = function() {
            $http.post("/user/logout", {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function() {
                console.log("Logout failed");
                $rootScope.authenticated = false;
            });
        };
    }]);