l4fApp.controller('registrationController', ['$rootScope', '$scope', '$http', '$location',
    function ($rootScope, $scope, $http, $location) {

        $scope.register = function () {
            $http.post("/user/register", $scope.credentials).success(function (data) {
                if (data.error) {
                    $scope.errorOccurred(data.error);
                }
                else {
                    $location.path("/login");
                }
            })
        };
    }]);