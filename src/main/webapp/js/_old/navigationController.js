l4fApp.controller('navigationController', ['$rootScope', '$scope', '$http', '$location', '$route', '$localStorage', '$sessionStorage',
    function ($rootScope, $scope, $http, $location, $route, $localStorage, $sessionStorage) {

        $rootScope.greeting = 'Hello,';
        $scope.credentials = {};
        $scope.storage = $sessionStorage;
        $scope.currentUser = $scope.storage.currentUser || {};

        $scope.isAuthenticated = function () {
            return Object.keys($scope.currentUser).length;
        };

        $rootScope.authenticated = $scope.isAuthenticated();

        $scope.errorOccurred = function (errorText, nextLocation) {
            $scope.error = errorText;
            if (nextLocation) {
                $location.path(nextLocation);
            }
        };

        $scope.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {
            $http.post("/user/login", $scope.credentials).success(function (data) {
                if (data.message) {
                    console.log("Login succeeded");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                    $rootScope.currentUser = data.currentUser;
                    $scope.storage.currentUser = data.currentUser;
                    $location.path("/");
                } else {
                    console.log("Login failed");
                    $rootScope.authenticated = false;
                    $scope.errorOccurred(data.error, "/login");
                }
            });
        };

        $scope.login = function () {
            authenticate($scope.credentials);
        };

        $scope.removeUserData = function () {
            delete $scope.storage.currentUser;
            $rootScope.authenticated = false;
        };

        $scope.logout = function () {
            $http.post("/user/logout", {}).success(function () {
                $scope.removeUserData();
                $location.path("/");
            }).error(function () {
                console.log("Logout failed");
                $scope.removeUserData();
            });
        };
    }]);