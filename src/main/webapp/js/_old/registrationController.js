l4fApp.controller('registrationController', ['$rootScope', '$scope', '$http', '$location',
    function ($rootScope, $scope, $http, $location) {

        $scope.credentials = {};

        $scope.isUsernameValid = function () {
            if ($scope.registrationForm.username.$error.required) {
                $scope.usernameErrorMessage = 'Username should be not empty';
                return false;
            }

            var usernameRegex = /^\w{2,20}$/;

            if (!usernameRegex.test($scope.credentials.username)) {
                $scope.usernameErrorMessage = 'Username should consist of letters, numbers or _ and has length 2-20';
                return false;
            }

            return true;
        };

        $scope.isEmailValid = function() {
            if ($scope.registrationForm.email.$error.required) {
                $scope.emailErrorMessage = 'Email should be not empty';
                return false;
            }

            if ($scope.registrationForm.email.$invalid) {
                $scope.emailErrorMessage = 'Invalid email';
                return false;
            }

            return true;
        };

        $scope.isFormValid = function() {
          return $scope.isUsernameValid() && $scope.isEmailValid();
        };

        $scope.register = function () {
            $http.post('/user/register', $scope.credentials).success(function (data) {
                if (data.error) {
                    $scope.registrationError = data.error;
                }
                else {
                    $location.path('/login');
                }
            })
        };
    }]);